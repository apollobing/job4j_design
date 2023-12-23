package ru.job4j.ood.isp.violation;

public class SmartWatch implements Watch {
    @Override
    public String showTime() {
        return "Current time is 08:17 AM";
    }

    @Override
    public int countSteps() {
        return 10248;
    }
}
