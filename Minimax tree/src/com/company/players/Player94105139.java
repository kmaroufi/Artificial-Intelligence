package com.company.players;

import com.company.Board;
import com.company.Cell;
import com.company.IntPair;
import javafx.util.Pair;

import java.util.*;

public class Player94105139 extends Player {
    private Integer[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    Random random = new Random();
    int[][] visited = new int[Board.getSize()][Board.getSize()];
    int[][] visited2 = new int[Board.getSize()][Board.getSize()];
    float samsum = 0;
    private Long time;
    private int maxDepth=1;
    private Boolean isIs;

    public Player94105139(int col) {
        super(col);
        List<Integer[]> arr = new ArrayList<>();
        arr.add(new Integer[]{1,0});
        arr.add(new Integer[]{0, 1});
        arr.add(new Integer[]{-1, 0});
        arr.add(new Integer[]{0, -1});
        Collections.shuffle(arr);
        for (int i = 0; i < 4; i++) {
            directions[i] = arr.get(i);
        }
    }

    @Override
    public IntPair getMove(Board board) {
        time = System.currentTimeMillis();
        int enemyCol = getCol() == 1 ? 2 : 1;
        Cell playerHead = board.getCell(board.getHead(getCol()).x, board.getHead(getCol()).y);
        Cell enemyHead = board.getCell(board.getHead(enemyCol).x, board.getHead(enemyCol).y);
        int m = bfs(board, playerHead, getCol());
        int n = bfs(board, enemyHead, enemyCol);
        isIs = visited2[playerHead.getRow()][playerHead.getColumn()] == -1;

//        if (board.getNumberOfMoves() == 200) {
//            for (int i = Board.getSize()-1; i > -1; i--) {
//                for (int j = 0; j < Board.getSize()-1; j++) {
//                    System.out.print(String.valueOf(visited[i][j]) + " ");
//                }
//                System.out.println();
//            }
//            System.out.println(m);
//            System.out.println();
//        }
        return minimax(board);
    }

    private IntPair minimax(Board board) {
        Board bestBoard = maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0).getValue();
//        System.out.println(board.getHead(getCol()).x);
//        System.out.println(board.getHead(getCol()).y);
//        System.out.println(bestBoard);
//        System.out.println();
        if (elapsedTime() > 440) {
            maxDepth-=1;
        } else if (maxDepth < 13 && board.getNumberOfMoves()>20){
//            System.out.println("s");
                maxDepth+=1;
        }
//        System.out.println(maxDepth);
        return bestBoard.getHead(getCol());
    }

    private Pair<Integer, Board> maxValue(Board board, int a, int b, int depth) {
//        System.out.println("------------------------------");
//        System.out.println("enter max: " + String.valueOf(depth));
        if (terminalTest(board, depth) || hurry()) {
            return new Pair<Integer, Board>(evaluate(board), board);
        }
        if (superHurry()) {
            return new Pair<Integer, Board>(Integer.MIN_VALUE, board);
        }
//        if (hurry()) {
//            return new Pair<Integer, Board>(Integer.MIN_VALUE, board);
//        }
        int v = Integer.MIN_VALUE;
        Board bestBoard = null;
        for (int i = 0; i < 4; i++) {
            int x = board.getHead(getCol()).x;
            int y = board.getHead(getCol()).y;
            Board nextBoard = new Board(board);
            int res = nextBoard.move(new IntPair(x + directions[i][0], y + directions[i][1]), getCol());
            if (res == -1) {
//                System.out.println(String.valueOf(getCol()) + ", " + String.valueOf(i));
                continue;
            }
            Pair<Integer, Board> pair = minValue(nextBoard, a, b, depth + 1);
            if (depth==0) {
//                System.out.println(String.valueOf(directions[i][0]) + ", " + String.valueOf(directions[i][1]) + " - " + pair.getKey());
            }
            if (!isIs && calcDistanceFromEdge(nextBoard.getCell(nextBoard.getHead(getCol()).x, nextBoard.getHead(getCol()).y))<=1){
                pair = new Pair<>(pair.getKey()-1000,pair.getValue());
            }
            if (v < pair.getKey()) {
                v = pair.getKey();
                bestBoard = nextBoard;
//                System.out.println("yup");
            }
            if (v >= b) {
//                System.out.println(bestBoard);
                return new Pair<Integer, Board>(v, bestBoard);
            }
            a = Math.max(a, v);
        }
//        System.out.println("exit max: " + String.valueOf(depth));
        return new Pair<Integer, Board>(v, bestBoard);
    }

