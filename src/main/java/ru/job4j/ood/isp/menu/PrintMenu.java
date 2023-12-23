package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class PrintMenu implements MenuPrinter {
    private StringBuilder menuOutput = new StringBuilder();

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        while (iterator.hasNext()) {
            String tabulator = "";
            Menu.MenuItemInfo element = iterator.next();
            if (element.getNumber().length() == 4) {
                tabulator = "-- ";
            } else if (element.getNumber().length() == 6) {
                tabulator = "---- ";
            }
            menuOutput.append(tabulator).append(element.getNumber())
                    .append(element.getName()).append("\n");
        }
        System.out.println(menuOutput.toString());
    }

    public String getMenuOutput() {
        return menuOutput.toString();
    }
}
