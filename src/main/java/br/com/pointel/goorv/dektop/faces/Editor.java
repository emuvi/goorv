package br.com.pointel.goorv.dektop.faces;

import java.awt.event.ActionEvent;
import java.nio.file.Files;
import br.com.pointel.goorv.dektop.pieces.GAct;
import br.com.pointel.goorv.dektop.pieces.GBox;
import br.com.pointel.goorv.dektop.pieces.GBoxBorder;
import br.com.pointel.goorv.dektop.pieces.GBoxLine;
import br.com.pointel.goorv.dektop.pieces.GFrame;
import br.com.pointel.goorv.dektop.pieces.GScroll;
import br.com.pointel.goorv.dektop.pieces.GText;
import br.com.pointel.goorv.domain.SourceFile;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class Editor extends GFrame {

    private final Desk desk;
    private final SourceFile source;

    private final GAct readAct = new GAct("Read").putShort('E').putAct(this::actRead);
    private final GAct writeAct = new GAct("Write").putShort('W').putAct(this::actWrite);
    private final GAct ditchAct = new GAct("Ditch").putShort('D').putAct(this::actDitch);
    private final GAct scanAct = new GAct("Scan").putShort('S').putAct(this::actScan);
    private final GAct runAct = new GAct("Run").putShort('R').putAct(this::actRun);
    private final GBox sourceTools = new GBoxLine().putAll(readAct, writeAct, ditchAct, scanAct, runAct);

    private final GText sourceText = new GText(30, 80).putWrap();
    private final GScroll sourceScroll = new GScroll(sourceText);

    private final GBox bodyBox = new GBoxBorder().putCenter(sourceScroll).putNorth(sourceTools).putBorder(4);

    public Editor(Desk desk, SourceFile source) {
        super(source.getName());
        this.desk = desk;
        this.source = source;
        setContentPane(bodyBox);
        initSource();
    }

    private void initSource() {
        try {
            sourceText.setText(source.getText());
        } catch (Exception e) {
            WizSwing.showError("Could not read the source file.", e);
        }
    }

    private void actRead(ActionEvent event) {
        try {
            sourceText.setText(source.getText());
        } catch (Exception e) {
            WizSwing.showError("Could not read the source file.", e);
        }
    }

    private void actWrite(ActionEvent event) {
        try {
            source.setText(sourceText.getText());
        } catch (Exception e) {
            WizSwing.showError("Could not write the source file.", e);
        }
    }

    private void actDitch(ActionEvent event) {
        if (source instanceof SourceFile sourceFile) {
            if (WizSwing.showConfirm("Do you really want to delete this source?")) {
                try {
                    Files.delete(sourceFile.getFile().toPath());
                    desk.updateSourceList();
                    close();
                } catch (Exception e) {
                    WizSwing.showError("Could not delete the source file.", e);
                }
            }    
        } else {
            WizSwing.showInfo("This is not a source file to be deleted.");
        }
    }

    private void actScan(ActionEvent event) {}

    private void actRun(ActionEvent event) {}

}
