package com.company.Str;

import com.company.Strategy;


public class Pavlov extends Strategy
{
    int otherLastAction = 0;
    int myLastAction = 0;
    int actionCount = 0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        myLastAction = 0;
        otherLastAction = 0;
        if(thisPlayer == 1)
            actionCount = p1payoffs.length;
        else
            actionCount = p1payoffs[0].length;
    }
    public int output()
    {
        if(myLastAction == otherLastAction)
            myLastAction = myLastAction;
        else if(myLastAction > otherLastAction)
            myLastAction = Math.min(myLastAction+1, actionCount-1);
        else
            myLastAction = otherLastAction;

        return myLastAction;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        otherLastAction = otherAction;
    }
}
