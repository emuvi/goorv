package br.com.pointel.goorv.domain;

public class Acting {

    private final Runner runner;
    private final Context context;
    private final PassedBy passedBy;

    public Acting(Runner runner, Context context, PassedBy passedBy) {
        this.runner = runner;
        this.context = context;
        this.passedBy = passedBy;
    }

    public Runner runner() {
        return this.runner;
    }

    public Context context() {
        return this.context;
    }

    public PassedBy passedBy() {
        return this.passedBy;
    }

}
