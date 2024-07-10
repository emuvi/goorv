package br.com.pointel.goorv.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Activity {

    public static final Order ORDER_HELP = new Order("help", "Show help for this activity.");

    protected final Context context;
    protected final String name;
    protected final String brief;
    protected final List<Order> orders;

    protected Activity(Context context, String brief, Order... orders) {
        this.context = context;
        this.name = this.getClass().getSimpleName();
        this.brief = brief;
        this.orders = new ArrayList<>(Arrays.asList(orders));
        this.orders.add(ORDER_HELP);
    }

    public void help() {
        send(this.name);
        send(this.brief);
        for (Order order : this.orders) {
            send(order.getName());
            send(order.getBrief());
            for (Param param : order.getParams()) {
                send(param.getName());
                send(param.getBrief());
            }
        }
    }

    public Order getOrder(String name) {
        for (Order order : this.orders) {
            if (order.getName().equals(name)) {
                return order;
            }
        }
        return null;
    }

    public Order getOrderOrThrow(String name) {
        var order = this.getOrder(name);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + name);
        }
        return order;
    }

    public Typed call(Order order) throws Exception {
        var method = this.getClass().getMethod(order.getName());
        var result = method.invoke(this);
        return new Typed(method.getReturnType(), result);
    }
    
    public Typed call(Order order, PassedBy allPassed) throws Exception {
        var method = this.getClass().getMethod(order.getName(), PassedBy.class);
        var result = method.invoke(this, allPassed);
        return new Typed(method.getReturnType(), result);
    }

    public void send(String glyphed) {
        context.send(new Glyphing(glyphed));
    }

    public void send(Exception e) {
        context.send(new Glyphing(e));
    }

}

