package model;

import java.util.List;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Triki {
    private Persistencia persistencia;
    private String[][] triki = { { "v", "v", "v" }, { "v", "v", "v" }, { "v", "v", "v" } };
    private List<Player> players;
    private boolean finish = false;

    public Triki() {
        try {
            players = new Persistencia().myRead();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean verifyPosition(int x, int y) {
        boolean result = false;
        if (triki[x][y] == "v") {
            result = true;
        }
        return result;
    }

    public void markPosition(int x, int y, String turn) {
        if (verifyPosition(x, y)) {
            triki[x][y] = turn;
            verifyGameIsEnded(turn);
        }
    }

    public String botSelect(String turn) {
        i    int x = (int) (Math.random() * 3);
        int y = (int) (Math.random() * 3);
        if (gameHas2InLine(turn)) {
            x = Integer.parseInt(botSelect2InLine(turn).substring(1));
            y = Integer.parseInt(botSelect2InLine(turn).substring(0, 1));
            markPosition(y, x, turn);
            return x + "" + y;
        } else if (gameHas2InLine(turn == "X" ? "O" : "X")) {
            x = Integer.parseInt(botSelect2InLine(turn == "X" ? "O" : "X").substring(1));
            y = Integer.parseInt(botSelect2InLine(turn == "X" ? "O" : "X").substring(0, 1));
            markPosition(y, x, turn);
            return x + "" + y;
        } else {
            while (!verifyPosition(x, y)) {
                x = (int) (Math.random() * 3);
                y = (int) (Math.random() * 3);
            }
        }
        markPosition(x, y, turn);
        return y + "" + x;
    }

    private String botSelect2InLine(String turn) {
        if (verifyHorizontal2InLine(turn)) {
            return botSelectHorizontal2InLine(turn);
        } else if (verifyVertical2InLine(turn)) {
            return botSelectVertical2InLine(turn);
        } else if (verifyDiagonal2InLine(turn)) {
            return botSelectDiagonal2InLine(turn);
        } else {
            return "";
        }
    }

    private String botSelectDiagonal2InLine(String turn) {
        if (triki[0][0] == turn && triki[1][1] == turn && triki[2][2] == "v") {
            return "22";
        } else if (triki[0][0] == turn && triki[1][1] == "v" && triki[2][2] == turn) {
            return "11";
        } else if (triki[0][0] == "v" && triki[1][1] == turn && triki[2][2] == turn) {
            return "00";
        } else if (triki[0][2] == turn && triki[1][1] == turn && triki[2][0] == "v") {
            return "20";
        } else if (triki[0][2] == turn && triki[1][1] == "v" && triki[2][0] == turn) {
            return "11";
        } else if (triki[0][2] == "v" && triki[1][1] == turn && triki[2][0] == turn) {
            return "02";
        } else {
            return "";
        }
    }

    private String botSelectVertical2InLine(String turn) {
        if (triki[0][0] == turn && triki[1][0] == turn && triki[2][0] == "v") {
            return "20";
        } else if (triki[0][0] == turn && triki[1][0] == "v" && triki[2][0] == turn) {
            return "10";
        } else if (triki[0][0] == "v" && triki[1][0] == turn && triki[2][0] == turn) {
            return "00";
        } else if (triki[0][1] == turn && triki[1][1] == turn && triki[2][1] == "v") {
            return "21";
        } else if (triki[0][1] == turn && triki[1][1] == "v" && triki[2][1] == turn) {
            return "11";
        } else if (triki[0][1] == "v" && triki[1][1] == turn && triki[2][1] == turn) {
            return "01";
        } else if (triki[0][2] == turn && triki[1][2] == turn && triki[2][2] == "v") {
            return "22";
        } else if (triki[0][2] == turn && triki[1][2] == "v" && triki[2][2] == turn) {
            return "12";
        } else if (triki[0][2] == "v" && triki[1][2] == turn && triki[2][2] == turn) {
            return "02";
        } else {
            return "";
        }
    }

    private String botSelectHorizontal2InLine(String turn) {
        if (triki[0][0] == turn && triki[0][1] == turn && triki[0][2] == "v") {
            return "02";
        } else if (triki[0][0] == turn && triki[0][1] == "v" && triki[0][2] == turn) {
            return "01";
        } else if (triki[0][0] == "v" && triki[0][1] == turn && triki[0][2] == turn) {
            return "00";
        } else if (triki[1][0] == turn && triki[1][1] == turn && triki[1][2] == "v") {
            return "12";
        } else if (triki[1][0] == turn && triki[1][1] == "v" && triki[1][2] == turn) {
            return "11";
        } else if (triki[1][0] == "v" && triki[1][1] == turn && triki[1][2] == turn) {
            return "10";
        } else if (triki[2][0] == turn && triki[2][1] == turn && triki[2][2] == "v") {
            return "22";
        } else if (triki[2][0] == turn && triki[2][1] == "v" && triki[2][2] == turn) {
            return "21";
        } else if (triki[2][0] == "v" && triki[2][1] == turn && triki[2][2] == turn) {
            return "20";
        } else {
            return "";
        }
    }

    private boolean gameHas2InLine(String turn) {
        return verifyHorizontal2InLine(turn) || verifyVertical2InLine(turn) || verifyDiagonal2InLine(turn);
    }

    private boolean verifyDiagonal2InLine(String turn) {
        if (triki[0][0] == turn && triki[1][1] == turn && triki[2][2] == "v") {
            return true;
        } else if (triki[0][0] == turn && triki[1][1] == "v" && triki[2][2] == turn) {
            return true;
        } else if (triki[0][0] == "v" && triki[1][1] == turn && triki[2][2] == turn) {
            return true;
        } else if (triki[0][2] == turn && triki[1][1] == turn && triki[2][0] == "v") {
            return true;
        } else if (triki[0][2] == turn && triki[1][1] == "v" && triki[2][0] == turn) {
            return true;
        } else if (triki[0][2] == "v" && triki[1][1] == turn && triki[2][0] == turn) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyVertical2InLine(String turn) {
        if (triki[0][0] == turn && triki[1][0] == turn && triki[2][0] == "v") {
            return true;
        } else if (triki[0][0] == turn && triki[1][0] == "v" && triki[2][0] == turn) {
            return true;
        } else if (triki[0][0] == "v" && triki[1][0] == turn && triki[2][0] == turn) {
            return true;
        } else if (triki[0][1] == turn && triki[1][1] == turn && triki[2][1] == "v") {
            return true;
        } else if (triki[0][1] == turn && triki[1][1] == "v" && triki[2][1] == turn) {
            return true;
        } else if (triki[0][1] == "v" && triki[1][1] == turn && triki[2][1] == turn) {
            return true;
        } else if (triki[0][2] == turn && triki[1][2] == turn && triki[2][2] == "v") {
            return true;
        } else if (triki[0][2] == turn && triki[1][2] == "v" && triki[2][2] == turn) {
            return true;
        } else if (triki[0][2] == "v" && triki[1][2] == turn && triki[2][2] == turn) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyHorizontal2InLine(String turn) {
        if (triki[0][0] == turn && triki[0][1] == turn && triki[0][2] == "v") {
            return true;
        } else if (triki[0][0] == turn && triki[0][1] == "v" && triki[0][2] == turn) {
            return true;
        } else if (triki[0][0] == "v" && triki[0][1] == turn && triki[0][2] == turn) {
            return true;
        } else if (triki[1][0] == turn && triki[1][1] == turn && triki[1][2] == "v") {
            return true;
        } else if (triki[1][0] == turn && triki[1][1] == "v" && triki[1][2] == turn) {
            return true;
        } else if (triki[1][0] == "v" && triki[1][1] == turn && triki[1][2] == turn) {
            return true;
        } else if (triki[2][0] == turn && triki[2][1] == turn && triki[2][2] == "v") {
            return true;
        } else if (triki[2][0] == turn && triki[2][1] == "v" && triki[2][2] == turn) {
            return true;
        } else if (triki[2][0] == "v" && triki[2][1] == turn && triki[2][2] == turn) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyGame(String turn) {
        return verifyHorizontal(turn) || verifyVertical(turn) || verifyDiagonal(turn);
    }

    private boolean verifyDiagonal(String turn) {
        if (triki[0][0] == turn && triki[1][1] == turn && triki[2][2] == turn) {
            return true;
        } else if (triki[0][2] == turn && triki[1][1] == turn && triki[2][0] == turn) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyVertical(String turn) {
        if (triki[0][0] == turn && triki[1][0] == turn && triki[2][0] == turn) {
            return true;
        } else if (triki[0][1] == turn && triki[1][1] == turn && triki[2][1] == turn) {
            return true;
        } else if (triki[0][2] == turn && triki[1][2] == turn && triki[2][2] == turn) {
            return true;
        } else {
            return false;
        }

    }

    private boolean verifyHorizontal(String turn) {
        if (triki[0][0] == turn && triki[0][1] == turn && triki[0][2] == turn) {
            return true;
        } else if (triki[1][0] == turn && triki[1][1] == turn && triki[1][2] == turn) {
            return true;
        } else if (triki[2][0] == turn && triki[2][1] == turn && triki[2][2] == turn) {
            return true;
        } else {
            return false;

        }
    }

    public boolean tie() {
        boolean result = false;
        if (!myToString().contains("v")) {
            result = true;
        }
        return result;
    }

    public void verifyGameIsEnded(String turn) {
        if (verifyGame(turn) || tie()) {
            finish = true;
        }
    }

    public boolean getFinish() {
        return finish;
    }

    public String myToString() {
        String stringTriki = "";
        for (int i = 0; i < triki.length; i++) {
            for (int j = 0; j < triki.length; j++) {
                stringTriki += triki[i][j] + ",";
            }
        }
        return stringTriki;
    }

    public void addPlayer(String name, String shape) {
        players.add(new Player(name, "", shape, new Date()));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void saveData() {
        try {
            persistencia = new Persistencia();
            persistencia.myWrite(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFinished(boolean finished) {
        this.finish = finished;
    }

    public void setDefaultGame() {
        triki = new String[][] { { "v", "v", "v" }, { "v", "v", "v" }, { "v", "v", "v" } };
        finish = false;
    }

}