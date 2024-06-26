package br.com.pointel.goorv.service.archive;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.codec.digest.DigestUtils;

public class ArkhBase implements Closeable {

    public final File root;
    private final int rootLength;
    
    private final List<Consumer<String>> listeners;

    public final ArkhBaseData baseData;
    public final ArkhBaseLoad baseLoad;

    public ArkhBase(File root) throws Exception {
        if (!root.exists() || !root.isDirectory()) {
            throw new Exception("Could not find the root directory.");
        }
        this.root = root;
        this.rootLength = this.root.getAbsolutePath().length();
        this.listeners = new ArrayList<>();
        this.baseData = new ArkhBaseData(this);
        this.baseLoad = new ArkhBaseLoad(this);
    }
    
    public void addListener(Consumer<String> listener) {
        listeners.add(listener);
    }
    
    public void delListener(Consumer<String> listener) {
        listeners.remove(listener);
    }
    
    public void sendToListeners(String message) {
        for (var listener : listeners) {
            listener.accept(message);
        }
    }

    public ArkhBase load() throws Exception {
        this.baseLoad.start();
        return this;
    }

    public String getPlace(File path) {
        return path.getAbsolutePath().substring(this.rootLength);
    }
    
    public String getPlaceFolder(File path) {
        var place = getPlace(path);
        return place.substring(0, place.lastIndexOf(File.separator) + 1);
    }
    
    public void putFile(File path, String verifier) throws Exception {
        this.baseData.putFile(getPlace(path), path.length(), verifier);
    }
    
    public void delFolder(File path) throws Exception {
        this.baseData.delFolder(getPlace(path));
    }
    
    public void delFile(File path) throws Exception {
        this.baseData.delFile(getPlace(path));
    }
    
    public void moveFolder(File fromPath, File toPath) throws Exception {
        this.baseData.moveFolder(getPlace(fromPath), getPlace(toPath));
    }
    
    public void moveFile(File fromPath, File toPath) throws Exception {
        this.baseData.moveFile(getPlace(fromPath), getPlace(toPath));
    }

    public String makeCheck(File path) throws Exception {
        var report = new StringBuilder();
        if (path.isDirectory()) {
            for (var inside : path.listFiles()) {
                checkFile(inside, report);
            }
        } else {
            checkFile(path, report);
        }
        return report.toString();
    }
    
    private void checkFile(File path, StringBuilder report) throws Exception {
        report.append("The file: ");
        report.append(path.getName());
        report.append("\n");
        try (FileInputStream input = new FileInputStream(path)) {
            var verifier = DigestUtils.sha256Hex(input);
            var arkhFiles = baseData.getByVerifier(verifier);
            if (arkhFiles.isEmpty()) {
                report.append("Is not in the base.\n");
            } else {
                for (var arkhFile : arkhFiles) {
                    report.append("Is in the base as: ");
                    report.append(arkhFile.place);
                    report.append("\n");
                }
            }
        }
        report.append("\n");
    }

    @Override
    public void close() throws IOException {
        this.baseLoad.stop();
        this.baseData.close();
    }

}
