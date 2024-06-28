package br.com.pointel.goorv.dektop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.formdev.flatlaf.FlatDarculaLaf;
import br.com.pointel.goorv.Glyphing;
import br.com.pointel.goorv.dektop.faces.Report;
import br.com.pointel.goorv.service.wizard.WizAct;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class Desktop extends JFrame {

    private static final Logger log = LoggerFactory.getLogger(Desktop.class);

    private final JPanel panel = new JPanel(new BorderLayout(8, 8));
    private final Border border = BorderFactory.createEmptyBorder(8, 8, 8, 8);
    private final JTextField prompt = new JTextField(27);
    private final JButton button = new JButton("Go");

    public Desktop() {
        setTitle("Goorv");
        setSize(180, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        getRootPane().setDefaultButton(button);
        panel.setBorder(border);
        panel.add(prompt, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);
        button.addActionListener(e -> go());
        WizSwing.initFrame(this);
    }

    private void go() {
        var report = new Report("Prompt Execution").showReport();
        var consumer = (Consumer<Glyphing>) glyphing -> {
            if (glyphing.hasGlyphed()) {
                report.println(glyphing.getGlyphed());
            } else if (glyphing.hasError()) {
                report.println(glyphing.getError().getMessage());
                report.println(glyphing.getErrorStack());
            }
        };
        new Thread(() -> {
            try {
                WizAct.execute(consumer, prompt.getText());
            } catch (Exception e) {
                consumer.accept(new Glyphing(e));
            } finally {
                consumer.accept(new Glyphing("End of execution"));
            }
        }).start();
    }

    public static void start(String[] args) {
        log.info("Starting Desktop");
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            log.error("Failed to initialize FlatDarculaLaf", e);
        }
        EventQueue.invokeLater(() -> new Desktop().setVisible(true));
    }

}
