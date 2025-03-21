package ru.nsu.ccfit.KHAMITOV.tetris.View;

import javax.swing.*;
import java.awt.*;

public class RenderMenu extends JPanel{
    private final JButton startButton = new JButton("Start Game");
    private final JButton tableScoreButton = new JButton("Table Score");;
    private final JButton exitButton = new JButton("Exit");;
    private JPanel gamePanel = new JPanel();
    private JPanel menuPanel;

    public RenderMenu(){
        setLayout(new CardLayout());
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tableScoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuPanel.add(Box.createVerticalGlue());  // Для выравнивания по вертикали
        menuPanel.add(startButton);
        menuPanel.add(Box.createVerticalGlue());  // Для выравнивания по вертикали
        menuPanel.add(tableScoreButton);
        menuPanel.add(Box.createVerticalGlue());  // Для выравнивания по вертикали
        menuPanel.add(exitButton);
        menuPanel.add(Box.createVerticalGlue());  // Для выравнивания по вертикали

        gamePanel.setLayout(new BorderLayout());

        add(menuPanel, "menu");
        add(gamePanel, "game");

        ((CardLayout) getLayout()).show(this, "menu");
        setPreferredSize(new Dimension(200, 240));
        setFocusable(true);
    }
    public JButton getStartButton() {
        return startButton;
    }

    public JButton getTableButton() {
        return tableScoreButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    // Метод для переключения на игровую панель
    public void showGamePanel() {
        ((CardLayout) getLayout()).show(this, "game");
    }
    public void addBoard(RenderBoard renderBoard){
        gamePanel.add(renderBoard);
    }
    // Метод для переключения на меню
    public void showMenuPanel() {
        ((CardLayout) getLayout()).show(this, "menu");
    }
    public void clearBoard() {
        if (gamePanel != null) {
            gamePanel.removeAll();
            gamePanel.revalidate();
            gamePanel.repaint();
        }
    }
}
