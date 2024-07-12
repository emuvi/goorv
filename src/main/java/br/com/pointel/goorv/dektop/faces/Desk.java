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
import br.com.pointel.goorv.dektop.pieces.GPace;
import br.com.pointel.goorv.dektop.pieces.GScroll;
import br.com.pointel.goorv.dektop.pieces.GSplit;
import br.com.pointel.goorv.dektop.pieces.GText;
import br.com.pointel.goorv.domain.Runner;
import br.com.pointel.goorv.domain.SourceFile;
import br.com.pointel.goorv.domain.SourceText;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class Desk extends GFrame {

    private final File folder;

    private final GField searchField = new GField();
    private final GList<SourceFile> sourceList = new GList<>();
    private final GScroll sourceScroll = new GScroll(sourceList);
    private final GAct newAct = new GAct("New").putShort('N').putAct(this::actNew);
    private final GAct editAct = new GAct("Edit").putShort('E').putAct(this::actEdit);
    private final GAct startAct = new GAct("Start").putShort('S').putAct(this::actStart);
    private final GBox sourceTools = new GBoxLine().putAll(newAct, editAct, startAct);
    private final GBox sourceBox = new GBoxBorder().putNorth(searchField).putCenter(sourceScroll).putSouth(sourceTools);

    private final GField runnerField = new GField();
    private final GAct runAct = new GAct("Run").putShort('R').putAct(this::actRun);
    private final GBox runnerBox = new GBoxBorder().putCenter(runnerField).putEast(runAct);

    private final GText outputText = new GText(25, 50).delEditable().putWrap();
    private final GScroll outputScroll = new GScroll(outputText);
    private final GCombo<Runner> runnerCombo = new GCombo<Runner>().putAct(this::actSelect);
    private final GAct pauseAct = new GAct("Pause").putShort('P').putAct(this::actPause);
    private final GAct stopAct = new GAct("Stop").putShort('T').putAct(this::actStop);
    private final GAct viewAct = new GAct("View").putShort('V').putAct(this::actView);
    private final GBox runnerTools = new GBoxLine().putAll(runnerCombo, pauseAct, stopAct,viewAct);
    private final GPace runnerPace = new GPace(0, 100).putBorder(2);
    private final GBox outputTools = new GBoxBorder().putWest(runnerTools).putCenter(runnerPace);
    private final GBox outputBox = new GBoxBorder().putNorth(runnerBox).putCenter(outputScroll).putSouth(outputTools);

    private final GSplit bodySplit = new GSplit(sourceBox, outputBox, 0.3).putBorder(4);

    public Desk(File folder) {
        super("Goorv");
        this.folder = folder;
        setContentPane(bodySplit);
        initSourceList();
    }

    private void initSourceList() {
        updateSourceList();
        sourceList.putAct(this::actEdit);
    }


    public void updateSourceList() {
        sourceList.clear();
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".grv")) {
                sourceList.put(new SourceFile(file));
            }
        }
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
                if (file.createNewFile()) {
                    var source = new SourceFile(file);
                    sourceList.put(source);
                    sortSorceList();
                } else {
                    throw new RuntimeException("Could not create the source file.");
                }
            } catch (Exception e) {
                WizSwing.showError(e);
            }
        }).start();
    }

    private void actEdit(ActionEvent event) {
        var source = sourceList.getSelectedValue();
        if (source != null) {
            new Editor(this, source).start();
        }
    }

    private int runnerId = 0;
    private int getRunnerId() {
        runnerId++;
        return runnerId;
    }

    private void actStart(ActionEvent event) {
        var source = sourceList.getSelectedValue();
        if (source != null) {
            startRunner(new Runner("#" + getRunnerId(), source));
        }
    }

    private void actRun(ActionEvent event) {
        var command = runnerField.getText();
        if (!command.isEmpty()) {   
            var name = "#" + getRunnerId();
            var source = new SourceText(name).putText(command);
            startRunner(new Runner(name, source));
        }
    }

    private void startRunner(Runner runner) {
        runnerCombo.put(runner);
        runnerCombo.select(runner);
        runner.start();
    }

    private void actSelect(ActionEvent event) {}

    private void actResume(ActionEvent event) {}

    private void actPause(ActionEvent event) {}

    private void actStop(ActionEvent event) {}

    private void actView(ActionEvent event) {}

    private void sortSorceList() {
        sourceList.sort((a, b) -> a.getName().compareTo(b.getName()));
    }

}
