package com.company;

import com.company.graphic.GamePanel;
import com.company.players.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by The_CodeBreakeR on 3/16/17.
 */
public class Game {

    private final static int timeLimit = 500;
    private Player[] players = new Player[2];
    private Board board;
    private JFrame frame;
    private GamePanel gamePanel;
    private int turn = 0;
    private IntPair newHead;

    public Game(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;
    }

    public void start() {
        board=new Board();
        gamePanel=new GamePanel(board);
        gamePanel.setBounds(0, 0, GamePanel.size * (Board.size + 1), GamePanel.size * (Board.size + 1));
        frame=new JFrame("WengerCup2");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(GamePanel.size * (Board.size + 1), GamePanel.size * (Board.size + 1) + 22));
        frame.pack();
        frame.setVisible(true);
        frame.add(gamePanel);
        gamePanel.repaint();
        while (board.win() == 0) {
            newHead = new IntPair(-10,-10);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Thread t = new Thread() {
                public void run() {
                    try {
                        newHead = players[turn].getMove(new Board(board));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            try {
                t.join(timeLimit);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            if(newHead.x == -10) {
                System.out.println("Player " + players[turn].getCol() + " has exceeded the time limit\n" +
                        "Player " + players[1-turn].getCol() + " has won\n");
                return;
            }
            if(board.move(newHead, turn + 1) == -1) {
                System.out.println("Player " + players[turn].getCol() + " has made an invalid move\n" +
                        "Player " + players[1-turn].getCol() + " has won\n");
                return;
            }
            gamePanel.repaint();
            turn = 1 - turn;
        }
        System.out.println("Player " + board.win() + " has won\n");
    }
}
