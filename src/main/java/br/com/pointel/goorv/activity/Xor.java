package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Context;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;
import br.com.pointel.goorv.domain.PassedBy;

public class Xor extends Activity {

    public static final Param PARAM_THE_CASE_A = new Param("caseA", "Case A that will be checked.");
    public static final Param PARAM_THE_CASE_B = new Param("caseB", "Case B that will be checked.");
    public static final Order ORDER_THE = new Order("the", "Runs the logic XOR between or the [caseA] or the [caseB].", PARAM_THE_CASE_A, PARAM_THE_CASE_B);

    public Xor(Context context) {
        super(context, "Returns the logical XOR between the passed by.");
    }

    public Object the(PassedBy byPassed) {
        return context.getValue(
            byPassed.getValueStringOrThrow(PARAM_THE_CASE_A)
        );
    }

}
