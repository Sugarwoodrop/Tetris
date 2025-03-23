package ru.nsu.ccfit.KHAMITOV.tetris.View;

import ru.nsu.ccfit.KHAMITOV.tetris.Model.Setting.Setting;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class RenderMenu extends JPanel{
    private final JButton startButton = new JButton("Start Game");
    private final JButton tableScoreButton = new JButton("Table Score");
    private final JButton exitButton = new JButton("Exit");
    private final JButton OkButton = new JButton("Ok");

    private JPanel gamePanel = new JPanel();
    private JPanel readNickname = new JPanel();
    private JPanel menuPanel;
    private JTable tableScore;
    private JTextField nicknameField;

    public RenderMenu(){
        setLayout(new CardLayout());
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        tableScore = new JTable();

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

        JLabel label = new JLabel("Enter your nickname:");
        readNickname.add(label);
        nicknameField = new JTextField(20);
        readNickname.add(nicknameField);
        readNickname.add(OkButton);

        gamePanel.setLayout(new BorderLayout());
        add(menuPanel, "menu");
        add(gamePanel, "game");
        add(readNickname, "nicknameField");

        ((CardLayout) getLayout()).show(this, "menu");
        setPreferredSize(new Dimension(Setting.getMenuSizewight(), Setting.getMenuSizeHeight()));
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
    public JButton getOkButton() {
        return OkButton;
    }

    // Метод для переключения на игровую панель
    public void showGamePanel() {
        ((CardLayout) getLayout()).show(this, "game");
    }

    public String getNickname(){
        return nicknameField.getText();
    }

    public void showNicknamePanel() {
        ((CardLayout) getLayout()).show(this, "nicknameField");
    }
    public void addBoard(RenderGame renderGame){
        gamePanel.add(renderGame);
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
    public JScrollPane ReadTableScore(String[][] table){
        tableScore.removeAll();
        tableScore.revalidate();
        tableScore.repaint();
        String[] columnNames =  {"Имя", "Очки"};

        tableScore = new JTable(new DefaultTableModel(table, columnNames));
        JScrollPane scrollPane = new JScrollPane(tableScore);
        return scrollPane;
    }
}
