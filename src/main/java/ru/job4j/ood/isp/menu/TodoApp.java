package ru.job4j.ood.isp.menu;

public class TodoApp {
    private SimpleMenu simpleMenu = new SimpleMenu();
    private String notificationName;
    private ActionDelegate action = () -> System.out.println("Напоминание: " + notificationName);

    public boolean addToRoot(String parentName, String childName, ActionDelegate actionDelegate) {
        return simpleMenu.add(parentName, childName, actionDelegate);
    }

    public boolean addToChild(String parentName, String childName, ActionDelegate actionDelegate) {
        return addToRoot(parentName, childName, actionDelegate);
    }

    public void action(String itemName) {
        simpleMenu.select(itemName).get().getActionDelegate().delegate();
    }

    public void print() {
        new PrintMenu().print(simpleMenu);
    }

    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp();
        todoApp.addToRoot(Menu.ROOT, "Сходить в магазин", todoApp.action);
        todoApp.addToChild("Сходить в магазин", "Купить продукты", todoApp.action);
        todoApp.addToChild("Купить продукты", "Купить хлеб", todoApp.action);
        todoApp.addToRoot(Menu.ROOT, "Покормить собаку", todoApp.action);
        todoApp.addToChild("Сходить в магазин", "Вернуть просрочку", todoApp.action);
        todoApp.addToRoot(Menu.ROOT, "Приготовить еду", todoApp.action);
        todoApp.addToChild("Купить продукты", "Купить молоко", todoApp.action);
        todoApp.print();
        todoApp.notificationName = "Приготовить еду";
        todoApp.action(todoApp.notificationName);
    }
}
