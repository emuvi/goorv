package br.com.pointel.goorv.dektop.faces;

import java.awt.event.ActionEvent;
import java.io.File;
import br.com.pointel.goorv.dektop.pieces.GAct;
import br.com.pointel.goorv.dektop.pieces.GBox;
import br.com.pointel.goorv.dektop.pieces.GBoxBorder;
import br.com.pointel.goorv.dektop.pieces.GBoxLine;
import br.com.pointel.goorv.dektop.pieces.GCombo;
import br.com.pointel.goorv.dektop.pieces.GField;
import br.com.pointel.goorv.dektop.pieces.GFrame;
import br.com.pointel.goorv.dektop.pieces.GList;
import br.com.pointel.goorv.dektop.pieces.GScroll;
import br.com.pointel.goorv.dektop.pieces.GSplit;
import br.com.pointel.goorv.dektop.pieces.GText;
import br.com.pointel.goorv.domain.Runner;
import br.com.pointel.goorv.domain.SourceFile;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class Desk extends GFrame {
    
    private final File folder;

    private final GList<SourceFile> sourceList = new GList<>();
    private final GScroll sourceScroll = new GScroll(sourceList);

    private final GAct newAct = new GAct("New").putAct(this::actNew);
    private final GAct editAct = new GAct("Edit").putAct(this::actEdit);
    private final GAct startAct = new GAct("Start").putAct(this::actStart);
    private final GBox sourceTools = new GBoxLine().putAll(newAct, editAct, startAct);
    private final GBox sourceBox = new GBoxBorder().putCenter(sourceScroll).putSouth(sourceTools);

    private final GField runnerField = new GField();
    private final GAct runAct = new GAct("Run").putAct(this::actRun);
    private final GBox runnerBox = new GBoxBorder().putCenter(runnerField).putEast(runAct);

    private final GText outputText = new GText(25, 50).delEditable().putWrap();
    private final GScroll outputScroll = new GScroll(outputText);
    private final GCombo<Runner> runnerCombo = new GCombo<Runner>().putAct(this::actSelect);
    private final GAct resumeAct = new GAct("Resume").putAct(this::actResume);
    private final GAct pauseAct = new GAct("Pause").putAct(this::actPause);
    private final GAct stopAct = new GAct("Stop").putAct(this::actStop);
    private final GAct clearAct = new GAct("Clear").putAct(this::actClear);
    private final GBox outputTools = new GBoxLine().putAll(runnerCombo, resumeAct, pauseAct, stopAct, clearAct);
    private final GBox outputBox = new GBoxBorder().putNorth(runnerBox).putCenter(outputScroll).putSouth(outputTools);

    private final GSplit bodySplit = new GSplit(sourceBox, outputBox, 0.3).putBorder(4);

    public Desk(File folder) {
        super("Goorv");
        this.folder = folder;
        setContentPane(bodySplit);
        initSourceList();
    }

    private void initSourceList() {
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".grv")) {
                sourceList.put(new SourceFile(file));
            }
        }
        sourceList.putAct(this::actEdit);
    }

    private void actNew(ActionEvent event) {
        new Namer(name -> {
            name = name.trim();
            if (name.isEmpty()) {
                return;
            }
            if (!name.endsWith(".grv")) {
                name = name + ".grv";
            }
            var file = new File(folder, name);
            try {
                file.createNewFile();
                var source = new SourceFile(file);
                sourceList.put(source);
                sortSorceList();
            } catch (Exception e) {
                WizSwing.showError("Could not create the source file.", e);
            }
            
        }).start();
    }

    private void actEdit(ActionEvent event) {
        var source = sourceList.getSelectedValue();
        if (source != null) {
            new Editor(source).start();
        }
    }

    private void actStart(ActionEvent event) {}

    private void actRun(ActionEvent event) {}

    private void actSelect(ActionEvent event) {}

    private void actResume(ActionEvent event) {}

    private void actPause(ActionEvent event) {}

    private void actStop(ActionEvent event) {}

    private void actClear(ActionEvent event) {}

    private void sortSorceList() {
        sourceList.sort((a, b) -> a.getName().compareTo(b.getName()));
    }

}
