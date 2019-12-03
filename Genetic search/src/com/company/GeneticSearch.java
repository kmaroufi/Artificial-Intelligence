package com.company;


import java.util.*;

/**
 * Created by asus-pc on 3/16/2018.
 */
public class GeneticSearch {
    private int k = 10; // TODO
    private Random random;
    private Game game;
    private ArrayList<String> cors;
    private int mutationProbabality = 10;

    GeneticSearch(Game game) {
        this.game = game;
        this.cors = new ArrayList<>();
        random = new Random();
    }

    public DFA search() {
        initCors();
        int maximum = sortCors();
        while (maximum < 750) {
            crossOverAndMutation();
            maximum = sortCors();
        }
        return corToDFA(cors.get(cors.size()-1));
    }

    private void crossOverAndMutation() {
        ArrayList<String> newCors = new ArrayList<>();
        for (int i = 0; i < k; i += 2) {
            int index = random.nextInt(35) + 1;
            String cor1 = cors.get(i);
            String cor2 = cors.get(i + 1);
            newCors.add(mutation(cor1.substring(0, index) + cor2.substring(index)));
            newCors.add(mutation(cor2.substring(0, index) + cor1.substring(index)));
        }
        cors.clear();
        cors.addAll(newCors);
    }

    private String mutation(String cor) {
        if (random.nextInt(mutationProbabality) == 0) {
            int index = random.nextInt(36);
            return cor.substring(0, index) + String.valueOf(random.nextInt(6)) + cor.substring(index+1);
        }
        return cor;
    }


    private int sortCors() {
        final Map<String, Integer> map = new HashMap<String, Integer>();
        int maximum = -1;
        for (String cor : cors) {
            int score = (int) game.score(corToDFA(cor));
            System.out.println(score);
            map.put(cor, score);
            maximum = Math.max(maximum, score);
        }
        cors.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(map.get(o1), map.get(o2));
            }
        });
//        System.out.println(maximum);
        return maximum;
    }

    private void initCors() {
        for (int i = 0; i < k; i++) {
            cors.add(generateRandomCode());
        }
    }

    private String generateRandomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 36; i++) {
            stringBuilder.append(String.valueOf(random.nextInt(6)));
        }
        return stringBuilder.toString();
    }

    private String dFAToCor(DFA dfa) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dfa.transitions.length; i++) {
            for (int j = 0; j < dfa.transitions[i].length; j++) {
                stringBuilder.append(dfa.transitions[i][j]);
            }
        }
        return stringBuilder.toString();
    }

    private DFA corToDFA(String cor) {
        int[][] transitions = new int[6][6];
        int[] outputs = {0, 1, 2, 3, 4, 5};
        for (int i = 0; i < cor.length(); i++) {
            transitions[i / 6][i % 6] = Integer.parseInt(String.valueOf(cor.charAt(i)));
        }
        DFA dfa = new DFA(outputs, transitions);
//        dfa.print();
        return dfa;
    }
}
