package br.com.pointel.goorv.service.document;

import java.io.File;
import org.apache.commons.io.FilenameUtils;
import br.com.pointel.goorv.service.wizard.WizArray;

public class DockReaderTXTUtils {

    public static String[] TXT_EXTENSIONS = new String[]{"txt", "md", "htm", "html", "log"};

    public static boolean isTXTFile(File file) {
        return isTXTFile(file.getName());
    }

    public static boolean isTXTFile(String fileName) {
        return WizArray.contains(FilenameUtils.getExtension(fileName).toLowerCase(), TXT_EXTENSIONS);
    }
    
}
