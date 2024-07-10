package br.com.pointel.goorv.service.wizard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Context;
import br.com.pointel.goorv.domain.Parsing;
import br.com.pointel.goorv.domain.Typed;
import br.com.pointel.goorv.domain.ValuedAs;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

public class WizAct {

    public static final String NAME = "goorv";

    public static final String ACTIVITY_PACKAGE = "br.com.pointel.goorv.activity";

    public static List<String> getActivities() {
        try (ScanResult scanResult = new ClassGraph().acceptPackages(ACTIVITY_PACKAGE)
                        .scan()) {
            return scanResult.getAllClasses().stream().map(c -> c.getName()).collect(
                            Collectors.toList());
        }
    }

    public static void execute(Context context, String prompt) {
        try {
            var allPrompt = WizChars.parseArguments(prompt);
            var pipedPrompt = new ArrayList<List<ValuedAs>>();
            var currentPrompt = new ArrayList<ValuedAs>();
            currentPrompt = splitPipedPrompts(allPrompt, pipedPrompt, currentPrompt);
            pipedPrompt.add(currentPrompt);
            var stackedPrompt = new ArrayList<ValuedAs>();
            for (int i = pipedPrompt.size() -1; i >= 0; i--) {
                var promptCall = pipedPrompt.get(i);
                if (promptCall.isEmpty()) {
                    continue;
                }
                var activityName = promptCall.remove(0).getString();
                var activity = getActivity(context, activityName);
                var orderName = promptCall.remove(0).getString();
                var order = activity.getOrderOrThrow(orderName);
                promptCall.addAll(stackedPrompt);
                stackedPrompt.clear();
                if (promptCall.isEmpty()) {
                    stackValue(activity.call(order), stackedPrompt);
                } else {
                    var params = new Parsing(order).parse(promptCall);
                    stackValue(activity.call(order, params), stackedPrompt);
                }
            }
        } catch (Exception e) {
            context.sendError(e);
        }
    }

    private static ArrayList<ValuedAs> splitPipedPrompts(
                    List<String> allPrompt,
                    ArrayList<List<ValuedAs>> pipedPrompt,
                    ArrayList<ValuedAs> currentPrompt) {
        for (String promptPart : allPrompt) {
            if (promptPart.equals("|")) {
                pipedPrompt.add(currentPrompt);
                currentPrompt = new ArrayList<>();
            } else {
                currentPrompt.add(new ValuedAs(promptPart));
            }
        }
        return currentPrompt;
    }

    private static void stackValue(Typed typed, List<ValuedAs> stackedPrompt) {
        if (typed.getType() != void.class && typed.getType() != Void.class) {
            stackedPrompt.add(new ValuedAs(typed.getValue()));
        }
    }

    public static Activity getActivity(Context context, String activityName) throws Exception {
        Class<?> clazz = Class.forName(ACTIVITY_PACKAGE + "." + activityName);
        return (Activity) clazz.getConstructor(Context.class).newInstance(context);
    }

}
