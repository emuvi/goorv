package br.com.pointel.goorv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class Activity {

    public static final Order ORDER_HELP = new Order("help", "Show help for this activity.");

    protected final Consumer<Glyphing> consumer;
    protected final String name;
    protected final String brief;
    protected final List<Order> orders;

    protected Activity(Consumer<Glyphing> consumer, String brief, Order... orders) {
        this.consumer = consumer;
        this.name = this.getClass().getSimpleName();
        this.brief = brief;
        this.orders = new ArrayList<>(Arrays.asList(orders));
        this.orders.add(ORDER_HELP);
    }

    public void help() {
        consumer.accept(new Glyphing(this.name));
        consumer.accept(new Glyphing(this.brief));
        for (Order order : this.orders) {
            consumer.accept(new Glyphing(order.getName()));
            consumer.accept(new Glyphing(order.getBrief()));
            for (Param param : order.getParams()) {
                consumer.accept(new Glyphing(param.getName()));
                consumer.accept(new Glyphing(param.getBrief()));
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

    public void call(Order order) {
        try {
            var method = this.getClass().getMethod(order.getName());
            method.invoke(this);
        } catch (Exception e) {
            consumer.accept(new Glyphing(e));
        }
    }
    
    public void call(Order order, PassedBy allPassed) {
        try {
            var method = this.getClass().getMethod(order.getName(), PassedBy.class);
            method.invoke(this, allPassed);
        } catch (Exception e) {
            consumer.accept(new Glyphing(e));
        }       
    }

    public void send(String glyphed) {
        consumer.accept(new Glyphing(glyphed));
    }

    public void send(Exception e) {
        consumer.accept(new Glyphing(e));
    }

}

