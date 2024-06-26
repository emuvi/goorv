package br.com.pointel.goorv.service.document;

import java.io.File;
import org.apache.commons.io.FilenameUtils;
import br.com.pointel.goorv.service.wizard.WizArray;

public class DockReaderPDFUtils {

    public static String[] PDF_EXTENSIONS = new String[]{"pdf", "fdf", "xfdf", "pdx", "ppdf"};

    public static boolean isPDFFile(File file) {
        return isPDFFile(file.getName());
    }

    public static boolean isPDFFile(String fileName) {
        return WizArray.contains(FilenameUtils.getExtension(fileName).toLowerCase(), PDF_EXTENSIONS);
    }
    
}
