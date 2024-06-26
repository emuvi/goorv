package br.com.pointel.goorv.service.wizard;

import java.util.List;
import java.util.stream.Collectors;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

public class WizApp {

    public static final String NAME = "goorv";

    public static String activityPackage = "br.com.pointel.goorv.activity";

    public static List<String> getActivities() {
        try (ScanResult scanResult = new ClassGraph().acceptPackages(activityPackage)
                        .scan()) {
            return scanResult.getAllClasses().stream().map(c -> c.getName()).collect(
                            Collectors.toList());
        }
    }

    public static String execute(String prompt) throws Exception {
        var promptParts = WizChars.parseStrings(prompt);
        if (promptParts.isEmpty()) {
            throw new IllegalArgumentException("Prompt not informed");
        }
        var operation = PromptOperation.LAUNCH;
        if (promptParts.get(0).equals("help")) {
            operation = PromptOperation.HELP;
            promptParts.remove(0);
        } else if (promptParts.get(0).equals("start")) {
            operation = PromptOperation.START;
            promptParts.remove(0);
        }
        if (promptParts.isEmpty()) {
            throw new IllegalArgumentException("Activity name not informed");
        }
        var activityName = promptParts.get(0);
        var activityVars = new String[promptParts.size() - 1];
        for (int i = 1; i < promptParts.size(); i++) {
            activityVars[i - 1] = promptParts.get(i);
        }
        switch (operation) {
            case HELP:
                return getHelpOfActivity(activityName);
            case START:
                startActivity(activityName, activityVars);
                return null;
            case LAUNCH:
                launchActivity(activityName, activityVars);
                return null;
        }
        throw new IllegalArgumentException("Invalid operation");
    }

    private static enum PromptOperation { HELP, START, LAUNCH }

    public static void startActivity(String activityName, String... activityVars) {
        new Thread(() -> {
            try {
                launchActivity(activityName, activityVars);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Activity " + activityName).start();
    }

    public static void launchActivity(String activityName, String... activityVars) throws Exception {
        Class<?> clazz = Class.forName(activityPackage + "." + activityName);
        clazz.getMethod("launch", String[].class).invoke(null, (Object) activityVars);
    }

    public static String getHelpOfActivity(String activityName) throws Exception {
        Class<?> clazz = Class.forName(activityPackage + "." + activityName);
        return (String) clazz.getMethod("help").invoke(null);
    }

}
