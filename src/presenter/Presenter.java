package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

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
    }

    public void game() {
        while (!ticTacToe.isFullBoard() && !ticTacToe.checkGameOver()) {
            userTurn();
            // view.showGraphicMessage(ticTacToe.showBoard());
            if (ticTacToe.checkGameOver()) {
                break;
            }
            machineTurn();
            // view.showGraphicMessage(ticTacToe.showBoard());
            if (ticTacToe.checkGameOver()) {
                break;
            }
        }
        ticTacToe.saveResults();
    }

    public void userTurn() {
        try {
            // short row = view.readGraphicShort("Ingrese la fila (1 al 3)");
            // short col = view.readGraphicShort("Ingrese la columna (1 al 3)");
            // ticTacToe.playerTurn((row - 1), (col - 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            // view.showErrorMessage(e.getMessage());
            userTurn();
        }
    }

    public void machineTurn() {
        ticTacToe.machineTurn(new Random(), new Random());
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
                System.out.println(user);
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
