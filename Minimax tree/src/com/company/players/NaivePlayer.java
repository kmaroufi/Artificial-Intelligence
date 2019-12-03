package com.company.players;

import com.company.Board;
import com.company.IntPair;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by The_CodeBreakeR on 3/16/17.
 */
public class NaivePlayer extends Player {

    public NaivePlayer(int col) {
        super(col);
    }

    @Override
    public IntPair getMove(Board board) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 19; i > -1; i--) {
            for (int j = 0; j < 20; j++) {
                System.out.print(String.valueOf(board.getCell(i, j).getColor()) + " ");
            }
            System.out.println();
        }
        System.out.println(board.getHead(getCol()).x);
        System.out.println("------------");
        while (true) {
            IntPair candidate = new IntPair(ThreadLocalRandom.current().nextInt(0, 16), ThreadLocalRandom.current().nextInt(0, 16));
            Board tmpBoard = new Board(board);
            if(tmpBoard.move(candidate, this.getCol()) == 0)
                return candidate;
        }
    }
}
