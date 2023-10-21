package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean learn = true;
        String programmingLanguage = "Java";
        char mainSymbol = 'J';
        byte version = 17;
        double minorMicroVersion = 0.7D;
        short allTasks = 708;
        long completedTasks = 394;
        int uncompletedTasks = allTasks - (int) completedTasks;
        float donePercentage = (float) completedTasks / allTasks * 100;
        LOG.debug("""

                        Do you learn programming? {}
                        Which language? {}
                        Which main symbol? {}
                        Which version? {}
                        Which minor and micro version? {}
                        Total task? {}
                        Completed? {}
                        Uncompleted? {}
                        Progress? {}%""",
                learn, programmingLanguage, mainSymbol, version, minorMicroVersion,
                allTasks, completedTasks, uncompletedTasks, donePercentage);
    }
}