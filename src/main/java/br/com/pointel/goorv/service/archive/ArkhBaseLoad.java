package br.com.pointel.goorv.service.archive;

import java.io.File;
import java.io.FileInputStream;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.codec.digest.DigestUtils;
import br.com.pointel.goorv.service.wizard.WizApp;

/**
 *
 * @author emuvi
 */
public class ArkhBaseLoad {

    private final ArkhBase arkhBase;

    private final Deque<File> files;

    private final AtomicBoolean shouldStop;
    private final AtomicBoolean doneLoadFiles;
    private final AtomicInteger doneLoadVerifiers;
    private final AtomicBoolean doneLinterClean;

    public final AtomicInteger statusProgressPos;
    public final AtomicInteger statusProgressMax;

    public final AtomicInteger statusNumberOfFiles;
    public final AtomicInteger statusNumberOfChecked;
    public final AtomicInteger statusNumberOfCleaned;
    public final AtomicInteger statusNumberOfErros;

    public ArkhBaseLoad(ArkhBase arkhBase) throws Exception {
        this.arkhBase = arkhBase;
        this.files = new ConcurrentLinkedDeque<>();
        this.shouldStop = new AtomicBoolean(false);
        this.doneLoadFiles = new AtomicBoolean(false);
        this.doneLoadVerifiers = new AtomicInteger(0);
        this.doneLinterClean = new AtomicBoolean(false);
        this.statusProgressPos = new AtomicInteger(0);
        this.statusProgressMax = new AtomicInteger(0);
        this.statusNumberOfFiles = new AtomicInteger(0);
        this.statusNumberOfChecked = new AtomicInteger(0);
        this.statusNumberOfCleaned = new AtomicInteger(0);
        this.statusNumberOfErros = new AtomicInteger(0);
    }

    public ArkhBaseLoad start() {
        new Thread("ArkhBaseLoad - Files") {
            @Override
            public void run() {
                loadFiles(arkhBase.root);
                doneLoadFiles.set(true);
            }
        }.start();
        for (int i = 1; i <= THREADS_VERIFIERS; i++) {
            new Thread("ArkhBaseLoad - Verifier " + i) {
                @Override
                public void run() {
                    loadVerifiers();
                    doneLoadVerifiers.incrementAndGet();
                }
            }.start();
        }
        new Thread("ArkhBaseLoad - Linter") {
            @Override
            public void run() {
                makeLinterClean();
                doneLinterClean.set(true);
            }
        }.start();
        return this;
    }

    public void stop() {
        shouldStop.set(true);
        while (!isDone()) {
            WizApp.sleep(10);
        }
    }

    public Boolean isDone() {
        return doneLoadFiles.get()
                && isDoneVerifiers()
                && doneLinterClean.get();
    }

    private boolean isDoneVerifiers() {
        return doneLoadVerifiers.get() == THREADS_VERIFIERS;
    }

    public Double getProgress() {
        return ((double) statusProgressPos.get()) / ((double) statusProgressMax.get()) * 100.0;
    }

    public String getProgressFormated() {
        return String.format("%.2f%%", getProgress());
    }

    private void loadFiles(File path) {
        if (shouldStop.get()) {
            return;
        }
        if (path.isFile()) {
            if (!(path.getName().startsWith("arkh") && path.getName().endsWith(".sdb"))) {
                files.addLast(path);
                this.statusProgressMax.incrementAndGet();
                this.statusNumberOfFiles.incrementAndGet();
            }
        } else if (path.isDirectory()) {
            for (var inside : path.listFiles()) {
                loadFiles(inside);
            }
        }
    }

    private void loadVerifiers() {
        while (true) {
            if (shouldStop.get()) {
                break;
            }
            var file = files.pollFirst();
            if (file == null) {
                if (doneLoadFiles.get()) {
                    break;
                } else {
                    WizApp.sleep(100);
                    continue;
                }
            }
            try {
                arkhBase.sendToListeners("[BASE] Verifing: " + file.getName());
                var place = arkhBase.getPlace(file);
                var baseFile = arkhBase.baseData.getByPlace(place);
                if (baseFile == null || file.lastModified() > baseFile.modified) {
                    try (FileInputStream input = new FileInputStream(file)) {
                        var verifier = DigestUtils.sha256Hex(input);
                        arkhBase.baseData.putFile(place, file.lastModified(), verifier);
                        arkhBase.sendToListeners("[BASE] Putted: " + file.getName());
                    }
                }
                this.statusNumberOfChecked.incrementAndGet();
            } catch (Exception e) {
                e.printStackTrace();
                arkhBase.sendToListeners("[BASE] Error: " + e.getMessage());
                statusNumberOfErros.incrementAndGet();
            } finally {
                this.statusProgressPos.incrementAndGet();
            }
        }
    }

    private void makeLinterClean() {
        try {
            while (!isDoneVerifiers()) {
                WizApp.sleep(100);
                if (shouldStop.get()) {
                    return;
                }
            }
            var places = arkhBase.baseData.getAllPlaces();
            statusProgressMax.addAndGet(places.size());
            for (var place : places) {
                if (this.shouldStop.get()) {
                    return;
                }
                try {
                    var file = new File(arkhBase.root, place);
                    if (!file.exists()) {
                        arkhBase.sendToListeners("[BASE] Cleaning: " + place);
                        arkhBase.baseData.delFile(place);
                        statusNumberOfCleaned.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    arkhBase.sendToListeners("[BASE] Error: " + e.getMessage());
                    statusNumberOfErros.incrementAndGet();
                } finally {
                    statusProgressPos.incrementAndGet();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            arkhBase.sendToListeners("[BASE] Error: " + e.getMessage());
            statusNumberOfErros.incrementAndGet();
        }
    }

    private static final Integer THREADS_VERIFIERS = 8;

}
