package br.com.pointel.goorv.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import br.com.pointel.goorv.Activity;
import br.com.pointel.goorv.Glyphing;
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

    private static final Map<String, String> GLOBAL_STATUS = new HashMap<>();

    public GlobalValues(Consumer<Glyphing> consumer) {
        super(consumer,"Keeps global values.", 
            ORDER_SHOW, ORDER_GET, ORDER_PUT
        );
    }

    public void show() {
        send("Global Values:");
        for (Map.Entry<String, String> entry : GLOBAL_STATUS.entrySet()) {
            send(entry.getKey() + " = " + entry.getValue());
        }
    }

    public void get(PassedBy allPassed) {
        send(GLOBAL_STATUS.get(allPassed.getGlyphed(PARAM_GET_NAME)));
    }

    public void put(PassedBy allPassed) {
        GLOBAL_STATUS.put(allPassed.getGlyphed(PARAM_PUT_NAME), allPassed.getGlyphed(PARAM_PUT_VALUE));
    }

}
