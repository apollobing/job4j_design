package ru.job4j.ood.lsp;

class VacuumCleaner {
    protected boolean active = true;

    public String vacuum() {
        if (!active) {
            throw new RuntimeException("Vacuum cleaner isn't active");
        }
        System.out.println("Vacuum starts");
        return "Vacuum starts";
    }
}

class XiaomiCleaner extends VacuumCleaner {
    @Override
    public String vacuum() {
        active = false;
        System.out.println("Wash starts");
        return "Wash starts";
    }
}

class Cellphone {
    protected int batteryFull = 0;

    public void charge(int percent) {
        if (percent > 100) {
            throw new IllegalArgumentException("Battery can't charge over 100%");
        } else {
            batteryFull = percent;
        }
    }
}

class SamsungPhone extends Cellphone {

    public void charge(int percent) {
        if (percent > 200) {
            throw new IllegalArgumentException("Battery can't charge over 100%");
        } else {
            batteryFull = percent;
            System.out.println(batteryFull);
        }
    }
}

class Toaster {
    protected int breadSlice;

    public Toaster(int breadSlice) {
        validate(breadSlice);
        this.breadSlice = breadSlice;
    }

    protected void validate(int breadSlice) {
        if (breadSlice < 1 || breadSlice > 2) {
            throw new IllegalArgumentException("Toaster works only with one or two bread slices");
        } else {
            this.breadSlice = breadSlice;
        }
    }
}

class BoshToaster extends Toaster {

    public BoshToaster(int breadSlice) {
        super(breadSlice);
    }

    protected void validate(int breadSlice) {
            this.breadSlice = breadSlice;
    }
}

class Main {
    public static void main(String[] args) {
        VacuumCleaner vacuum = new XiaomiCleaner();
        vacuum.vacuum();
        Cellphone phone = new SamsungPhone();
        phone.charge(150);
        Toaster toaster = new BoshToaster(5);
    }
}