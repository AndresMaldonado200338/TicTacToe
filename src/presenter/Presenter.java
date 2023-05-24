package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import model.*;
import view.*;

public class Presenter implements ActionListener {
    private TicTacToe ticTacToe;
    private User user;
    private ViewGUI viewGUI;

    public Presenter() {
        ticTacToe = new TicTacToe();
        viewGUI = new ViewGUI(this);
        user = new User();
    }

    public void userCreate() {
        viewGUI.playMenu();
    }

    public void history() {
        viewGUI.historyMenu();
    }

    public void gameScreen() {
        viewGUI.gameMenu(user);
        ticTacToe.chooseFigure(user);
        ticTacToe.initBoard();
    }

    public void match() {
        JRadioButton radioButton = new JRadioButton();
        while (!ticTacToe.getGameOver() && !ticTacToe.isFullBoard()) {
            for (int i = 0; i < viewGUI.getRadioButtons().length; i++) {
                for (int j = 0; j < viewGUI.getRadioButtons()[i].length; j++) {
                    if (radioButton == viewGUI.getRadioButtons()[i][j]) {
                        ticTacToe.playerTurn(i, j);
                        if (ticTacToe.checkForWinner(ticTacToe.getPlayer())) {
                            ticTacToe.checkGameOver();
                        }
                        ticTacToe.machineTurn(new Random(), new Random());
                        if (ticTacToe.checkForWinner(ticTacToe.getComputer())) {
                            ticTacToe.checkGameOver();
                        }
                    }
                }
            }
        }
        ticTacToe.saveResults();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(viewGUI.getPlayButton().getText())) {
            userCreate();
        }
        if (command.equals(viewGUI.getHistoryButton().getText())) {
            history();
        }
        if (command.equals(viewGUI.getContinueButton().getText())) {
            if (viewGUI.getUserText().getText().isEmpty()
                    || viewGUI.getFigureChoose().getSelectedItem().equals("Select")) {
                JOptionPane.showMessageDialog(viewGUI.getMainFrame(), "Error, por favor completar los campos", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                user.setUserName(viewGUI.getUserText().getText());
                String userFigure = viewGUI.getFigureChoose().getSelectedItem().toString();
                Figure figure = (userFigure.equals("Circle")) ? Figure.CIRCLE : Figure.CROSS;
                user.setUserFigure(figure);
                viewGUI.getMainFrame().dispose();
                gameScreen();
            }
        }
        if (command.equals(viewGUI.getBackButton().getText())) {
            viewGUI.getMainFrame().dispose();
            viewGUI.mainMenu();
        }
    }

    public static void main(String[] args) {
        new Presenter();
    }
}