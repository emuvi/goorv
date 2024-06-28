package br.com.pointel.goorv.service.wizard;

public class WizBase {

    private WizBase() {}

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
