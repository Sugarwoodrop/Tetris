package ru.nsu.ccfit.KHAMITOV.tetris.View;

import ru.nsu.ccfit.KHAMITOV.tetris.Model.Board.Board;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Setting.Setting;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Tetromino.Tetromino;

import javax.swing.*;
import java.awt.*;
public class RenderGame extends JPanel{
    private Board board;
    private Tetromino tetromino;
    private JLabel label;

    public RenderGame(Board board, Tetromino tetromino){
        this.board = board;
        this.tetromino = tetromino;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setDoubleBuffered(true);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1980, 1080);

        g.setColor(Color.WHITE); // Белая рамка
        g.drawRect(0- Setting.getCellSize(), 0-Setting.getCellSize(), (board.getWidth())*Setting.getCellSize()+Setting.getCellSize(), (board.getHeight())*Setting.getCellSize()+Setting.getCellSize());

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getfield(j, i) == 0) {
                    draw3DCell(g2d, j * Setting.getCellSize(), i * Setting.getCellSize());
                }
                if (board.getfield(j, i) == 1) {
                    draw3DCell1(g2d, j * Setting.getCellSize(), i * Setting.getCellSize());
                }
            }
        }

        int sizeMatrix = (int) Math.sqrt(tetromino.getTetromineMatrix().size());

        for (int i = 0; i < tetromino.getTetromineMatrix().size(); i++) {
            if (tetromino.getTetromineMatrix().get(i) == 1) {
                int x = (tetromino.getX() + (i % sizeMatrix)) * Setting.getCellSize();
                int y = (tetromino.getY() + (i / sizeMatrix)) * Setting.getCellSize();
                draw3DCell1(g2d, x, y);
            }
        }

        drawNextTetromino(g2d);

    }
    private void draw3DCell(Graphics2D g2d, int x, int y) {
        // Цвет для основной части клетки
        Color mainColor = new Color(255, 255, 255);

        // Рисуем основную клетку
        g2d.setColor(mainColor);
        g2d.fillRect(x, y, Setting.getCellSize(), Setting.getCellSize());

        // Добавим светлый блик, чтобы подчеркнуть объем
        GradientPaint highlight = new GradientPaint(x, y, new Color(64, 53, 53), x + Setting.getCellSize()-10, y + Setting.getCellSize()-10, new Color(0, 0, 0));
        g2d.setPaint(highlight);
        g2d.fillRoundRect(x, y, Setting.getCellSize(), Setting.getCellSize(), 1, 1); // Используем roundRect для округления углов
    }
    private void draw3DCell1(Graphics2D g2d, int x, int y) {
        // Основной цвет клетки
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, Setting.getCellSize(), Setting.getCellSize());

        // Тень (нижняя и правая стороны)
        g2d.setColor(new Color(85, 83, 83));
        g2d.fillRect(x + Setting.getCellSize() - 4, y + 4, 4, Setting.getCellSize() - 4); // Правая тень
        g2d.fillRect(x + 4, y + Setting.getCellSize() - 4, Setting.getCellSize() - 4, 4); // Нижняя тень

        // Блик (верхняя и левая стороны)
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.fillRect(x, y, Setting.getCellSize() - 4, 4); // Верхний блик
        g2d.fillRect(x, y, 4, Setting.getCellSize() - 4); // Левый блик
    }

    private void drawNextTetromino(Graphics2D g){
       int sizeMatrix = (int)Math.sqrt(Tetromino.TableTetromineMatrix.Matrix(tetromino.getNextTetromino()).size());
        g.setColor(Color.WHITE); // Белая рамка
        g.drawRect((board.getWidth())*Setting.getCellSize(), (board.getHeight()-4-1)*Setting.getCellSize(), (5)*Setting.getCellSize(), (5)*Setting.getCellSize());

        for (int i = 0; i < Tetromino.TableTetromineMatrix.Matrix(tetromino.getNextTetromino()).size(); i++) {
            if (Tetromino.TableTetromineMatrix.Matrix(tetromino.getNextTetromino()).get(i) == 1) {
                int x = (board.getWidth()+1 + (i % sizeMatrix)) * Setting.getCellSize();
                int y = (board.getHeight()-sizeMatrix-1 + (i / sizeMatrix)) * Setting.getCellSize();
                draw3DCell1(g, x, y);
            }
        }
    }

    public void EndGameText(){
        label = new JLabel("THE END", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 50)); // Устанавливаем шрифт для текста
        label.setForeground(Color.RED); // Устанавливаем цвет текста
        label.setVisible(false);
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);

    }
    public void EndGame(){
        label.setVisible(true);
    }
}