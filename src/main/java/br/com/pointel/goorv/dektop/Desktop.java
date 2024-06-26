package br.com.pointel.goorv.dektop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import br.com.pointel.goorv.dektop.faces.Report;
import br.com.pointel.goorv.service.wizard.WizApp;
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
        try {
            var retorno = WizApp.execute(prompt.getText());
            if (retorno != null) {
                new Report("Prompt Execution")
                    .setContent(retorno).showReport();
            }
        } catch (Exception e) {
            WizSwing.showError(e);
        }
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
