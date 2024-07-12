package br.com.pointel.goorv.domain;

import java.util.List;
import br.com.pointel.goorv.service.wizard.WizChars;

public class Parsing {
    
    private final Order order;
    private final Param[] params;

    private boolean isNamed = false;
    private int paramIndex = 0;
    private Param actualParam = null;
    private PassedBy result = null;

    public Parsing(Order order) {
        this.order = order;
        this.params = order.params();
    }

    public PassedBy parse(List<ValuedAs> arguments) {
        isNamed = false;
        paramIndex = 0;
        actualParam = params[paramIndex];
        result = new PassedBy();
        while (!arguments.isEmpty()) {
            var argument = arguments.remove(0);
            if (argument.hasString()) {
                parseArgumentString(argument.getString());
            } else {
                result.add(actualParam, argument);
            }
        }
        return result;
    }

    private void parseArgumentString(String argumentString) {
        if (isNamed) {
            parseArgumentStringOnNamed(argumentString);
        } else {
            parseArgumentStringOnIndex(argumentString);
        }
    }

    private void parseArgumentStringOnNamed(String argumentString) {
        if (argumentString.startsWith("-")) {
            actualParam = order.getParamOrThrow(argumentString);
        } else {
            result.add(actualParam, new ValuedAs(WizChars.removeQuotes(argumentString)));
        }
    }

    private void parseArgumentStringOnIndex(String argumentString) {
        if (argumentString.startsWith("-")) {
            isNamed = true;
            actualParam = order.getParamOrThrow(argumentString);
        } else {
            result.add(actualParam, new ValuedAs(WizChars.removeQuotes(argumentString)));
            if (paramIndex < this.params.length - 1) {
                paramIndex++;
            }
            actualParam = this.params[paramIndex];
        }
    }

}
