package br.com.pointel.goorv;

import java.util.List;

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

    public PassedBy getPassedBy(List<String> args) {
        PassedBy paramsPassed = new PassedBy();
        var isNamed = false;
        var paramIndex = 0;
        Param actualParam = this.params[paramIndex];
        while (!args.isEmpty()) {
            var arg = args.remove(0);
            if (isNamed) {
                if (arg.startsWith("-")) {
                    actualParam = this.getParamOrThrow(arg);
                } else {
                    paramsPassed.add(actualParam, arg);
                }
            } else {
                if (arg.startsWith("-")) {
                    isNamed = true;
                    actualParam = this.getParamOrThrow(arg);
                } else {
                    paramsPassed.add(actualParam, arg);
                    if (paramIndex < this.params.length - 1) {
                        paramIndex++;
                    }
                    actualParam = this.params[paramIndex];
                }
            }
        }
        return paramsPassed;
    }

}
