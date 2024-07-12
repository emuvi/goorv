package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Context;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;
import br.com.pointel.goorv.domain.PassedBy;

public class And extends Activity {

    public static final Param PARAM_THE_CASE_A = new Param("caseA", "Case A that will be checked.");
    public static final Param PARAM_THE_CASE_B = new Param("caseB", "Case B that will be checked.");
    public static final Order ORDER_THE = new Order("the", "Runs the logic AND between the [caseA] and the [caseB].", PARAM_THE_CASE_A, PARAM_THE_CASE_B);

    public And(Context context) {
        super(context, "Returns the logical AND between the passed by.");
    }

    public Object the(PassedBy byPassed) {
        return context.getValue(
            byPassed.getValueStringOrThrow(PARAM_THE_CASE_A)
        );
    }

}
