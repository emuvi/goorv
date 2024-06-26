package br.com.pointel.goorv.activity;

public class ShowStats {

    public static void launch(String[] args) {
        System.out.println("ShowStats");
        System.out.println("Args:-" + String.join("-", args) + "-");
    }

    public static String help() {
        return "ShowStats [arg1] [arg2] ...";
    }

}
