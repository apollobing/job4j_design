package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        MenuItem menuItem = new SimpleMenuItem(childName, actionDelegate);
        if (parentName == null) {
            rootElements.add(menuItem);
            rsl = true;
        } else {
            Optional<ItemInfo> parentItem = findItem(parentName);
            if (parentItem.isPresent()) {
                rsl = parentItem.get().menuItem.getChildren().add(menuItem);
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> rsl = Optional.empty();
        Optional<ItemInfo> element = findItem(itemName);
        if (element.isPresent()) {
            rsl = Optional.of(new Menu.MenuItemInfo(element.get().menuItem, element.get().number));
        }
        return rsl;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            Iterator<ItemInfo> iterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo element = iterator.next();
                return new Menu.MenuItemInfo(element.menuItem, element.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rsl = Optional.empty();
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                rsl = Optional.of(itemInfo);
            }
        }
        return rsl;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}