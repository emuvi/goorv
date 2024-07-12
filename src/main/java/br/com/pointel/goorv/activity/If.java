package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Context;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;
import br.com.pointel.goorv.domain.PassedBy;

public class If extends Activity {

    public static final Param PARAM_THE_CASE = new Param("case", "Case that will be checked.");
    public static final Order ORDER_THE = new Order("the", "Runs the logic CHECK on the [case] and executes if true.", PARAM_THE_CASE);

    public If(Context context) {
        super(context, "If the case is true, executes the block of commands.");
    }

    public Object the(PassedBy byPassed) {
        return context.getValue(
            byPassed.getValueStringOrThrow(PARAM_THE_CASE)
        );
    }

}
