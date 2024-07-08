package br.com.pointel.goorv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.pointel.goorv.dektop.Desktop;

public class Goorv {

    private static Logger log = LoggerFactory.getLogger(Goorv.class);
    
    public static void main(String[] args) {
        try {
            log.info("Starting Goorv");
            Desktop.start(args);
        } catch (Exception e) {
            log.error("Error on Goorv", e);
        }
    }

}
