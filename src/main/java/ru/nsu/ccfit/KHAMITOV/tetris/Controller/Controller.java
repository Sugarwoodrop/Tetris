package ru.nsu.ccfit.KHAMITOV.tetris.Controller;

import ru.nsu.ccfit.KHAMITOV.tetris.Model.Board.Board;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Tetromino.Tetromino;
import ru.nsu.ccfit.KHAMITOV.tetris.View.RenderBoard;
import ru.nsu.ccfit.KHAMITOV.tetris.View.RenderMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class Controller {
    private final int TARGET_FPS = 200;
    private double OPTIMAL_TIME = 1_000_000_000.0 / TARGET_FPS;

    private long lastMoveTime = 0;
    private long lastRotationTime = 0;
    private long lastDropTime = 0;
    private long moveDelay = 110;  // 150 мс перед началом
    private long dropDelay = 150;// Начальное время падения (1 сек)
    private double delta;
    private long lastTime;

    private RenderMenu view;
    private JFrame frame;
    private Board board;
    private RenderBoard renderBoard;
    private Tetromino tetromino;
    private Queue<Integer> queueOrdertetromino = new LinkedList<>();
    private Queue<Integer> bofferQueueOrdertetromino = new LinkedList<>();

    private boolean gameStateChanged = false;
    private boolean isGameStateChanged = true;

    public Controller(RenderMenu menu, JFrame frame){
        this.view = menu;
        this.frame = frame;

        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        view.getTableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTable();
            }
        });
        view.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitGame();
            }
        });
    }

    private void startGame() {
        // Логика начала игры
        board = new Board(21,15);
        isGameStateChanged = true;
        queueOrdertetromino.clear();
        view.clearBoard();

        queueOrdertetromino = FillingQueue(7);

        bofferQueueOrdertetromino = FillingQueue(7);

        tetromino = new Tetromino(queueOrdertetromino.poll(), 10, -3);
        tetromino.setNextTetromino(queueOrdertetromino.peek());
        renderBoard = new RenderBoard(board, tetromino);
        renderBoard.EndGameText();
        GameInputHandler inputHandler = new GameInputHandler();

        frame.addKeyListener(inputHandler);
        frame.setFocusable(true);

        view.addBoard(renderBoard);
        frame.setSize((board.getWidth()+6)*30, (board.getHeight()+3)*30);
        frame.setLocationRelativeTo(null);

        view.showGamePanel();
        new Thread(() -> GameLoop(inputHandler)).start();
    }

    // Показать таблицу (возможно, таблица с результатами)
    private void showTable() {

    }

    // Завершение игры
    private void exitGame() {
        System.exit(0);
    }

    private void GameLoop(GameInputHandler inputHandler){
        while (isGameStateChanged){
            long now = System.nanoTime();
            delta += (now - lastTime) / OPTIMAL_TIME;
            lastTime = now;


            while (delta >=1) {
                Update(inputHandler);
                delta--;
            }

            if(gameStateChanged) {
                SwingUtilities.invokeLater(() -> view.repaint());
                gameStateChanged = false;
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> view.showMenuPanel());
    }

    private void Update(GameInputHandler inputHandler){
        long now = System.currentTimeMillis();
        Drop(now);
        Move(inputHandler);

        Fell();

        Rotation(inputHandler, now);
    }

    private void Move(GameInputHandler inputHandler){
        long now = System.currentTimeMillis();
        if (inputHandler.isLeft()  && now - lastMoveTime > moveDelay) {
            if(board.isPossiblePut(tetromino.getTetromineMatrix(), tetromino.getX()-1, tetromino.getY())) {
                tetromino.MoveLeftAndRight(1);
                lastMoveTime = now;
                gameStateChanged = true;
            }
        }
        if (inputHandler.isRight()  && now - lastMoveTime > moveDelay) {
            if(board.isPossiblePut(tetromino.getTetromineMatrix(), tetromino.getX()+1, tetromino.getY())){
                tetromino.MoveLeftAndRight(2);
                lastMoveTime = now;
                gameStateChanged =true;
            }
        }
    }
    private void Drop(long now){
        if (now - lastDropTime > dropDelay) {
            tetromino.MoveDown();
            lastDropTime = now;
            gameStateChanged = true;
        }
    }
    private void Rotation(GameInputHandler inputHandler, long now){
        if (inputHandler.isUp() && now - lastRotationTime > moveDelay) {
            tetromino.Rotation(board,2);
            lastRotationTime = now;
            gameStateChanged = true;
        }
        if (inputHandler.isDown() && now - lastRotationTime > moveDelay) {
            tetromino.Rotation(board,1);
            lastRotationTime = now;
            gameStateChanged = true;
        }
    }
    private void Fell(){
        if(!board.isPossiblePut(tetromino.getTetromineMatrix(), tetromino.getX(), tetromino.getY())){
            board.addFloor(tetromino.getTetromineMatrix(), tetromino.getX(), tetromino.getY());

            if(queueOrdertetromino.isEmpty()){
                queueOrdertetromino = bofferQueueOrdertetromino;

                bofferQueueOrdertetromino = FillingQueue(7);

            }

            board.AllLine();

            tetromino.setTetromineMatrix(queueOrdertetromino.poll());
            tetromino.setX(10);
            tetromino.setY(0);
            if(!queueOrdertetromino.isEmpty()) {
                tetromino.setNextTetromino(queueOrdertetromino.peek());
            }
            else {
                tetromino.setNextTetromino(bofferQueueOrdertetromino.peek());
            }
            gameStateChanged = true;

            if (board.isEnd()) {
                renderBoard.EndGame();
                isGameStateChanged = false;
            }
        }
    }

    private LinkedList<Integer> FillingQueue(int quantityTeromino) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < quantityTeromino; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return new LinkedList<>(numbers);
    }

    public class GameInputHandler implements KeyListener {
        private boolean up, down, left, right;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> up = true;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = true;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> left = true;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> right = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> up = false;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = false;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> left = false;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> right = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        public boolean isUp() { return up; }
        public boolean isDown() { return down; }
        public boolean isLeft() { return left; }
        public boolean isRight() { return right; }
    }

}
