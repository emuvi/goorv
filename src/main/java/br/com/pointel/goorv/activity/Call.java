package br.com.pointel.goorv.activity;

import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Context;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;
import br.com.pointel.goorv.domain.PassedBy;

public class Call extends Activity {

    public static final Param PARAM_THE_NAME = new Param("name", "Name of the method.");
    public static final Order ORDER_THE = new Order("the", "Runs the method of the [name].", PARAM_THE_NAME);

    public Call(Context context) {
        super(context, "Runs the commands inside a method.");
    }

    public Object the(PassedBy byPassed) {
        return context.getValue(
            byPassed.getValueStringOrThrow(PARAM_THE_NAME)
        );
    }

}
