package br.com.pointel.goorv.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Runner {

    private final String title;
    private final Source source;

    private final List<Output> output = new ArrayList<>();
    private final Consumer<Output> consumer = output::add;
    private final Context context = new Context(consumer);
    private final Thread thread = new Thread(this::run);

    private volatile boolean done = false;

    public Runner(String title, Source source) {
        this.title = title;
        this.source = source;
    }

    public void pause() {
        context.pause();
    }

    public void resume() {
        context.resume();
    }

    public void stop() {
        context.stop();
    }

    public boolean isPaused() {
        return context.isToPause();
    }

    public boolean isStopped() {
        return context.isToStop();
    }

    public float getProgress() {
        return context.getProgress();
    }

    private void run() {
        try {
            process();
        } catch (Exception e) {
            context.sendError(e);
        } finally {
            done = true;
        }
    }

    private void process() throws Exception {
        var commands = new Commander(source.getText(), this, context).parse();
        var index = 0f;
        var total = commands.size();
        context.setProgress(index / total);
        for (Command command : commands) {
            context.clearStack();
            while (context.isToPause()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    context.sendError(e);
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            if (context.isToStop()) {
                break;
            }
            command.run();
            context.setProgress(++index / total);
        }
        context.setProgress(1f);
    }

    public void start() {
        thread.start();
    }

    public boolean isDone() {
        return done;
    }

    public String toString() {
        return title;
    }

}
