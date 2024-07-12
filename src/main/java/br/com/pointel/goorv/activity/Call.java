package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Acting;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;

public class Call extends Activity {

    public static final Param PARAM_ACT_NAME = new Param("name", "Name of the method.");
    public static final Order ORDER_ACT = new Order("act", "Executes the method of the [name].", PARAM_ACT_NAME);

    public Call(Acting acting) {
        super(acting, "Executes the commands inside a method.", ORDER_ACT);
    }

    public Object act() {
        return null;
    }

}
