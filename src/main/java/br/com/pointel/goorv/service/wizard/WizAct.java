package br.com.pointel.goorv.service.wizard;

import java.util.List;
import java.util.stream.Collectors;
import br.com.pointel.goorv.domain.Acting;
import br.com.pointel.goorv.domain.Activity;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class WizAct {

    public static final String ACTIVITY_PACKAGE = "br.com.pointel.goorv.activity";

    public static List<String> getActivities() {
        try (ScanResult scanResult = new ClassGraph().acceptPackages(ACTIVITY_PACKAGE).scan()) {
            return scanResult.getAllClasses().stream().map(ClassInfo::getName).collect(Collectors.toList());
        }
    }

    public static Activity getActivity(Acting acting, String activityName) throws Exception {
        Class<?> clazz = Class.forName(ACTIVITY_PACKAGE + "." + activityName);
        return (Activity) clazz.getConstructor(Acting.class).newInstance(acting);
    }    

}
