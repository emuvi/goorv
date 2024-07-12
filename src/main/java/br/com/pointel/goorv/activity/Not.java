package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Context;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;
import br.com.pointel.goorv.domain.PassedBy;

public class Not extends Activity {

    public static final Param PARAM_THE_CASE = new Param("case", "Case that will be switched.");
    public static final Order ORDER_THE = new Order("the", "Runs the logic NOT on the [case].", PARAM_THE_CASE);

    public Not(Context context) {
        super(context, "Returns the logical NOT of the passed by.");
    }

    public Object the(PassedBy byPassed) {
        return context.getValue(
            byPassed.getValueStringOrThrow(PARAM_THE_CASE)
        );
    }

}
