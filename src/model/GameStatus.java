package model;

public enum GameStatus {
    WINNER("Ganador"), LOSER("Perdedor"), TIE("Empate");

    private String statusName;

    private GameStatus(String statusName){
        this.statusName = statusName;
    }

    public String getStatusName(){
        return statusName;
    }
}
