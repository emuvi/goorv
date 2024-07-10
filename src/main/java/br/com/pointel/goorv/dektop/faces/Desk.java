package br.com.pointel.goorv.dektop.faces;

import java.awt.event.ActionEvent;
import java.io.File;
import br.com.pointel.goorv.dektop.pieces.GAct;
import br.com.pointel.goorv.dektop.pieces.GBox;
import br.com.pointel.goorv.dektop.pieces.GBoxBorder;
import br.com.pointel.goorv.dektop.pieces.GBoxLine;
import br.com.pointel.goorv.dektop.pieces.GCombo;
import br.com.pointel.goorv.dektop.pieces.GFrame;
import br.com.pointel.goorv.dektop.pieces.GList;
import br.com.pointel.goorv.dektop.pieces.GScroll;
import br.com.pointel.goorv.dektop.pieces.GSplit;
import br.com.pointel.goorv.dektop.pieces.GText;
import br.com.pointel.goorv.domain.Runner;
import br.com.pointel.goorv.domain.Source;

public class Desk extends GFrame {
    
    private final File folder;

    private final GList<Source> sourceList = new GList<>();
    private final GScroll sourceScroll = new GScroll(sourceList);

    private final GAct newAct = new GAct("New").putAction(this::actNew);
    private final GAct editAct = new GAct("Edit").putAction(this::actEdit);
    private final GAct runAct = new GAct("Run").putAction(this::actRun);
    private final GBox sourceTools = new GBoxLine().putAll(newAct, editAct, runAct);
    private final GBox sourceBox = new GBoxBorder().putCenter(sourceScroll).putSouth(sourceTools);

    private final GText outputText = new GText().delEditable().putWrap();
    private final GScroll outputScroll = new GScroll(outputText);
    private final GCombo<Runner> runnerCombo = new GCombo<Runner>().putAction(this::actSelect);
    private final GAct resumeAct = new GAct("Resume").putAction(this::actResume);
    private final GAct pauseAct = new GAct("Pause").putAction(this::actPause);
    private final GAct stopAct = new GAct("Stop").putAction(this::actStop);
    private final GAct clearAct = new GAct("Clear").putAction(this::actClear);
    private final GBox outputTools = new GBoxLine().putAll(runnerCombo, resumeAct, pauseAct, stopAct, clearAct);
    private final GBox outputBox = new GBoxBorder().putCenter(outputScroll).putSouth(outputTools);

    private final GSplit bodySplit = new GSplit(sourceBox, outputBox, 0.3);

    private final Runner allRunner = new Runner("All");

    public Desk(File folder) {
        super("Goorv");
        this.folder = folder;
        setContentPane(bodySplit);
        initFolder();
        initRunner();
    }

    private void initFolder() {
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                sourceList.put(new Source(file));
            }
        }
    }

    private void initRunner() {
        runnerCombo.put(allRunner);
        runnerCombo.setSelectedItem(allRunner);
    }

    private void actNew(ActionEvent event) {}

    private void actEdit(ActionEvent event) {}

    private void actRun(ActionEvent event) {}

    private void actSelect(ActionEvent event) {}

    private void actResume(ActionEvent event) {}

    private void actPause(ActionEvent event) {}

    private void actStop(ActionEvent event) {}

    private void actClear(ActionEvent event) {}


}
