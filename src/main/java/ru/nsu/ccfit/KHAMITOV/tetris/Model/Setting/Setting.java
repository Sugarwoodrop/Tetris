package ru.nsu.ccfit.KHAMITOV.tetris.Model.Setting;

public class Setting {
    private static final int TARGET_FPS = 200;
    private static final double OPTIMAL_TIME = 1_000_000_000.0 / TARGET_FPS;

    private static final long moveDelay = 110;
    private static final long fastDownDelay = 300;
    private static final double dropStartDelay = 300;
    private static final double delayRotation = 200;
    private static final double delayReductionFactor = 0.85;
    private static final int scoreForNextLvl = 300;

    private static final int heightBoard = 21;
    private static final int widthBoard = 15;

    private static final String nameFileScoreTable = "ScoreTable.json";

    private static final int nambersTetromino = 7;

    private static final int tableSizeHeight = 300;
    private static final int tableSizeWight = 400;
    private static final int menuSizeHeight = 240;
    private static final int menuSizewight = 200;
    private static final int blockDesignation = 1;
    private static final int boardDesignation = 0;

    private static final int moveLeft = 1;
    private static final int moveRight = 2;
    private static final int rotationLeft = 2;
    private static final int rotationRight = 1;

    private static final int cellSize = 30;

    public static double getDelayRotation() {
        return delayRotation;
    }

    public static int getScoreForNextLvl(){
        return scoreForNextLvl;
    }

    public static double getDelayReductionFactor(){
        return delayReductionFactor;
    }

    public static int getMoveLeft(){
        return moveLeft;
    }

    public static int getMoveRight(){
        return moveRight;
    }

    public static int getTableSizeHeight() {
        return tableSizeHeight;
    }

    public static int getTableSizeWight(){
        return tableSizeWight;
    }

    public static int getRotationLeft() {
        return rotationLeft;
    }

    public static int getRotationRight() {
        return rotationRight;
    }

    public static int getMenuSizeHeight() {
        return menuSizeHeight;
    }

    public static int getBlockDesignation() {
        return blockDesignation;
    }

    public static int getBoardDesignation() {
        return boardDesignation;
    }

    public static int getCellSize() {
        return cellSize;
    }


    public static int getMenuSizewight() {
        return menuSizewight;
    }

    public static int getTARGET_FPS() {
        return TARGET_FPS;
    }

    public static double getOPTIMAL_TIME() {
        return OPTIMAL_TIME;
    }


    public static long getMoveDelay() {
        return moveDelay;
    }

    public static long getFastDownDelay() {
        return fastDownDelay;
    }

    public static double getStartDropDelay() {
        return dropStartDelay;
    }

    public static int getHeightBoard() {
        return heightBoard;
    }

    public static int getWidthBoard() {
        return widthBoard;
    }

    public static String getNameFileScoreTable() {
        return nameFileScoreTable;
    }

    public static int getNambersTetromino() {
        return nambersTetromino;
    }
}
