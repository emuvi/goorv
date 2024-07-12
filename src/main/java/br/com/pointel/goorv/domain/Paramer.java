package br.com.pointel.goorv.domain;

import java.util.List;

public class Paramer {
    
    private final Order order;
    private final Param[] params;
    private final PassedBy passedBy;
    
    public Paramer(Order order, PassedBy passedBy) {
        this.order = order;
        this.params = order.params();
        this.passedBy = passedBy;
    }

    public void parse(List<Token> arguments) {
        if (arguments.isEmpty()) {
            return;
        }
        var isNamed = false;
        var index = 0;
        var param = params[index];
        while (!arguments.isEmpty()) {
            var argument = arguments.remove(0);
            if (argument.isParamName()) {
                isNamed = true;
                param = order.getParamOrThrow(argument.getChars());
            } else {
                passedBy.add(param, argument);
                if (!isNamed && index < this.params.length - 1) {
                    param = this.params[++index];
                }
            }
        }
    }

}
