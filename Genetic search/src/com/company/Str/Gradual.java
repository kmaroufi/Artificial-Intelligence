package com.company.Str;

import com.company.Strategy;


public class Gradual extends Strategy
{
    double paybackRate = 0.5;

    int lastAction = 0;
    int otherDefectCount = 0;
    int otherDefectCountTemp = 0;
    int coopPhase = 2;
    boolean paybackPhase = false;
    int actionCount = 0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        lastAction = 0;
        otherDefectCount = 0;
        otherDefectCountTemp = 0;
        coopPhase = 2;
        paybackPhase = false;
        if(thisPlayer == 1)
            actionCount = p1payoffs.length;
        else
            actionCount = p1payoffs[0].length;
    }
    public int output()
    {
        if(coopPhase > 0)
        {
            otherDefectCount += lastAction;
            coopPhase--;
            return 0;
        } else if(paybackPhase)
        {
            int temp = (int) Math.min(Math.round(otherDefectCount*paybackRate), actionCount-1);
            otherDefectCount -= temp;
            otherDefectCountTemp += lastAction;
            if(otherDefectCount <= 0)
            {
                otherDefectCount = otherDefectCountTemp;
                otherDefectCountTemp = 0;
                paybackPhase = false;
                coopPhase = 2;
            }
            return temp;
        }

        if(lastAction > 0)
        {
            paybackPhase = true;
            return lastAction;
        }

        return 0;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        lastAction = otherAction;
    }
}
