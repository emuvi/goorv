package br.com.pointel.goorv.dektop;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.formdev.flatlaf.FlatDarculaLaf;

public class Main extends JFrame {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public Main() {
        setTitle("Goorv");
        setSize(200, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new DropBox(files -> {
            for (var file : files) {
                log.info("File dropped: {}", file);
            }
        }));
    }

    public static void start(String[] args) {
        log.info("Starting Goorv");
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            log.error("Failed to initialize FlatDarculaLaf", e);
        }
        EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

}
