package ru.nsu.ccfit.KHAMITOV.tetris.Model.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class ScoreTable {
    private ArrayList<String> nickname;
    private ArrayList<Integer> score;

    public ScoreTable(){
        nickname = new ArrayList<>();
        score = new ArrayList<>();
    }

    public void addNicknameAndScore(String nickname, int score) {
        for(int i = 0 ; i<this.nickname.size(); i++) {
            if (Objects.equals(this.nickname.get(i), nickname)) {
                if (this.score.get(i) < score) {
                    this.score.set(i, score);
                }
                sortRecords();
                return;
            }
        }
        this.nickname.add(nickname);
        this.score.add(score);
        sortRecords();
    }

    public  ArrayList<String> getNickname() {
        return nickname;
    }

    public void setNickname(ArrayList<String> nickname) {
        this.nickname = nickname;
    }

    public ArrayList<Integer> getScore() {
        return score;
    }

    public void setScore(ArrayList<Integer> score) {
        this.score = score;
    }

    private void sortRecords() {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < score.size(); i++) {
            indexList.add(i);
        }
        Collections.sort(indexList, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return score.get(i2).compareTo(score.get(i1));
            }
        });

        ArrayList<Integer> sortedScores = new ArrayList<>();
        ArrayList<String> sortedNames = new ArrayList<>();

        for (int index : indexList) {
            sortedScores.add(score.get(index));
            sortedNames.add(nickname.get(index));
        }
        score.clear();
        nickname.clear();
        score.addAll(sortedScores);
        nickname.addAll(sortedNames);
    }
}
