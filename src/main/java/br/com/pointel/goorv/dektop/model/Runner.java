package br.com.pointel.goorv.dektop.model;

import java.io.Serializable;

public class Runner implements Serializable {

    private final String title;

    public Runner(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }

}
