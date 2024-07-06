package br.com.pointel.goorv.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import br.com.pointel.goorv.Activity;
import br.com.pointel.goorv.Context;
import br.com.pointel.goorv.Order;
import br.com.pointel.goorv.Param;
import br.com.pointel.goorv.PassedBy;

public class GlobalValues extends Activity {

    public static final Order ORDER_SHOW = new Order("show", "Show all values.");

    public static final Param PARAM_GET_NAME = new Param("name", "Name of the value.");
    public static final Order ORDER_GET = new Order("get", "Get the value of the [name].", PARAM_GET_NAME);

    public static final Param PARAM_PUT_NAME = new Param("name", "Name of the value.");
    public static final Param PARAM_PUT_VALUE = new Param("value", "Description of the value.");
    public static final Order ORDER_PUT = new Order("put", "Put the [value] on the [name].", PARAM_PUT_NAME, PARAM_PUT_VALUE);

    private static final Map<String, Object> GLOBAL_VALUES = new HashMap<>();

    public GlobalValues(Context context) {
        super(context,"Global values functionality.", 
            ORDER_SHOW, ORDER_GET, ORDER_PUT
        );
    }

    public void show() {
        send("Global Values:");
        for (Map.Entry<String, Object> entry : GLOBAL_VALUES.entrySet()) {
            send(entry.getKey() + " = " + entry.getValue());
        }
    }

    public Object get(PassedBy allPassed) {
        return GLOBAL_VALUES.get(
            allPassed.getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public Set<Map.Entry<String, Object>> getAll() {
        return GLOBAL_VALUES.entrySet();
    }

    public void put(PassedBy allPassed) {
        GLOBAL_VALUES.put(
            allPassed.getValueStringOrThrow(PARAM_PUT_NAME), 
            allPassed.getValueAny(PARAM_PUT_VALUE)
        );
    }

    public void del(PassedBy allPassed) {
        GLOBAL_VALUES.remove(
            allPassed.getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public void clear() {
        GLOBAL_VALUES.clear();
    }

}
