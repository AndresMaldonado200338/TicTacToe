package model;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.JFrame;
import view.ImagePanel;

public class TicTacToe {
    private String[][] board;
    private User user;
    private String player;
    private String computer;
    private boolean gameOver;
    private GameStatus gameStatus;
    private LocalDateTime gameTime;
    private DateTimeFormatter formatter;
    private String path = "Triki/src/resources/History/history_game.txt";

   
    private JFrame frame;
    private ImagePanel imagePanel;

    /**
     * 
     */
    public TicTacToe() {
        board = new String[3][3];
        gameOver = false;
        gameTime = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 300));

      
        imagePanel = new ImagePanel("IconMain.png"); 
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);

      
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getGameStatus() {
        return gameStatus.getStatusName();
    }

    public void initBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = "-";
            }
        }
    }

    public String showBoard() {
        String myBoard = "-------------\n";
        for (int row = 0; row < board.length; row++) {
            myBoard += "| ";
            for (int col = 0; col < board[row].length; col++) {
                myBoard += board[row][col] + " | ";
            }
            myBoard += "\n-------------\n";
        }
        return myBoard;
    }

    public void chooseFigure(User user) {
        this.user = user;
        player = user.getUserFigure();
        computer = (player.equals("X")) ? "O" : "X";
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == "-";
    }

    public boolean isFullBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == "-") {
                    return false;
                }
            }
        }
        return true;
    }

    public void removeFigure(int row, int col) {
        board[row][col] = "-";
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }

    public void playerTurn(int i, int j) {
        placeFigure(i, j, player);
    }

    public void machineTurn(Random row, Random column) {
        boolean machinePlaced = tryToWin(computer);
        if (!machinePlaced) {
            machinePlaced = tryToBlock(user.getUserFigure());
        }
        if (!machinePlaced) {
            placeRandomFigure(row, column);
        }
    }

    private boolean tryToWin(String figure) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (isCellEmpty(row, col)) {
                    placeFigure(row, col, figure);
                    if (checkForWinner(figure)) {
                        return true;
                    } else {
                        removeFigure(row, col);
                    }
                }
            }
        }
        return false;
    }

    private boolean tryToBlock(String figure) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (isCellEmpty(row, col)) {
                    placeFigure(row, col, player);
                    boolean playerWinningMove = checkForWinner(player);
                    removeFigure(row, col);
                    if (playerWinningMove) {
                        placeFigure(row, col, computer);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void placeRandomFigure(Random rows, Random column) {
        int row = rows.nextInt(board.length);
        int col = column.nextInt(board.length);
        if (isCellEmpty(row, col)) {
            placeFigure(row, col, computer);
        }
    }

    public void placeFigure(int row, int col, String figure) {
        if (isValidPosition(row, col) && isCellEmpty(row, col)) {
            board[row][col] = figure;
            imagePanel.repaint(); 
        }
    }

    public boolean checkForWinner(String figure) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == figure && board[row][1] == figure && board[row][2] == figure) {
                return true;
            }
        }
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == figure && board[1][col] == figure && board[2][col] == figure) {
                return true;
            }
        }
        if (board[0][0] == figure && board[1][1] == figure && board[2][2] == figure) {
            return true;
        }
        if (board[0][2] == figure && board[1][1] == figure && board[2][0] == figure) {
            return true;
        }
        return false;
    }

    public boolean checkGameOver() {
        if (checkForWinner(user.getUserFigure())) {
            setGameStatus(GameStatus.WINNER);
            System.out.println(getGameStatus());
            gameOver = true;
            return true;
        } else if (checkForWinner(computer)) {
            setGameStatus(GameStatus.LOSER);
            System.out.println(getGameStatus());
            gameOver = true;
            return gameOver;
        } else if (isFullBoard()) {
            setGameStatus(GameStatus.TIE);
            System.out.println(getGameStatus());
            gameOver = true;
            return gameOver;
        }
        return gameOver;
    }

    public void saveResults() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(user.getUserName() + ";" + getGameStatus() + ";" + user.getUserFigure() + ";"
                    + gameTime.format(formatter));
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}