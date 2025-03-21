package ru.nsu.ccfit.KHAMITOV.tetris;

import ru.nsu.ccfit.KHAMITOV.tetris.Controller.Controller;
import ru.nsu.ccfit.KHAMITOV.tetris.View.RenderMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        RenderMenu menu = new RenderMenu();
        JFrame frame = new JFrame("Tetris");

        Controller controller = new Controller(menu, frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(menu);
        frame.pack();
        frame.setVisible(true);

        // Делаем панель доступной для получения событий клавиш
        menu.setFocusable(true);
    }
}