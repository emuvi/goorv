package br.com.pointel.goorv.service.archive;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;
import br.com.pointel.goorv.service.wizard.WizBase;

public class FolderMirror {

    private final File origin;
    private final String originPath;
    private final List<String> exclusions;
    private final File destiny;
    private final Boolean clean;
    private final Integer speed;
    private final Deque<File> founds;
    private final List<File> destinyFounds;
    private final AtomicBoolean doneFinder;
    private final AtomicInteger doneLoader;
    private final AtomicBoolean doneClean;
    private final List<Consumer<String>> observers;

    public FolderMirror(File origin, String exclusions, File destiny, Boolean clean, Integer speed) {
        this.origin = origin;
        this.originPath = origin.getAbsolutePath();
        this.exclusions = Arrays.asList(exclusions.split("\\;")).stream()
                .map(e -> e.replace(".", "").toLowerCase())
                .collect(Collectors.toList());
        this.destiny = destiny;
        this.clean = clean;
        this.speed = speed;
        this.founds = new ConcurrentLinkedDeque<>();
        this.destinyFounds = Collections.synchronizedList(new ArrayList<>());
        this.doneFinder = new AtomicBoolean(false);
        this.doneLoader = new AtomicInteger(0);
        this.doneClean = new AtomicBoolean(false);
        this.observers = new ArrayList<>();
    }

    public FolderMirror addObserver(Consumer<String> observer) {
        this.observers.add(observer);
        return this;
    }

    public FolderMirror start() {
        new Thread(() -> find(), "FolderMirror - Finder").start();
        for (int i = 1; i <= speed; i++) {
            new Thread(() -> load(), "FolderMirror - Loader " + i).start();
        }
        if (this.clean) {
            new Thread(() -> clean(), "FolderMirror - Clean").start();
        } else {
            doneClean.set(true);
        }
        return this;
    }

    public boolean isDoneLoad() {
        return doneFinder.get() && doneLoader.get() == speed;
    }

    public boolean isDoneClean() {
        return doneClean.get();
    }

    public boolean isDone() {
        return isDoneLoad() && isDoneClean();
    }

    private void send(String message) {
        for (var observer : observers) {
            observer.accept(message);
        }
    }

    private void find() {
        findToLoad(origin);
        doneFinder.set(true);
    }

    private void findToLoad(File path) {
        if (path.isDirectory()) {
            for (var inside : path.listFiles()) {
                findToLoad(inside);
            }
        } else {
            var extension = FilenameUtils.getExtension(path.getName()).replace(".", "").toLowerCase();
            var excluded = !extension.isBlank() && exclusions.contains(extension);
            if (!excluded) {
                founds.add(path);
            }
            send("Found " + (excluded ? " but excluded " : "") + path.getAbsolutePath());
        }
    }

    private void load() {
        try {
            while (!doneFinder.get() || !founds.isEmpty()) {
                var found = founds.pollFirst();
                if (found == null) {
                    continue;
                }
                var relative = getRelative(found);
                destinyFounds.add(relative);
                var shouldCopy = !relative.exists() || relative.length() != found.length();
                if (shouldCopy) {
                    try {
                        send("Started copying: " + relative.getAbsolutePath());
                        relative.getParentFile().mkdirs();
                        Files.copy(found.toPath(), relative.toPath(),
                                StandardCopyOption.COPY_ATTRIBUTES,
                                StandardCopyOption.REPLACE_EXISTING);
                        send("Finished copying: " + relative.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                        send("Error on copy: " + e.getMessage());
                    }
                } else {
                    send("No need to copy: " + relative.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            send("Error on load: " + e.getMessage());
        } finally {
            doneLoader.incrementAndGet();
        }
    }

    private void clean() {
        try {
            while (!isDoneLoad()) {
                WizBase.sleep(100);
            }
            findFilesToClean(destiny);
            findFoldersToClean(destiny);
            send("Finished to clean: " + destiny.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            send("Error on clean: " + e.getMessage());
        } finally {
            doneClean.set(true);
        }
    }

    private void findFilesToClean(File path) {
        if (path.isDirectory()) {
            for (var inside : path.listFiles()) {
                findFilesToClean(inside);
            }
        } else {
            if (destinyFounds.contains(path)) {
                send("Keep from clean: " + path.getAbsolutePath());
            } else {
                send("Remove on clean: " + path.getAbsolutePath());
                path.delete();
            }
        }
    }

    private void findFoldersToClean(File path) {
        if (path.isDirectory()) {
            for (var inside : path.listFiles()) {
                findFoldersToClean(inside);
            }
            path.delete();
        }
    }

    private File getRelative(File ofOrigin) {
        return new File(destiny,
                ofOrigin.getAbsolutePath().substring(originPath.length()));
    }
}
