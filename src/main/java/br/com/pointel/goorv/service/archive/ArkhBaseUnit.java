package br.com.pointel.goorv.service.archive;

/**
 *
 * @author emuvi
 */
public class ArkhBaseUnit {
    
    public final String place;
    public final Long modified;
    public final String verifier;

    public ArkhBaseUnit(String place, Long modified, String verifier) {
        this.place = place;
        this.modified = modified;
        this.verifier = verifier;
    }
    
}
