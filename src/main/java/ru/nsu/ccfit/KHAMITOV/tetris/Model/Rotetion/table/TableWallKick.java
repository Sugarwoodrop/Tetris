package ru.nsu.ccfit.KHAMITOV.tetris.Model.Rotetion.table;

import java.util.ArrayList;
import java.util.List;

public class TableWallKick {
    private static final List<List<int[]>> tableWallKickNoI = new ArrayList<>();
    private static final List<List<int[]>> tableWallKickForI = new ArrayList<>();
    /*
        0 = состояние возрождения
        90 = состояние, возникающее в результате поворота по часовой стрелке
        180 = состояние, возникающее в результате 2 последовательных поворотов в любом направлении от точки появления.
        270 = состояние, возникающее в результате поворота против часовой стрелки

        Первый элемент от 0 к 90
        Второй от 0 к 270
        Третий от 90 к 0
        Четвёртый от 90 к 180
        Пятый от 180 к 90
        Шестой от 180 к 270
        Седьмой от 270 к 180
        Восьмой от 270 к 0
     */
    static {
        setTableWallKickNoI();
        setTableWallKickForI();
    }

    private static void setTableWallKickNoI(){
        List<int[]> kickDataFrom0To90 = new ArrayList<>();
        List<int[]> kickDataFrom0To270 = new ArrayList<>();
        List<int[]> kickDataFrom90To0 = new ArrayList<>();
        List<int[]> kickDataFrom90To180 = new ArrayList<>();
        List<int[]> kickDataFrom180To90 = new ArrayList<>();
        List<int[]> kickDataFrom180To270 = new ArrayList<>();
        List<int[]> kickDataFrom270To180 = new ArrayList<>();
        List<int[]> kickDataFrom270To0 = new ArrayList<>();

        kickDataFrom0To90.add(new int[]{0,0});
        kickDataFrom0To90.add(new int[]{-1,0});
        kickDataFrom0To90.add(new int[]{-1,1});
        kickDataFrom0To90.add(new int[]{0,-2});
        kickDataFrom0To90.add(new int[]{-1,-2});

        kickDataFrom0To270.add(new int[]{0,0});
        kickDataFrom0To270.add(new int[]{1,0});
        kickDataFrom0To270.add(new int[]{1,1});
        kickDataFrom0To270.add(new int[]{0,-2});
        kickDataFrom0To270.add(new int[]{1,-2});

        kickDataFrom90To0.add(new int[]{0,0});
        kickDataFrom90To0.add(new int[]{1,0});
        kickDataFrom90To0.add(new int[]{1,-1});
        kickDataFrom90To0.add(new int[]{0,2});
        kickDataFrom90To0.add(new int[]{1,2});

        kickDataFrom90To180.add(new int[]{0,0});
        kickDataFrom90To180.add(new int[]{1,0});
        kickDataFrom90To180.add(new int[]{1,-1});
        kickDataFrom90To180.add(new int[]{0,2});
        kickDataFrom90To180.add(new int[]{1,2});

        kickDataFrom180To90.add(new int[]{0,0});
        kickDataFrom180To90.add(new int[]{-1,0});
        kickDataFrom180To90.add(new int[]{-1,1});
        kickDataFrom180To90.add(new int[]{0,-2});
        kickDataFrom180To90.add(new int[]{-1,-2});

        kickDataFrom180To270.add(new int[]{0,0});
        kickDataFrom180To270.add(new int[]{1,0});
        kickDataFrom180To270.add(new int[]{1,1});
        kickDataFrom180To270.add(new int[]{0,-2});
        kickDataFrom180To270.add(new int[]{1,-2});

        kickDataFrom270To180.add(new int[]{0,0});
        kickDataFrom270To180.add(new int[]{-1,0});
        kickDataFrom270To180.add(new int[]{-1,-1});
        kickDataFrom270To180.add(new int[]{0,2});
        kickDataFrom270To180.add(new int[]{-1,2});

        kickDataFrom270To0.add(new int[]{0,0});
        kickDataFrom270To0.add(new int[]{-1,0});
        kickDataFrom270To0.add(new int[]{-1,-1});
        kickDataFrom270To0.add(new int[]{0, 2});
        kickDataFrom270To0.add(new int[]{-1,2});

        tableWallKickNoI.add(kickDataFrom0To90);
        tableWallKickNoI.add(kickDataFrom0To270);
        tableWallKickNoI.add(kickDataFrom90To0);
        tableWallKickNoI.add(kickDataFrom90To180);
        tableWallKickNoI.add(kickDataFrom180To90);
        tableWallKickNoI.add(kickDataFrom180To270);
        tableWallKickNoI.add(kickDataFrom270To180);
        tableWallKickNoI.add(kickDataFrom270To0);
    }