    private Pair<Integer, Board> minValue(Board board, int a, int b, int depth) {
//        System.out.println("------------------------------");
//        System.out.println("enter min: " + String.valueOf(depth));
        if (terminalTest(board, depth) || hurry()) {
            return new Pair<Integer, Board>(evaluate(board), board);
        }
        if (superHurry()) {
            return new Pair<Integer, Board>(Integer.MAX_VALUE, board);
        }
//        if (hurry()) {
//            return new Pair<Integer, Board>(Integer.MAX_VALUE, board);
//        }
        int v = Integer.MAX_VALUE;
        Board bestBoard = null;
        int enemyCol = getCol() == 1 ? 2 : 1; //TODO
        for (int i = 0; i < 4; i++) {
            int x = board.getHead(enemyCol).x;
            int y = board.getHead(enemyCol).y;
            Board nextBoard = new Board(board);
            int res = nextBoard.move(new IntPair(x + directions[i][0], y + directions[i][1]), enemyCol);
            if (res == -1) {
//                System.out.println(String.valueOf(getCol()) + ", " + String.valueOf(i) + " no");
                continue;
            }
//            System.out.println(String.valueOf(getCol()) + ", " + String.valueOf(i) + " yes");
            Pair<Integer, Board> pair = maxValue(nextBoard, a, b, depth + 1);
//            System.out.println("back to min: " + String.valueOf(depth));
//            System.out.println(pair.getKey());
            if (v > pair.getKey()) {
                v = pair.getKey();
                bestBoard = nextBoard;
            }
            if (v <= a) {
//                System.out.println("D");
//                if (bestBoard == null) {
//                    bestBoard = nextBoard;
//                }
                return new Pair<Integer, Board>(v, bestBoard);
            }
            b = Math.min(b, v);
        }
//        System.out.println("exit min: " + String.valueOf(depth));
        return new Pair<Integer, Board>(v, bestBoard);
    }

    private Boolean terminalTest(Board board, int depth) {
        if (depth > maxDepth)
            return true;
        for (int i = 0; i < 4; i++) {
            int x = board.getHead(getCol()).x;
            int y = board.getHead(getCol()).y;
            Board nextBoard = new Board(board);
            int res = nextBoard.move(new IntPair(x + directions[i][0], y + directions[i][1]), getCol());
            if (res != -1) {
//                int enemyCol = getCol() == 1 ? 2 : 1;
//                for (int j = 0; j < 4; j++) {
//                    x = board.getHead(enemyCol).x;
//                    y = board.getHead(enemyCol).y;
//                    nextBoard = new Board(board);
//                    res = nextBoard.move(new IntPair(x + directions[j][0], y + directions[j][1]), enemyCol);
//                    if (res != -1) {
//                        return false;
//                    }
//                }
//                return true;
                return false;
            }
        }
        return true;
    }

    private Integer evaluate(Board board) {
        int enemyCol = getCol() == 1 ? 2 : 1;
        Cell playerHead = board.getCell(board.getHead(getCol()).x, board.getHead(getCol()).y);
//        if (playerHead.getColumn() == 8 && playerHead.getRow() == 11) {
//            System.out.println("DSfffffffffffffffffffffffff");
//        }
        Cell enemyHead = board.getCell(board.getHead(enemyCol).x, board.getHead(enemyCol).y);
//        System.out.println(String.valueOf(board.getHead(getCol()).x) + ", " + String.valueOf(board.getHead(getCol()).y));
//        System.out.println(String.valueOf(board.getHead(enemyCol).x) + ", " + String.valueOf(board.getHead(enemyCol).y));
//        System.out.println();
//        System.out.println(String.valueOf(playerHead.getColumn()) + ", " + String.valueOf(playerHead.getRow()));
//        System.out.println(String.valueOf(enemyHead.getColumn()) + ", " + String.valueOf(enemyHead.getRow()));
        int m = bfs(board, playerHead, getCol());
        float sum1 = samsum;
        int n = bfs(board, enemyHead, enemyCol);
        float sum2 = samsum;
//        System.out.println("m: " + String.valueOf(m) + ", n: " + String.valueOf(n));
        float ratio = Math.min(1, board.getNumberOfMoves() / 200.0f);
//        System.out.println("sum1: " + String.valueOf(sum1) + ", sum2: " + String.valueOf(sum2));
//        System.out.println(visited[playerHead.getRow()][playerHead.getColumn()]);
//        return -(20-playerHead.getRow());
        if (visited2[playerHead.getRow()][playerHead.getColumn()] != -1) {
//            System.out.println(visited[playerHead.getRow()][playerHead.getColumn()]);
//            System.out.println("QQQ");
//            System.out.println(-calcDistance2(playerHead, enemyHead));
            if (board.getNumberOfMoves() < 20) {
//                System.out.println("SS");
                return (int)-calcDistanceFromCenter2(playerHead)*10;
            }
//            if (calcDistanceFromEdge(playerHead) == 1) {
//                return -1000;
//            }
            int z = 0;
//            System.out.println(m+n);
//            if (m+n+board.getNumberOfMoves()*2 !=)
            return (int)(m+sum2-sum1*5-calcDistanceFromCenter2(playerHead)*10-visited2[playerHead.getRow()][playerHead.getColumn()]*2) ;
        } else {
            if (isIs) {
//                System.out.println("BBB");
                return m;
            }
            return (m-n)*10000;
        }
//        return m;

//        return random.nextInt(10);
    }

