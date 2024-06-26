package br.com.pointel.goorv.service.document;

import java.io.File;
import org.apache.commons.io.FilenameUtils;
import br.com.pointel.goorv.service.wizard.WizArray;

public class DockReaderMSOUtils {

    public static String[] MSPOWERPOINT_EXTENSIONS = new String[]{"ppt", "pptx"};
    public static String[] MSEXCEL_EXTENSIONS = new String[]{"xls", "xlsx"};
    public static String[] MSWORD_EXTENSIONS = new String[]{"doc", "docx"};

    public static boolean isMSWordFile(File file) {
        return isMSWordFile(file.getName());
    }

    public static boolean isMSWordFile(String fileName) {
        return WizArray.contains(FilenameUtils.getExtension(fileName).toLowerCase(), MSWORD_EXTENSIONS);
    }

    public static boolean isMSPowerPointFile(File file) {
        return isMSPowerPointFile(file.getName());
    }

    public static boolean isMSPowerPointFile(String fileName) {
        return WizArray.contains(FilenameUtils.getExtension(fileName).toLowerCase(), MSPOWERPOINT_EXTENSIONS);
    }

    public static boolean isMSExcelFile(File file) {
        return isMSExcelFile(file.getName());
    }

    public static boolean isMSExcelFile(String fileName) {
        return WizArray.contains(FilenameUtils.getExtension(fileName).toLowerCase(), MSEXCEL_EXTENSIONS);
    }
    
}
