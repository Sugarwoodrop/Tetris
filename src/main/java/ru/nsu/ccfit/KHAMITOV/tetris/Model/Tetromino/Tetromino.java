package ru.nsu.ccfit.KHAMITOV.tetris.Model.Tetromino;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Board.Board;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Rotetion.RotationSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tetromino {
    private List<Integer> tetromineMatrix;
    private int angleRotation = 0; // 0 - 0, 90-1, 180-2, 270 -3, 360 - 0
    private Integer x,y = null;
    private int numbertetromine;
    private int nextTetromino;

    public Tetromino(int numberTetromine, int X, int  Y){
        setTetromineMatrix(numberTetromine);
        setX(X);
        setY(Y);
        this.numbertetromine = numberTetromine;
    }

    public void setNextTetromino(int nextTetromino){
        this.nextTetromino = nextTetromino;
    }

    public int getNextTetromino(){
        return nextTetromino;
    }

    public void setTetromineMatrix(int numberTetromine){
        tetromineMatrix=TableTetromineMatrix.Matrix(numberTetromine);
    }
    public List<Integer> getTetromineMatrix(){
        return tetromineMatrix;
    }
    public void MoveDown() {
        ++y;
    }

    public void MoveLeftAndRight(int leftOrRight){
        switch (leftOrRight){
            case 1 -> --x;
            case 2 -> ++x;
        }
    }

    public int setAngleRotation(int leftOrRight){
        int angleRotation = this.angleRotation;
        angleRotation += (leftOrRight == 1) ? -1 : 1;

        if(angleRotation == 4){
           return 0;
        } else if(angleRotation == -1){
             return 3;
        }
        return angleRotation;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public Integer getX(){
        return x;
    }
    public Integer getY(){
        return y;
    }

    public void Rotation(Board board, int leftOrRight){
        int angleAfter = setAngleRotation(leftOrRight);
        List<Integer> tetromine = RotationSystem.RotationTetromine(tetromineMatrix,leftOrRight);

        for (int i = 0; i<5; i++) {
            int[] wallkick = RotationSystem.WallKick(this.numbertetromine, angleRotation, angleAfter,i);
            Integer newx = this.x + wallkick[0];
            Integer newy = this.y + wallkick[1];
            if(board.isPossiblePut(tetromine, newx, newy)){
                this.x=newx;
                this.y=newy;
                angleRotation = angleAfter;
                tetromineMatrix = tetromine;
                return;
            }
        }
    }

    public class TableTetromineMatrix {
        private static final List<List<List<Integer>>> tableTetromineMatrix = new ArrayList<>(7);

        static {
            for (int i = 0; i < 7; i++) {
                tableTetromineMatrix.add(new ArrayList<>());
            }
            createI();
            createT();
            createS();
            createZ();
            createL();
            createJ();
            createO();
        }

        private static void createI() {
            tableTetromineMatrix.get(0).add(Arrays.asList(
                    0, 0, 0, 0,
                    1, 1, 1, 1,
                    0, 0, 0, 0,
                    0, 0, 0, 0)); //0, 180, 360
        }
        private static void createT() {
            tableTetromineMatrix.get(1).add(Arrays.asList( 0, 0, 0,
                    1, 1, 1,
                    0, 1, 0)); //0, 360

        }
        private static void createS() {
            tableTetromineMatrix.get(2).add(Arrays.asList( 0 , 1 , 1 ,
                    1 , 1 , 0 ,
                    0 , 0 , 0 )); //0, 180, 360
        }
        private static void createZ() {
            tableTetromineMatrix.get(3).add(Arrays.asList( 1, 1, 0,
                    0, 1, 1,
                    0, 0, 0)); //0,180,360
        }
        private static void createL() {
            tableTetromineMatrix.get(4).add(Arrays.asList( 0, 0, 0,
                    1, 1, 1,
                    1, 0, 0)); //0, 360
        }
        private static void createJ() {
            tableTetromineMatrix.get(5).add(Arrays.asList( 0 , 0 , 0 ,
                    1 , 1 , 1 ,
                    0 , 0 , 1 )); //0, 360

        }
        private static void createO(){
            tableTetromineMatrix.get(6).add(Arrays.asList(1 , 1 , 1 , 1));
        }

        public static List<Integer> Matrix(int numberTetromine){
            return tableTetromineMatrix.get(numberTetromine).get(0);
        }
    }

}
