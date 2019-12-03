package com.company;

import com.company.Str.*;

public class Main
{
    public static Game game;
    public static Strategy[] trainSet;
    public static double[] trainSetWeight;
    public static int[] trainSetRepeat;

    public static void main(String[] args) throws Exception
    {
        setTrainsetDefault();
        setGame();

        GeneticSearch search = new GeneticSearch(game);
        System.out.println(search.search());

        //System.out.println(game.score(new TitForTat()));
        DFA.loadFromFile()

    }

    public static void setTrainsetDefault()
    {
        trainSet = new Strategy[17];
        trainSetWeight = new double[17];
        trainSetRepeat = new int[17];

        trainSet[0] = new Adaptive();
        trainSetWeight[0] = 3.0;
        trainSetRepeat[0] = 1;
        trainSet[1] = new AlwaysCooperate();
        trainSetWeight[1] = 2.0;
        trainSetRepeat[1] = 1;
        trainSet[2] = new AlwaysDefect();
        trainSetWeight[2] = 5.0;
        trainSetRepeat[2] = 10;
        trainSet[3] = new AlwaysRandom();
        trainSetWeight[3] = 5.0;
        trainSetRepeat[3] = 10;
        trainSet[4] = new Gradual();
        trainSetWeight[4] = 3.0;
        trainSetRepeat[4] = 1;
        trainSet[5] = new GrimTrigger();
        trainSetWeight[5] = 4.0;
        trainSetRepeat[5] = 1;
        trainSet[6] = new GrimTriggerSoft();
        trainSetWeight[6] = 3.0;
        trainSetRepeat[6] = 1;
        trainSet[7] = new GrudgeHolder();
        trainSetWeight[7] = 3.0;
        trainSetRepeat[7] = 1;
        trainSet[8] = new Pavlov();
        trainSetWeight[8] = 3.0;
        trainSetRepeat[8] = 1;
        trainSet[9] = new TitForTat();
        trainSetWeight[9] = 5.0;
        trainSetRepeat[9] = 1;
        trainSet[10] = new TitForTatExp();
        trainSetWeight[10] = 10.0;
        trainSetRepeat[10] = 5;
        trainSet[11] = new TitForTatExpRemorse();
        trainSetWeight[11] = 10.0;
        trainSetRepeat[11] = 4;
        trainSet[12] = new TitForTatHarsh();
        trainSetWeight[12] = 3.0;
        trainSetRepeat[12] = 1;
        trainSet[13] = new TitForTatPeace();
        trainSetWeight[13] = 10.0;
        trainSetRepeat[13] = 5;
        trainSet[14] = new TitForTatSus();
        trainSetWeight[14] = 3.0;
        trainSetRepeat[14] = 3;
        trainSet[15] = new TitForTwoTat();
        trainSetWeight[15] = 4.0;
        trainSetRepeat[15] = 1;
        trainSet[16] = new UpDown();
        trainSetWeight[16] = 1.0;
        trainSetRepeat[16] = 1;
    }

    public static void setGame()
    {
        game = new Game();
        int[][] p1playoffs = new int[6][];
        int[][] p2playoffs = new int[6][];
        for(int i=0; i<6; i++)
        {
            p1playoffs[i] = new int[6];
            p2playoffs[i] = new int[6];
        }

        for(int p1=0; p1<6; p1++)
            for(int p2=0; p2<6; p2++)
            {
                p1playoffs[p1][p2] = p1+2*(5-p2);
                p2playoffs[p1][p2] = p2+2*(5-p1);
            }

        game.set(p1playoffs, p2playoffs, 100, 20, trainSet, trainSetWeight, trainSetRepeat, 0.0);
    }

}
