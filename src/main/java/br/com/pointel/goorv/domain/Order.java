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

    public String getName() {
        return this.name;
    }

    public String getBrief() {
        return this.brief;
    }

    public Param[] getParams() {
        return this.params;
    }

    public Param getParam(String name) {
        for (Param param : this.params) {
            if (param.getName().equals(name)) {
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
