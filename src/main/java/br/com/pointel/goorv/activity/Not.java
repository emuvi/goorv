package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Acting;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;

public class Not extends Activity {

    public static final Param PARAM_ACT_CASE = new Param("case", "Case that will be switched.");
    public static final Order ORDER_ACT = new Order("act", "Runs the logic NOT on the [case].", PARAM_ACT_CASE);

    public Not(Acting acting) {
        super(acting, "Returns the logical NOT of the passed by.", ORDER_ACT);
    }

    public Object act() {
        return null;
    }

}
