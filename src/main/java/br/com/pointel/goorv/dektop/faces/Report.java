package br.com.pointel.goorv.dektop.faces;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class Report extends JFrame {
    
    private final JPanel panel = new JPanel(new BorderLayout(8, 8));
    private final Border border = BorderFactory.createEmptyBorder(8, 8, 8, 8);
    private final JTextArea textArea = new JTextArea();
    private final JScrollPane scrollPane = new JScrollPane(textArea);

    public Report(String title) {
        setTitle("Report " + title);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panel);
        panel.add(scrollPane, BorderLayout.CENTER);
        textArea.setBorder(border);
        textArea.setEditable(false);
        WizSwing.initFrame(this);
    }

    public Report setContent(String content) {
        textArea.setText(content);
        return this;
    }

    public Report showReport() {
        setVisible(true);
        return this;
    }

    public Report append(String content) {
        textArea.append(content);
        return this;
    }

}
