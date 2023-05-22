package model;

public enum Figure {
    CIRCLE("O"), CROSS("X");

    private String figureText;

    private Figure(String figureText) {
        this.figureText = figureText;
    }

    public String getFigureText(){
        return figureText;
    }
}
