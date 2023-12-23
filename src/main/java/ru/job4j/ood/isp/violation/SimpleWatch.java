package ru.job4j.ood.isp.violation;

public class SimpleWatch implements Watch {

    @Override
    public String showTime() {
        return "Current time is 12:11 PM";
    }

    @Override
    public int countSteps() {
        throw new UnsupportedOperationException();
    }
}
