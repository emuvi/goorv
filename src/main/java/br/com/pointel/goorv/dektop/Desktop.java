package br.com.pointel.goorv.dektop;

import java.awt.EventQueue;
import java.io.File;
import javax.swing.UIManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.formdev.flatlaf.FlatDarculaLaf;
import br.com.pointel.goorv.dektop.faces.Desk;

public class Desktop {

    private Desktop() {}

    private static final Logger log = LoggerFactory.getLogger(Desktop.class);

    public static void start(File folder) throws Exception {
        log.info("Starting Desk on: {}", folder.getAbsolutePath());
        UIManager.setLookAndFeel(new FlatDarculaLaf());
        EventQueue.invokeLater(() -> new Desk(folder).putExitOnClose().start());
    }

}
