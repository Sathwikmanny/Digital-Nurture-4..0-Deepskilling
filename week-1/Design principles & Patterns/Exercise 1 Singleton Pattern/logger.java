import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoggerExample {

    static class Logger {
        private static int logCount = 0;
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        private Logger() {
            System.out.println("Logger started at " + currentTime());
        }

        private static class LoggerHolder {
            private static final Logger INSTANCE = new Logger();
        }

        public static Logger getInstance() {
            return LoggerHolder.INSTANCE;
        }

        public void log(String level, String message) {
            logCount++;
            System.out.printf("[%s] [%s] (%d) %s%n", currentTime(), level.toUpperCase(), logCount, message);
        }

        private String currentTime() {
            return LocalTime.now().format(formatter);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("== Singleton Logger Test ==");

        Logger logger1 = Logger.getInstance();
        logger1.log("info", "Application started");

        Logger logger2 = Logger.getInstance();
        logger2.log("warn", "Low memory warning");

        System.out.println("Same logger instance? " + (logger1 == logger2));

        Runnable task = () -> {
            Logger logger = Logger.getInstance();
            logger.log("debug", "Thread " + Thread.currentThread().getName() + " logging");
        };

        Thread[] threads = {
            new Thread(task, "Worker-1"),
            new Thread(task, "Worker-2"),
            new Thread(task, "Worker-3")
        };

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        Logger logger3 = Logger.getInstance();
        logger3.log("error", "Final log entry");

        System.out.println("Still same instance? " + (logger1 == logger3));
    }
}