    private static void setTableWallKickForI(){
        List<int[]> kickDataFrom0To90 = new ArrayList<>();
        List<int[]> kickDataFrom0To270 = new ArrayList<>();
        List<int[]> kickDataFrom90To0 = new ArrayList<>();
        List<int[]> kickDataFrom90To180 = new ArrayList<>();
        List<int[]> kickDataFrom180To90 = new ArrayList<>();
        List<int[]> kickDataFrom180To270 = new ArrayList<>();
        List<int[]> kickDataFrom270To180 = new ArrayList<>();
        List<int[]> kickDataFrom270To0 = new ArrayList<>();

        kickDataFrom0To90.add(new int[]{0,0});
        kickDataFrom0To90.add(new int[]{-2,0});
        kickDataFrom0To90.add(new int[]{1,0});
        kickDataFrom0To90.add(new int[]{-2,-1});
        kickDataFrom0To90.add(new int[]{1,2});

        kickDataFrom0To270.add(new int[]{0,0});
        kickDataFrom0To270.add(new int[]{-1,0});
        kickDataFrom0To270.add(new int[]{2,0});
        kickDataFrom0To270.add(new int[]{-1,2});
        kickDataFrom0To270.add(new int[]{2,-1});

        kickDataFrom90To0.add(new int[]{0,0});
        kickDataFrom90To0.add(new int[]{2,0});
        kickDataFrom90To0.add(new int[]{-1,0});
        kickDataFrom90To0.add(new int[]{2,1});
        kickDataFrom90To0.add(new int[]{-1,-2});

        kickDataFrom90To180.add(new int[]{0,0});
        kickDataFrom90To180.add(new int[]{-1,0});
        kickDataFrom90To180.add(new int[]{2,0});
        kickDataFrom90To180.add(new int[]{-1,2});
        kickDataFrom90To180.add(new int[]{2,-1});

        kickDataFrom180To90.add(new int[]{0,0});
        kickDataFrom180To90.add(new int[]{1,0});
        kickDataFrom180To90.add(new int[]{-2,0});
        kickDataFrom180To90.add(new int[]{1,-2});
        kickDataFrom180To90.add(new int[]{-2,1});

        kickDataFrom180To270.add(new int[]{0,0});
        kickDataFrom180To270.add(new int[]{2,0});
        kickDataFrom180To270.add(new int[]{-1,0});
        kickDataFrom180To270.add(new int[]{2,1});
        kickDataFrom180To270.add(new int[]{-1,-2});

        kickDataFrom270To180.add(new int[]{0,0});
        kickDataFrom270To180.add(new int[]{-2,0});
        kickDataFrom270To180.add(new int[]{1,0});
        kickDataFrom270To180.add(new int[]{-2,-1});
        kickDataFrom270To180.add(new int[]{1,2});

        kickDataFrom270To0.add(new int[]{0,0});
        kickDataFrom270To0.add(new int[]{1,0});
        kickDataFrom270To0.add(new int[]{-2,0});
        kickDataFrom270To0.add(new int[]{1,-2});
        kickDataFrom270To0.add(new int[]{-2,1});

        tableWallKickForI.add(kickDataFrom0To90);
        tableWallKickForI.add(kickDataFrom0To270);
        tableWallKickForI.add(kickDataFrom90To0);
        tableWallKickForI.add(kickDataFrom90To180);
        tableWallKickForI.add(kickDataFrom180To90);
        tableWallKickForI.add(kickDataFrom180To270);
        tableWallKickForI.add(kickDataFrom270To180);
        tableWallKickForI.add(kickDataFrom270To0);
    }
    public static int[] getTableWallKick(int numberTetromine, int numberCheck, int angleBefore, int angleAfter){
        int numberKickData = -1;
        if(angleBefore == 0 && angleAfter == 1){
            numberKickData =0;
        } else if (angleBefore == 0 && angleAfter == 3) {
            numberKickData = 1;
        } else if (angleBefore == 1 && angleAfter == 0) {
            numberKickData = 2;
        } else if (angleBefore == 1 && angleAfter == 2) {
            numberKickData = 3;
        } else if (angleBefore == 2 && angleAfter == 1) {
            numberKickData = 4;
        } else if (angleBefore == 2 && angleAfter == 3) {
            numberKickData = 5;
        } else if (angleBefore == 3 && angleAfter == 2) {
            numberKickData = 6;
        } else if (angleBefore == 3 && angleAfter == 0) {
            numberKickData = 7;
        }
        if(numberTetromine == 1){
            return tableWallKickForI.get(numberKickData).get(numberCheck);
        }
        else {
            return tableWallKickNoI.get(numberKickData).get(numberCheck);
        }
    }
}
