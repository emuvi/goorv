package br.com.pointel.goorv.activity;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import br.com.pointel.goorv.domain.Acting;
import br.com.pointel.goorv.domain.Activity;
import br.com.pointel.goorv.domain.Order;
import br.com.pointel.goorv.domain.Param;
import br.com.pointel.goorv.domain.PassedBy;

public class Global extends Activity {

    public static final Order ORDER_SHOW = new Order("show", "Show all values.");

    public static final Param PARAM_GET_NAME = new Param("name", "Name of the value.");
    public static final Order ORDER_GET = new Order("get", "Get the value of the [name].", PARAM_GET_NAME);

    public static final Param PARAM_PUT_NAME = new Param("name", "Name of the value.");
    public static final Param PARAM_PUT_VALUE = new Param("value", "Description of the value.");
    public static final Order ORDER_PUT = new Order("put", "Put the [value] on the [name].", PARAM_PUT_NAME, PARAM_PUT_VALUE);

    private static final Map<String, Object> GLOBAL_VALUES = new ConcurrentHashMap<>();

    public Global(Acting acting) {
        super(acting,"Global values functionality.", ORDER_SHOW, ORDER_GET, ORDER_PUT);
    }

    public void show() {
        send("Global Values:");
        for (Map.Entry<String, Object> entry : GLOBAL_VALUES.entrySet()) {
            send(entry.getKey() + " = " + entry.getValue());
        }
    }

    public Object get(PassedBy byPassed) {
        return GLOBAL_VALUES.get(
            byPassed.getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public Set<Map.Entry<String, Object>> getAll() {
        return GLOBAL_VALUES.entrySet();
    }

    public void put(PassedBy byPassed) {
        GLOBAL_VALUES.put(
            byPassed.getValueStringOrThrow(PARAM_PUT_NAME), 
            byPassed.getValue(PARAM_PUT_VALUE, Object.class)
        );
    }

    public void del(PassedBy byPassed) {
        GLOBAL_VALUES.remove(
            byPassed.getValueStringOrThrow(PARAM_GET_NAME)
        );
    }

    public void clear() {
        GLOBAL_VALUES.clear();
    }

}
