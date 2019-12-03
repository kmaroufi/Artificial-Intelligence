package com.company;


public abstract class Strategy
{
    public abstract void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer);
    public abstract int output();
    public abstract void input(int otherAction, int p1payoff, int p2payoff);
    public void print(){System.out.println("Strategy Default");}
}
