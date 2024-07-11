package br.com.pointel.goorv.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Runner {

    private final String title;
    private final Source source;

    private final List<Glyphing> output = new ArrayList<>();
    private final Consumer<Glyphing> consumer = output::add;
    private final Context context = new Context(consumer);
    private final Thread thread = new Thread(this::run);

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
        var parsed = source.parse();
        var commands = parsed.getCommands(context);
        var index = 0f;
        var total = commands.size() * 1.0f;
        context.setProgress(index / total);
        for (Command command : commands) {
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
            index++;
            context.setProgress(index / total);
        }
    }

    public void start() {
        thread.start();
    }

    public String toString() {
        return title;
    }

}
