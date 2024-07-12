package br.com.pointel.goorv.activity;

import java.util.Map;
import java.util.Set;
import br.com.pointel.goorv.domain.Acting;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;

public class Local extends Activity {

    public static final Order ORDER_SHOW = new Order("show", "Show all values.");

    public static final Param PARAM_GET_NAME = new Param("name", "Name of the value.");
    public static final Order ORDER_GET = new Order("get", "Get the value of the [name].", PARAM_GET_NAME);

    public static final Param PARAM_SET_NAME = new Param("name", "Name of the value.");
    public static final Param PARAM_SET_VALUE = new Param("value", "Description of the value.");
    public static final Order ORDER_SET = new Order("set", "Put the [value] on the [name].", PARAM_SET_NAME, PARAM_SET_VALUE);

    public Local(Acting acting) {
        super(acting,"Local values functionality.", ORDER_SHOW, ORDER_GET, ORDER_SET);
    }

    public void show() {
        send("Local Values:");
        for (Map.Entry<String, Object> entry : acting.context().getValues()) {
            send(entry.getKey() + " = " + entry.getValue());
        }
    }

    public Object get() {
        return acting.context().getValue(
            acting.passedBy().getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public Set<Map.Entry<String, Object>> getAll() {
        return acting.context().getValues();
    }

    public void set() {
        acting.context().putValue(
            acting.passedBy().getValueStringOrThrow(PARAM_SET_NAME), 
            acting.passedBy().getValue(PARAM_SET_VALUE, Object.class)
        );
    }

    public void del() {
        acting.context().delValue(
            acting.passedBy().getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public void clear() {
        acting.context().clearValues();
    }

}
