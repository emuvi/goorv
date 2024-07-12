package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Acting;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;

public class Or extends Activity {

    public static final Param PARAM_ACT_CASE_A = new Param("caseA", "Case A that will be checked.");
    public static final Param PARAM_ACT_CASE_B = new Param("caseB", "Case B that will be checked.");
    public static final Order ORDER_ACT = new Order("act", "Runs the logic OR between the [caseA] or the [caseB].", PARAM_ACT_CASE_A, PARAM_ACT_CASE_B);

    public Or(Acting acting) {
        super(acting, "Returns the logical OR between the passed by.", ORDER_ACT);
    }

    public Object act() {
        return null;
    }

}
