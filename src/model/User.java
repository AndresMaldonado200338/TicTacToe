package model;

public class User {
    private String userName;
    private Figure figure;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserFigure(Figure figure) {
        this.figure = figure;
    }

    public String getUserFigure() {
        return figure.getFigureText();
    }

    @Override
    public String toString() {
        return "Usuario: " + getUserName() + "\nFigura: " + getUserFigure();
    }
}