    private float calcDistance(Cell cel1, Cell cel2) {
        return (float) (Math.abs(cel1.getColumn()-cel2.getColumn()) + Math.abs(cel1.getRow()-cel2.getRow()));
    }

    private float calcDistance2(Cell cel1, Cell cel2) {
        return (float) (Math.pow(cel1.getColumn()-cel2.getColumn(),2) + Math.pow(cel1.getRow()-cel2.getRow(),2));
    }

    private float calcDistanceFromCenter(Cell cel1) {
        return (float) (Math.abs(cel1.getColumn()-10) + Math.abs(cel1.getRow()-10));
    }

    private float calcDistanceFromCenter2(Cell cel1) {
        return (float) Math.sqrt(Math.pow(cel1.getColumn()-10,2) + Math.pow(cel1.getRow()-10,2));
    }

    private float calcDistanceFromEdge(Cell cel1) {
        return Math.min(Math.min(cel1.getRow(), 20-cel1.getRow()), Math.min(cel1.getColumn(), 20-cel1.getColumn()));
    }

    private int bfs(Board board, Cell s, int color) {
        if (color == getCol()) {
            for (int i = 0; i < Board.getSize(); i++) {
                for (int j = 0; j < Board.getSize(); j++) {
                    visited[i][j] = -1;
                }
            }
            samsum = 0;
            visited[s.getRow()][s.getColumn()] = 0;
            Queue<Cell> queue = new LinkedList<>();
            queue.add(s);
            int num = 0;
            while (!queue.isEmpty()) {
                num++;
                Cell cell = queue.remove();
                for (int i = 0; i < 4; i++) {
                    int x = cell.getRow()+directions[i][0];
                    int y = cell.getColumn()+directions[i][1];
                    if (board.getHead(getCol()).x == x && board.getHead(getCol()).y == y) {
                        visited[x][y] = visited[cell.getRow()][cell.getColumn()] + 1;
                    }
                    if (x < 0 || x > Board.getSize() - 1 || y < 0 || y > Board.getSize() - 1 ||
                            board.getCell(x,y).getColor() != 0)
                        continue;
                    if (visited[x][y] == -1) {
                        visited[x][y] = visited[cell.getRow()][cell.getColumn()] + 1;
                        samsum += visited[x][y];
                        queue.add(board.getCell(x, y));
                    }
                }
            }
            samsum /= num;
            return num;
        } else {
            for (int i = 0; i < Board.getSize(); i++) {
                for (int j = 0; j < Board.getSize(); j++) {
                    visited2[i][j] = -1;
                }
            }
            samsum = 0;
            visited2[s.getRow()][s.getColumn()] = 0;
            Queue<Cell> queue = new LinkedList<>();
            queue.add(s);
            int num = 0;
            while (!queue.isEmpty()) {
                num++;
                Cell cell = queue.remove();
                for (int i = 0; i < 4; i++) {
                    int x = cell.getRow()+directions[i][0];
                    int y = cell.getColumn()+directions[i][1];
                    if (board.getHead(getCol()).x == x && board.getHead(getCol()).y == y) {
                        visited2[x][y] = visited2[cell.getRow()][cell.getColumn()] + 1;
                    }
                    if (x < 0 || x > Board.getSize() - 1 || y < 0 || y > Board.getSize() - 1 ||
                            board.getCell(x,y).getColor() != 0)
                        continue;
                    if (visited2[x][y] == -1) {
                        visited2[x][y] = visited2[cell.getRow()][cell.getColumn()] + 1;
                        samsum += visited2[x][y];
                        queue.add(board.getCell(x, y));
                    }
                }
            }
            samsum /= num;
            return num;
        }
    }

    private int calcMagic() {
        int num = 0;
        for (int i = 0; i < Board.getSize(); i++) {
            for (int j = 0; j < Board.getSize(); j++) {
                if (visited2[i][j] == -1 || visited[i][j] == -1)
                    continue;
                if (visited2[i][j] < visited[i][j]) {
                    num++;
                }
            }
        }
        System.out.println(num);
        return num;
    }

    private int elapsedTime() {
        return (int) (System.currentTimeMillis() - time);
    }

    private Boolean hurry() {
        if (elapsedTime() > 440) {
//            System.out.println("AAAAAAAAAAAAAA");
        }
        return elapsedTime() > 440 && elapsedTime() < 470;
    }

    private Boolean superHurry() {
        return elapsedTime() > 470;
    }
}
