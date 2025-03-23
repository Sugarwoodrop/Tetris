package ru.nsu.ccfit.KHAMITOV.tetris.Model.Score;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Setting.Setting;

import java.io.File;
import java.io.IOException;

public class Score {
    int score = 0;
    private static ScoreTable table = new ScoreTable();
    private static final File file = new File(Setting.getNameFileScoreTable());

    public void WriteRecord(String nickname) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            table = ReadRecords();
        } catch (IOException e) {
            System.out.println("Первый запуск");
        }
        table.addNicknameAndScore(nickname, score);
        objectMapper.writeValue(file, table);
    }
    public static ScoreTable ReadRecords() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("ScoreTable.json");

        if (file.exists()) {
            return objectMapper.readValue(file, ScoreTable.class);
        } else {
            return new ScoreTable();
        }
    }

    public void addScore(int score){
        this.score += score;
    }

    public int getScore(){
        return score;
    }
}
