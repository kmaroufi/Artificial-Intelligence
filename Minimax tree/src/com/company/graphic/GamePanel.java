package com.company.graphic;

import com.company.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by The_CodeBreakeR on 3/16/17.
 */
public class GamePanel extends JPanel{

    public final static int size = 30;
    public final static int dia = 25;
    private Board board;
    private Color [] colors={Color.GRAY, new Color(238,130,238), new Color(144,238,144)};

    public GamePanel(Board board) {
        this.board = board;
        setLayout(null);
        setOpaque(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(size * (Board.size + 1), size * (Board.size + 1)));
        setSize(size * (Board.size + 1), size * (Board.size + 1));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0 ; i < Board.size ; i++)
            for(int j = 0 ; j < Board.size ; j++) {

                if (board.getCell(Board.size - 1 - j, i).getColor() == 0)
                    drawCenteredRectangle((Graphics2D) g, size * (i + 1), size * (j + 1),
                            dia, colors[board.getCell(Board.size - 1 - j, i).getColor()]);

                if (board.getHead(1).x == Board.size - 1 - j && board.getHead(1).y == i)
                {
                    drawCenteredRectangle((Graphics2D) g, size * (i + 1), size * (j + 1),
                            dia, colors[0]);
                    drawCenteredCircle((Graphics2D) g, size * (i + 1), size * (j + 1), dia*4/5, Color.MAGENTA);
                }
                else if (board.getHead(2).x == Board.size - 1 - j && board.getHead(2).y == i)
                {
                    drawCenteredRectangle((Graphics2D) g, size * (i + 1), size * (j + 1),
                            dia, colors[0]);
                    drawCenteredCircle((Graphics2D) g, size * (i + 1), size * (j + 1), dia*4/5, Color.GREEN);
                }
                else
                    drawCenteredRectangle((Graphics2D) g, size * (i + 1), size * (j + 1),
                            dia, colors[board.getCell(Board.size - 1 - j, i).getColor()]);
            }
    }

    private void drawCenteredCircle(Graphics2D g, int x, int y, int d, Color col) {
        g.setColor(col);
        x = x - (d / 2);
        y = y - (d / 2);
        g.fillOval(x, y, d, d);
    }

    private void drawCenteredRectangle(Graphics2D g, int x, int y, int d, Color col)
    {
        g.setColor(col);
        x = x - (d / 2);
        y = y - (d / 2);
        g.fillRect(x, y, d, d);
    }
}