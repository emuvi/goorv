package br.com.pointel.goorv;

public class Passed {
    
    private final Param param;
    private final ValuedAs valued;

    public Passed(Param param, ValuedAs valued) {
        this.param = param;
        this.valued = valued;
    }

    public Param getParam() {
        return this.param;
    }

    public ValuedAs getValued() {
        return this.valued;
    }

    

}
