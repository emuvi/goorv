package br.com.pointel.goorv.dektop;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.formdev.flatlaf.FlatDarculaLaf;
import br.com.pointel.goorv.service.Questioner;

public class Desktop extends JFrame {

    private static Logger log = LoggerFactory.getLogger(Desktop.class);

    private final Questioner questioner = new Questioner();

    public Desktop() {
        setTitle("Goorv");
        setSize(180, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new DropBox(files -> {
            for (var file : files) {
                questioner.launch(file);
            }
        }));
    }

    public static void start(String[] args) {
        log.info("Starting Desktop");
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            log.error("Failed to initialize FlatDarculaLaf", e);
        }
        EventQueue.invokeLater(() -> {
            new Desktop().setVisible(true);
        });
    }

}
