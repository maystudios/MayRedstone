package net.maystudios.mayredstone.loging;

import java.util.logging.Level;
import java.util.logging.Logger;



public class Log {

    public enum LogType {
        ERROR,
        INFO
    }
    private static final Logger logger = Logger.getLogger(Log.class.getName());

    public static void log(Level lvl, String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2];
        logger.log(lvl, "MayRedstone:[Logger] at Line: " + caller.getLineNumber() + " " + message);
    }

    public static void log(LogType type, String message) {
        switch (type) {
            case ERROR:
                logger.log(Level.SEVERE, message);
                break;
            case INFO:
                logger.log(Level.INFO, message);
                break;
        }
    }

    public static void log(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder sb = new StringBuilder(e.toString());
        sb.append(System.lineSeparator());
        for (StackTraceElement element : stackTrace) {
            sb.append("\tat ");
            sb.append(element);
            sb.append(System.lineSeparator());
        }
        logger.log(Level.SEVERE, sb.toString());
    }

    public static void logLineNumber() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2];
        System.out.println("Linenumber: " + caller.getLineNumber());
    }

    public static int logLineNumberInt() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2];
        return caller.getLineNumber();
    }
}
