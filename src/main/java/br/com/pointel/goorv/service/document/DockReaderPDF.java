package br.com.pointel.goorv.service.document;

import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;

public class DockReaderPDF {
    
    public static boolean canRead(File file) {
        return DockReaderPDFUtils.isPDFFile(file);
    }
    
    private final File file;

    public DockReaderPDF(File file) {
        this.file = file;
    }

    public String read() throws Exception {
        try (var doc = Loader.loadPDF(file)) {
            var stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(doc.getNumberOfPages());
            return stripper.getText(doc);
        }
    }
}
