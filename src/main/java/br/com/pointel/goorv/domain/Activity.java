package br.com.pointel.goorv.domain;

public abstract class Activity {

    protected final String name;
    protected final Acting acting;
    protected final String brief;
    protected final Order singleOrder;
    protected final Order[] multipleOrders;

    protected Activity(Acting acting, String brief, Order singleOrder) {
        this.name = this.getClass().getSimpleName();
        this.acting = acting;
        this.brief = brief;
        this.singleOrder = singleOrder;
        this.multipleOrders = null;
    }

    protected Activity(Acting acting, String brief, Order... multipleOrders) {
        this.name = this.getClass().getSimpleName();
        this.acting = acting;
        this.brief = brief;
        this.singleOrder = null;
        this.multipleOrders = multipleOrders;
    }

    public Acting acting() {
        return this.acting;
    }

    public String name() {
        return this.name;
    }

    public String brief() {
        return this.brief;
    }

    public boolean isSingleOrder() {
        return this.singleOrder != null;
    }

    public Order getOrder() {
        return this.singleOrder;
    }

    public Order[] getOrders() {
        return this.multipleOrders;
    }

    public Order getOrder(String name) {
        for (Order order : this.multipleOrders) {
            if (order.name().equals(name)) {
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
        var method = this.getClass().getMethod(order.name());
        var result = method.invoke(this);
        return new Typed(method.getReturnType(), result);
    }
    
    public Typed call(Order order, PassedBy allPassed) throws Exception {
        var method = this.getClass().getMethod(order.name(), PassedBy.class);
        var result = method.invoke(this, allPassed);
        return new Typed(method.getReturnType(), result);
    }

    public void send(Object value) {
        acting.context().send(new Glyphing(value));
    }

    public void send(Exception e) {
        acting.context().send(new Glyphing(e));
    }

}

