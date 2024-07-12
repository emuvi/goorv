package br.com.pointel.goorv.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import br.com.pointel.goorv.service.wizard.WizApp;

public class Context {

    private volatile boolean pause;
    private volatile boolean stop;
    private volatile float progress;
    private final Consumer<Output> consumer;

    private final Deque<Object> stackValues = new ArrayDeque<>();
    private final Map<String, Object> localValues = new HashMap<>();

    public Context(Consumer<Output> consumer) {
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
            WizApp.sleep(10);
        }
        return stop;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void send(Output glyphing) {
        consumer.accept(glyphing);
    }

    public void sendValue(Object value) {
        consumer.accept(new Output(value));
    }

    public void sendError(Throwable error) {
        consumer.accept(new Output(error));
    }

    public boolean hasStack() {
        return !stackValues.isEmpty();
    }

    public Object popStack() {
        return stackValues.pop();
    }

    public void pushStack(Object value) {
        stackValues.push(value);
    }

    public void clearStack() {
        stackValues.clear();
    }

    public Object getValue(String name) {
        return localValues.get(name);
    }

    public void putValue(String name, Object value) {
        localValues.put(name, value);
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
