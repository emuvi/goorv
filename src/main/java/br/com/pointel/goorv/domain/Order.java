package br.com.pointel.goorv.domain;

public class Order {
    
    private final String name;
    private final String brief;
    private final Param[] params;

    public Order(String name, String brief, Param... params) {
        this.name = name;
        this.brief = brief;
        this.params = params != null ? params : new Param[0];
    }

    public String name() {
        return this.name;
    }

    public String brief() {
        return this.brief;
    }

    public Param[] params() {
        return this.params;
    }

    public Param getParam(String name) {
        for (Param param : this.params) {
            if (param.name().equals(name)) {
                return param;
            }
        }
        return null;
    }

    public Param getParamOrThrow(String name) {
        var param = this.getParam(name);
        if (param == null) {
            throw new IllegalArgumentException("Param not found: " + name);
        }
        return param;
    }



}
