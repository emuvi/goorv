package br.com.pointel.goorv.domain;

import java.util.List;
import br.com.pointel.goorv.service.wizard.WizAct;

public class Command {

    private final Runner runner;
    private final Context context;
    private final List<Token> tokens;

    public Command(Runner runner, Context context, List<Token> tokens) {
        this.runner = runner;
        this.context = context;
        this.tokens = tokens;
    }

    public void run() throws Exception {
        var piped = new Piped(tokens);
        for (int p = piped.size() -1; p >= 0; p--) {
            var prompt = piped.get(p);
            if (prompt.isEmpty()) {
                continue;
            }
            var passedBy = new PassedBy();
            var acting = new Acting(runner, context, passedBy);
            var activityName = prompt.remove(0).getChars();
            var activity = WizAct.getActivity(acting, activityName);
            Order order;
            if (activity.isSingleOrder()) {
                order = activity.getOrder();
            } else {
                var orderName = prompt.remove(0).getChars();
                order = activity.getOrderOrThrow(orderName);
            }
            new Paramer(order, passedBy).parse(prompt);
            var returned = activity.call(order);
            if (returned.isNotVoid()) {
                context.pushStack(returned.getValue());
            }
        }
    }

}
