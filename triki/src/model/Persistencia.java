package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    BufferedReader br = null;
    BufferedWriter bw = null;

    public Persistencia() throws IOException {
        br = new BufferedReader(new FileReader("triki/src/resources/Hystory_game.txt"));
        
    }

    public void myWrite(List<Player> list) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        try {
            bw = new BufferedWriter(new FileWriter("triki/src/resources/Hystory_game.txt", false));
            for (Player player : list) {
                bw.write(player.getName() + ";" + player.getStatus() + ";" + player.getFigure() + ";"
                        + formatter.format(player.getDateTime()));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> myRead() throws IOException, ParseException {
        ArrayList<Player> list = new ArrayList<>();
        String info = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        while ((info = br.readLine()) != null) {
            list.add(new Player(info.split(";")[0], info.split(";")[1], info.split(";")[2],
                    formatter.parse(info.split(";")[3])));
        }
        br.close();
        return list;
    }
}
