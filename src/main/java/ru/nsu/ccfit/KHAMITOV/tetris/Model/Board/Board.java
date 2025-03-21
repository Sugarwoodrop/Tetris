package ru.nsu.ccfit.KHAMITOV.tetris.Model.Board;

import java.util.List;

import static java.lang.Math.sqrt;

public class Board {
    private int[][] field;
    private int height;
    private int width;

    public Board(int height, int width){
        setField(height,width);
    }

    private void setField(int height, int width){
        this.height = height;
        this.width = width;
        field = new int[height][width];
    }
    public void addFloor(List<Integer> tetromineMatrix, int X, int Y){
        int sizeMatrix = (int)sqrt(tetromineMatrix.size());
        for (int i = 0; i < tetromineMatrix.size(); i++) {
            if (tetromineMatrix.get(i) == 1) {
                int numberRow = i / sizeMatrix;
                int y = Y + numberRow-1;
                int x = X + (i % sizeMatrix);

                if (y >= 0 && y < height && x >= 0 && x < width) {
                    field[y][x] = 1;
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getfield(int X, int Y){
        return  field[Y][X];
    }

    public boolean isPossiblePut(List<Integer> tetromineMatrix, int X, int Y){
        int sizeMatrix = (int)sqrt(tetromineMatrix.size());
        int numberTrueBlocks =0;
        for (int i = 0; i < tetromineMatrix.size(); i++) {
            if (tetromineMatrix.get(i) == 1) {
                int numberRow = i / sizeMatrix;
                int y = Y + numberRow;
                int x = X + (i % sizeMatrix);

                if (y >= 0 && y < height && x >= 0 && x < width && field[y][x] == 0) {
                    numberTrueBlocks++;
                    if(numberTrueBlocks ==4){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isEnd(){
        for(int i = 0; i < width; i++){
            if(field[1][i] != 0){
                return true;
            }
        }
        return false;
    }

    public int AllLine(){
        int numersLine = 0;
        for(int i = 0; i < height; i++){
            int blocks = 0;
            for(int j = 0; j < width; j++){
                if(field[i][j] == 1){
                    blocks++;
                }
                if(blocks == width){
                    numersLine++;
                }
            }
            if(blocks == width){
                field = DeleteLine(i);
            }
        }
        return numersLine;
    }
    public int[][] DeleteLine(int deliteline){
        for(int j = 0; j < width; j++) {
            field[deliteline][j] = 0;
        }
        int[][] newfield = new int[height][width];
        for(int j = 1; j <= deliteline; j++){
            newfield[j] = field[j-1];
        }
        for(int j = deliteline+1; j < height; j++){
            newfield[j] = field[j];
        }
        return  newfield;
    }
}
