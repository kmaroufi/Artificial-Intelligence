package com.company;

import com.company.players.*;

/**
 * Created by The_CodeBreakeR on 3/16/17.
 */
public class Main {

    public static void main(String[] args) {
//        Player p1 = new NaivePlayer(1);
//        Player p2 = new NaivePlayer(2);
        Player p1 = new Player94105139(1);
//        Player94105139 p2 = new Player94105139(2);
//        Player94105803 p1 = new Player94105803(1);
//        Player94105803 p2 = new Player94105803(2);
        Player p2 = new Player94109143_2(2);
//        Player p2 = new Player94105139(2);
//        Danial p2 = new Danial(2);
        Game g = new Game(p1, p2);
        g.start();
    }

}