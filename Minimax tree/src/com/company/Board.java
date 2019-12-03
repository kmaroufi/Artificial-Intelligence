package com.company;

/**
 * Created by The_CodeBreakeR on 3/16/17.
 */
public class Board {

    public final static int size = 20;
    private Cell[][] cells = new Cell[size][size];
    private int numberOfMoves = 0;
    private IntPair[] head = new IntPair[2];

    public Board() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                this.cells[i][j] = new Cell(i, j, 0);
        this.cells[size / 4 - 1][size / 4 - 1] = new Cell(size / 4 - 1, size / 4 - 1, 1);
        this.cells[3 * size / 4][3 * size / 4] = new Cell(3 * size / 4, 3 * size / 4, 2);
        this.numberOfMoves = 0;
        this.head[0] = new IntPair(size / 4 - 1, size / 4 - 1);
        this.head[1] = new IntPair(3 * size / 4, 3 * size / 4);
    }

    public Board(Board board) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                this.cells[i][j] = new Cell(board.cells[i][j]);
        this.numberOfMoves = board.numberOfMoves;
        this.head[0] = new IntPair(board.head[0]);
        this.head[1] = new IntPair(board.head[1]);
    }

    public static int getSize() {
        return size;
    }

    public Cell getCell(int i , int j) {
        return cells[i][j];
    }

    public void setCell(int i , int j, Cell newCell) {
        this.cells[i][j] = new Cell(newCell);
    }

    public IntPair getHead(int playerColor) {
        return head[playerColor - 1];
    }

    public void setHead(int playerColor, IntPair newHead) {
        head[playerColor - 1] = new IntPair(newHead);
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int num) {
        numberOfMoves = num;
    }

    public int win() {
        return 0;
    }

    public int move(IntPair cellNum, int playerColor) {
        numberOfMoves++;
        if (cellNum.x < 0 || cellNum.x > size - 1 || cellNum.y < 0 || cellNum.y > size - 1 ||
                cells[cellNum.x][cellNum.y].getColor() != 0 || !adj(head[playerColor - 1], cellNum))
            return -1;
        else {
            cells[cellNum.x][cellNum.y].setColor(playerColor);
            head[playerColor - 1] = new IntPair(cellNum);
        }
        return 0;
    }

    private boolean adj(IntPair intPair1, IntPair intPair2) {
        return (intPair1.x == intPair2.x && Math.abs(intPair1.y - intPair2.y) == 1) ||
                (intPair1.y == intPair2.y && Math.abs(intPair1.x - intPair2.x) == 1);
    }
}