package br.com.pointel.goorv.service.document;

import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import br.com.pointel.goorv.service.wizard.WizImage;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class MakePDFInFolder {

    private final File folder;

    public MakePDFInFolder(File folder) {
        this.folder = folder;
    }

    public File make() throws Exception {
        try (PDDocument document = new PDDocument()) {
            var hasPages = false;
            for (var inside : folder.listFiles()) {
                if (WizImage.isImage(inside)) {
                    try {
                        addPage(document, inside);
                        hasPages = true;
                    } catch (Exception e) {
                        WizSwing.showError(e, "Could not add from file: " + inside.getName());
                    }
                }
            }
            if (hasPages) {
                var destiny = new File(folder, "document.pdf");
                destiny.delete();
                document.save(destiny);
                return destiny;
            }
        }
        return null;
    }

    private void addPage(PDDocument inDocument, File fromImageFile) throws Exception {
        PDImageXObject imageItem = PDImageXObject.createFromFileByExtension(fromImageFile, inDocument);
        PDPage newPage = new PDPage(new PDRectangle(imageItem.getWidth(), imageItem.getHeight()));
        inDocument.addPage(newPage);
        try (PDPageContentStream contents = new PDPageContentStream(inDocument, newPage)) {
            contents.drawImage(imageItem, 0, 0);
        }
    }

}
