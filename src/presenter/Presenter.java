package presenter;

import java.util.Random;

import model.*;
import view.*;

public class Presenter {
    private TicTacToe ticTacToe;
    private User user;
    private View view;

    public Presenter() {
        ticTacToe = new TicTacToe();
        view = new View();
        user = new User();
        play();
    }

    public void play() {
        ticTacToe.initBoard();
        int option = view.readGraphicInt("Bienvenido a Triki\n1. Jugar\n2. Salir");
        switch (option) {
            case 1:
                game();
                play();
                break;
            case 2:
                view.showGraphicMessage("Gracias por jugar");
                System.exit(0);
            default:
                view.showErrorMessage("Opcion no valida");
                play();
        }
    }

    public void createUser() {
        user.setUserName(view.readGraphicString("Ingrese su nombre"));
        view.showGraphicMessage("usuario registrado como: " + user.getUserName());
    }

    public void chooseFigure() {
        short option = view.readGraphicShort("Ingrese la figura que desea usar\n1. CIRCULO\n2. EQUIS");
        try {
            switch (option) {
                case 1:
                    user.setUserFigure(Figure.CIRCLE);
                    view.showGraphicMessage("Figura creada con exito: " + user.getUserFigure());
                    break;
                case 2:
                    user.setUserFigure(Figure.CROSS);
                    view.showGraphicMessage("Figura creada con exito: " + user.getUserFigure());
                    break;
                default:
                    view.showGraphicMessage("Opcion no valida");
                    break;
            }
            ticTacToe.chooseFigure(user);
        } catch (NullPointerException e) {
            view.showErrorMessage(e.getMessage());
        }

    }

    public void game() {
        createUser();
        chooseFigure();
        while (!ticTacToe.isFullBoard() && !ticTacToe.checkGameOver()) {
            userTurn();
            view.showGraphicMessage(ticTacToe.showBoard());
            if (ticTacToe.checkGameOver()) {
                break;
            }
            machineTurn();
            view.showGraphicMessage(ticTacToe.showBoard());
            if (ticTacToe.checkGameOver()) {
                break;
            }
        }
        ticTacToe.saveResults();
    }

    public void userTurn() {
        try {
            short row = view.readGraphicShort("Ingrese la fila (1 al 3)");
            short col = view.readGraphicShort("Ingrese la columna (1 al 3)");
            ticTacToe.playerTurn((row - 1), (col - 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            view.showErrorMessage(e.getMessage());
            userTurn();
        }
    }

    public void machineTurn() {
        ticTacToe.machineTurn(new Random(), new Random());
    }

    public static void main(String[] args) {
        new Presenter();
    }
}
