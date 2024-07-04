package br.com.pointel.goorv;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import br.com.pointel.goorv.service.wizard.WizBase;

public class Context {

    private volatile boolean pause;
    private volatile boolean stop;
    private final Consumer<Glyphing> consumer;

    private final Map<String, Object> localValues = new HashMap<>();

    public Context(Consumer<Glyphing> consumer) {
        this.pause = false;
        this.stop = false;
        this.consumer = consumer;
    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;
    }

    public void stop() {
        stop = true;
    }

    public boolean isToPause() {
        return pause;
    }

    public boolean isToStop() {
        return stop;
    }

    public boolean doPauseAndCheckStop() {
        while (pause) {
            WizBase.sleep(10);
        }
        return stop;
    }

    public void send(Glyphing glyphing) {
        consumer.accept(glyphing);
    }

    public void putValue(String name, Object value) {
        localValues.put(name, value);
    }

    public Object getValue(String name) {
        return localValues.get(name);
    }

    public void delValue(String name) {
        localValues.remove(name);
    }

    public void clearValues() {
        localValues.clear();
    }

    public Set<Map.Entry<String, Object>> getValues() {
        return localValues.entrySet();
    }

}
