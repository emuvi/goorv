package br.com.pointel.goorv.service.document;

import java.io.File;

public class DockReader {

    public static boolean canRead(File file) {
        return DockReaderPDF.canRead(file)
                || DockReaderMSO.canRead(file)
                || DockReaderTXT.canRead(file);
    }

    private final File file;

    public DockReader(File file) {
        this.file = file;
    }

    public String read() throws Exception {
        if (DockReaderPDF.canRead(file)) {
            return new DockReaderPDF(file).read();
        } else if (DockReaderMSO.canRead(file)) {
            return new DockReaderMSO(file).read();
        } else if (DockReaderTXT.canRead(file)) {
            return new DockReaderTXT(file).read();
        } else {
            throw new Exception("Can not read this file type.");
        }
    }

}
