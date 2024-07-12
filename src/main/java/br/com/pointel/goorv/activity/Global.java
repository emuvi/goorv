package br.com.pointel.goorv.activity;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import br.com.pointel.goorv.domain.Acting;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;

public class Global extends Activity {

    public static final Order ORDER_SHOW = new Order("show", "Show all values.");

    public static final Param PARAM_GET_NAME = new Param("name", "Name of the value.");
    public static final Order ORDER_GET = new Order("get", "Get the value of the [name].", PARAM_GET_NAME);

    public static final Param PARAM_SET_NAME = new Param("name", "Name of the value.");
    public static final Param PARAM_SET_VALUE = new Param("value", "Description of the value.");
    public static final Order ORDER_SET = new Order("set", "Put the [value] on the [name].", PARAM_SET_NAME, PARAM_SET_VALUE);

    private static final Map<String, Object> GLOBAL_VALUES = new ConcurrentHashMap<>();

    public Global(Acting acting) {
        super(acting,"Global values functionality.", ORDER_SHOW, ORDER_GET, ORDER_SET);
    }

    public void show() {
        send("Global Values:");
        for (Map.Entry<String, Object> entry : GLOBAL_VALUES.entrySet()) {
            send(entry.getKey() + " = " + entry.getValue());
        }
    }

    public Object get() {
        return GLOBAL_VALUES.get(
            acting.passedBy().getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public Set<Map.Entry<String, Object>> getAll() {
        return GLOBAL_VALUES.entrySet();
    }

    public void set() {
        GLOBAL_VALUES.put(
            acting.passedBy().getValueStringOrThrow(PARAM_SET_NAME), 
            acting.passedBy().getValue(PARAM_SET_VALUE, Object.class)
        );
    }

    public void del() {
        GLOBAL_VALUES.remove(
            acting.passedBy().getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public void clear() {
        GLOBAL_VALUES.clear();
    }

}
