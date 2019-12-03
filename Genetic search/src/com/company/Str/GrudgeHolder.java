package com.company.Str;

import com.company.Strategy;

public class GrudgeHolder extends Strategy
{
    double rememberRate = 0.8;
    double memory = 0.0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        rememberRate = 0.8;
        memory = 0.0;
    }

    public int output()
    {
        return (int) Math.round(memory);
    }

    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        memory = rememberRate * memory + (1-rememberRate) * otherAction;
    }
}
