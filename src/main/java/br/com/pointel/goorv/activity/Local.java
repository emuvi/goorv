package br.com.pointel.goorv.activity;

import java.util.Map;
import java.util.Set;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Context;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;
import br.com.pointel.goorv.domain.PassedBy;

public class Local extends Activity {

    public static final Order ORDER_SHOW = new Order("show", "Show all values.");

    public static final Param PARAM_GET_NAME = new Param("name", "Name of the value.");
    public static final Order ORDER_GET = new Order("get", "Get the value of the [name].", PARAM_GET_NAME);

    public static final Param PARAM_PUT_NAME = new Param("name", "Name of the value.");
    public static final Param PARAM_PUT_VALUE = new Param("value", "Description of the value.");
    public static final Order ORDER_PUT = new Order("put", "Put the [value] on the [name].", PARAM_PUT_NAME, PARAM_PUT_VALUE);

    public Local(Context context) {
        super(context,"Local values functionality.", 
            ORDER_SHOW, ORDER_GET, ORDER_PUT
        );
    }

    public void show() {
        send("Local Values:");
        for (Map.Entry<String, Object> entry : context.getValues()) {
            send(entry.getKey() + " = " + entry.getValue());
        }
    }

    public Object get(PassedBy byPassed) {
        return context.getValue(
            byPassed.getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public Set<Map.Entry<String, Object>> getAll() {
        return context.getValues();
    }

    public void put(PassedBy byPassed) {
        context.putValue(
            byPassed.getValueStringOrThrow(PARAM_PUT_NAME), 
            byPassed.getValueAny(PARAM_PUT_VALUE)
        );
    }

    public void del(PassedBy byPassed) {
        context.delValue(
            byPassed.getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public void clear() {
        context.clearValues();
    }

}
