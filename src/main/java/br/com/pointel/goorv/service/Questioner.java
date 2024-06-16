package br.com.pointel.goorv.service;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Questioner {

    private static Logger log = LoggerFactory.getLogger(Questioner.class);

    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    public void launch(File file) {
        executor.execute(() -> question(file));
    }

    private void question(File file) {
        try {
            log.info("Questioning file: " + file);
            try (var doc = Loader.loadPDF(file)) {
                var stripper = new PDFTextStripper();
                stripper.setStartPage(1);
                stripper.setEndPage(10);
                var source = stripper.getText(doc);
                System.out.println(source);
            }
        } catch (Exception e) {
            log.error("Failed to question file: " + file, e);
        }
    }

}
