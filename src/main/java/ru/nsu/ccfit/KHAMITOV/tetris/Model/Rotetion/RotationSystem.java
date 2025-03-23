package ru.nsu.ccfit.KHAMITOV.tetris.Model.Rotetion;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Rotetion.table.TableWallKick;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Setting.Setting;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class RotationSystem {
    public static List<Integer> RotationTetromine(List<Integer> tetrominoMatrix, int leftOrRight){
        int size = (int) sqrt(tetrominoMatrix.size());
        int[][] matrix = new int[size][size];

        // Заполнение двумерного массива
        for (int i = 0; i < tetrominoMatrix.size(); i++) {
            int row = i / size;
            int col = i % size;
            matrix[row][col] = tetrominoMatrix.get(i);
        }

        int[][] rotatedMatrix = new int[size][size];

        if (leftOrRight == Setting.getRotationLeft()) {
            // Поворот на 90 градусов по часовой стрелке
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    rotatedMatrix[j][size - 1 - i] = matrix[i][j];
                }
            }
        } else {
            // Поворот на 90 градусов против часовой стрелки
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    rotatedMatrix[size - 1 - j][i] = matrix[i][j];
                }
            }
        }

        // Преобразование обратно в List<Integer>
        List<Integer> result = new ArrayList<>(tetrominoMatrix.size());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.add(rotatedMatrix[i][j]);
            }
        }
        return result;
    }
    public static int[] WallKick( int numberTetromine, int angleBefore, int angleAfter, int numberCheck){
        return TableWallKick.getTableWallKick(numberTetromine,numberCheck,angleBefore,angleAfter);
    }
}
