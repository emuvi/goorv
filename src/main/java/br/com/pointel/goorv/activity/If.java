package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Acting;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;

public class If extends Activity {

    public static final Param PARAM_ACT_CASE = new Param("case", "Case that will be checked.");
    public static final Order ORDER_ACT = new Order("act", "Runs the logic CHECK on the [case] and executes if true.", PARAM_ACT_CASE);

    public If(Acting acting) {
        super(acting, "If the case is true, executes the block of commands.", ORDER_ACT);
    }

    public Object act() {
        return null;
    }

}
