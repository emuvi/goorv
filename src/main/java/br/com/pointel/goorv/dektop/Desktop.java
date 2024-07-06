package br.com.pointel.goorv.dektop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.formdev.flatlaf.FlatDarculaLaf;
import br.com.pointel.goorv.Context;
import br.com.pointel.goorv.service.wizard.WizAct;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class Desktop extends JFrame {

    private static final Logger log = LoggerFactory.getLogger(Desktop.class);
    public static final Border borderBody = BorderFactory.createEmptyBorder(8, 8, 8, 8);

    private final JPanel panelTop = new JPanel(new BorderLayout(8, 8));
    private final JButton buttonMe = new JButton("Me");
    private final JTextArea textPrompt = new JTextArea(1, 27);
    private final JScrollPane scrollPrompt = new JScrollPane(textPrompt);
    private final JButton buttonGo = new JButton("Go");
    private final JTextArea textView = new JTextArea(1, 27);
    private final JScrollPane scrollView = new JScrollPane(textView);
    private final JSplitPane splitBody = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelTop, scrollView);

    private final JPopupMenu popupMenu = new JPopupMenu();
    private final JMenuItem menuExit = new JMenuItem("Exit");

    public Desktop() {
        setTitle("Goorv");
        setSize(180, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(splitBody);
        splitBody.setBorder(borderBody);
        panelTop.add(buttonMe, BorderLayout.WEST);
        panelTop.add(scrollPrompt, BorderLayout.CENTER);
        panelTop.add(buttonGo, BorderLayout.EAST);
        buttonMe.addActionListener(e -> launchMenu());
        buttonGo.addActionListener(e -> launchPrompt());
        textPrompt.setLineWrap(true);
        textPrompt.setWrapStyleWord(true);
        textView.setEditable(false);
        WizSwing.initFrame(this);
    }

    private void launchMenu() {
        textView.setText("Me");
    }

    private void launchPrompt() {
        var prompt = textPrompt.getText();
        var context = new Context(glyphing -> SwingUtilities.invokeLater(() -> textView.append(glyphing.toString())));
        new Thread(() -> WizAct.execute(context, prompt)).start();   
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
