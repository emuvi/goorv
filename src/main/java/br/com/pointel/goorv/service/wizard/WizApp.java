package br.com.pointel.goorv.service.wizard;

public class WizApp {

    public static final String NAME = "goorv";

    private WizApp() {}

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

    public static Thread startDaemon(Runnable task, String name) {
        var result = new Thread(task, name);
        result.setDaemon(true);
        result.start();
        return result;
    }

}
