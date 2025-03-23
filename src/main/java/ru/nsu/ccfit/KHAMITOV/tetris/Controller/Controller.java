package ru.nsu.ccfit.KHAMITOV.tetris.Controller;

import ru.nsu.ccfit.KHAMITOV.tetris.Model.Board.Board;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Score.Score;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Score.ScoreTable;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Setting.Setting;
import ru.nsu.ccfit.KHAMITOV.tetris.Model.Tetromino.Tetromino;
import ru.nsu.ccfit.KHAMITOV.tetris.View.RenderGame;
import ru.nsu.ccfit.KHAMITOV.tetris.View.RenderMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static java.lang.Math.pow;

public class Controller {
    private double delta;
    private long lastTime;
    private static long lastMoveTime = 0;
    private static long lastRotationTime = 0;
    private static long lastDropTime = 0;
    private double dropDelay = Setting.getStartDropDelay();
    private int lvl = 1;

    private RenderMenu view;
    private JFrame frame;
    private Board board;
    private RenderGame renderGame;
    private Tetromino tetromino;
    private Queue<Integer> queueOrdertetromino = new LinkedList<>();
    private Queue<Integer> bofferQueueOrdertetromino = new LinkedList<>();

    private boolean gameStateChanged = false;
    private boolean isGameStateChanged = true;
    private String nickname;
    private Score score = new Score();

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
                try {
                    showTable();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        view.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OkNikcname();
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
        view.showNicknamePanel();
    }

    // Показать таблицу (возможно, таблица с результатами)
    private void showTable() throws IOException {
        frame.setSize(Setting.getMenuSizewight(), Setting.getMenuSizeHeight());
        String[][] table = DataInString(Score.ReadRecords());
        JFrame tableFrame = new JFrame("Таблица Очков");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setSize(Setting.getTableSizeWight(), Setting.getTableSizeHeight());
        tableFrame.setLocationRelativeTo(null);
        tableFrame.add(view.ReadTableScore(table));
        tableFrame.setVisible(true);
    }

    private void OkNikcname() {
        nickname = view.getNickname();
        if (nickname.isEmpty()) {
            nickname = "Unknown ";
            LocalDateTime currentDateTime = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            nickname +=  currentDateTime.format(formatter);;
        }

        board = new Board(Setting.getHeightBoard(),Setting.getWidthBoard());
        isGameStateChanged = true;
        queueOrdertetromino.clear();
        view.clearBoard();

        queueOrdertetromino = FillingQueue(Setting.getNambersTetromino());

        bofferQueueOrdertetromino = FillingQueue(Setting.getNambersTetromino());

        tetromino = new Tetromino(queueOrdertetromino.poll(), board.getWidth()/2, -3);
        tetromino.setNextTetromino(queueOrdertetromino.peek());
        renderGame = new RenderGame(board, tetromino);
        renderGame.EndGameText();
        GameInputHandler inputHandler = new GameInputHandler();

        frame.addKeyListener(inputHandler);
        frame.setFocusable(true);

        view.addBoard(renderGame);
        frame.setSize((board.getWidth()+6)*Setting.getCellSize(), (board.getHeight()+3)*Setting.getCellSize());
        frame.setLocationRelativeTo(null);

        view.showGamePanel();
        new Thread(() -> {
            try {
                GameLoop(inputHandler);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // Завершение игры
    private void exitGame() {
        System.exit(0);
    }

    private void GameLoop(GameInputHandler inputHandler) throws IOException {
        while (isGameStateChanged){
            long now = System.nanoTime();
            delta += (now - lastTime) / Setting.getOPTIMAL_TIME();
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        score.WriteRecord(nickname);

        SwingUtilities.invokeLater(() -> view.showMenuPanel());
        frame.setSize(Setting.getMenuSizewight(), Setting.getMenuSizeHeight());
    }

    private void Update(GameInputHandler inputHandler){
        long now = System.currentTimeMillis();

        if(score.getScore() > Setting.getScoreForNextLvl()*lvl){
            lvl++;
            dropDelay = Setting.getStartDropDelay() * pow(Setting.getDelayReductionFactor(), lvl-1);
        }

        Drop(now);
        Move(inputHandler);

        Fell();

        Rotation(inputHandler, now);
    }

    private void Move(GameInputHandler inputHandler){
        long now = System.currentTimeMillis();
        if (inputHandler.isLeft()  && now - lastMoveTime > Setting.getMoveDelay()) {
            if(board.isPossiblePut(tetromino.getTetromineMatrix(), tetromino.getX()-1, tetromino.getY())) {
                tetromino.MoveLeftAndRight(Setting.getMoveLeft());
                lastMoveTime = now;
                gameStateChanged = true;
            }
        }
        if (inputHandler.isRight()  && now - lastMoveTime > Setting.getMoveDelay()) {
            if(board.isPossiblePut(tetromino.getTetromineMatrix(), tetromino.getX()+1, tetromino.getY())){
                tetromino.MoveLeftAndRight(Setting.getMoveRight());
                lastMoveTime = now;
                gameStateChanged =true;
            }
        }
        if(inputHandler.isSpace() && now - lastMoveTime > Setting.getFastDownDelay()) {
            while(board.isPossiblePut(tetromino.getTetromineMatrix(), tetromino.getX(), tetromino.getY() + 1)) { // Проверяем на следующий шаг
                tetromino.setY(tetromino.getY() + 1);
            }
            lastMoveTime = now;
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
        if (inputHandler.isUp() && now - lastRotationTime > Setting.getMoveDelay()) {
            tetromino.Rotation(board,Setting.getRotationRight());
            lastRotationTime = now;
            gameStateChanged = true;
        }
        if (inputHandler.isDown() && now - lastRotationTime > Setting.getMoveDelay()) {
            tetromino.Rotation(board,Setting.getRotationLeft());
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

            score.addScore(board.AllLine() * 50);

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
                renderGame.EndGame();
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
        private boolean up, down, left, right, space;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> up = true;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = true;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> left = true;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> right = true;
                case KeyEvent.VK_SPACE -> space = true;
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
                case KeyEvent.VK_SPACE -> space = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        public boolean isUp() { return up; }
        public boolean isDown() { return down; }
        public boolean isLeft() { return left; }
        public boolean isRight() { return right; }
        public boolean isSpace() { return space; }

    }

    private String[][] DataInString(ScoreTable table){
        String[] nick = table.getNickname().toArray(new String[0]);
        ArrayList<Integer> score = table.getScore();
        String[][] result = new String[score.size()][2];
        for(int i = 0; i<score.size(); i++){
            result[i][0] = nick[i];
            result[i][1] = String.valueOf(score.get(i));
        }

        return result;
    }
}
