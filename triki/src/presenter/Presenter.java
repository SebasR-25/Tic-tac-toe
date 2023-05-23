package presenter;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.*;
import view.*;

public class Presenter implements ActionListener {
    private Triki triki;
    private Frame frame;

    public Presenter() {
        triki = new Triki();
        frame = new Frame(this);
    }

    public void play() {
        while (!triki.getFinish()) {

        }
    }

    public static void main(String[] args) {
        Presenter presenter = new Presenter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "continue":
            addNewPlayer();
                if (!frame.getGameSelectionPanel().getNameTextField().getText().equals("")) {
                    restartGame();
                }
                break;
            case "history":
                loadHistory();
                break;
            case "back":
                break;
            case "exit":
                break;
            case "save":
                break;
            case "load":
                break;
            case "reset":
                break;
            case "mark":
                markButton(e);
                break;
        }
    }

    private void restartGame() {
        triki.setDefaultGame();
        frame.setDefaultGamePanel();
        frame.setDefaultGameSelectionPanel();
    }

    private void loadHistory() {
        frame.getRecordsPanel().setPlayers(triki.getPlayers());
        frame.getRecordsPanel().fillTable();
    }

    private void markButton(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Player player = triki.getPlayers().get(triki.getPlayers().size() - 1);
        int x = Integer.parseInt(button.getName().substring(0, 1));
        int y = Integer.parseInt(button.getName().substring(1, 2));
        boolean isCircle = player.getFigure().equals("Circulo");
        if (isCircle) {
            Image image = new ImageIcon("src/resources/Circulo.jpeg").getImage();
            button.setIcon(new ImageIcon(image.getScaledInstance(145, 145, Image.SCALE_SMOOTH)));
            triki.markPosition(y, x, "O");
        } else {
            Image image = new ImageIcon("src/resources/Cruz.png").getImage();
            button.setIcon(new ImageIcon(image.getScaledInstance(145, 145, Image.SCALE_SMOOTH)));
            triki.markPosition(y, x, "X");
        }
        System.out.println(triki.myToString());

        frame.revalidate();
        frame.repaint();

        button.setEnabled(false);
        if (triki.getFinish()) {
            if (triki.tie()) {
                frame.showMessage("El juego ha terminado en empate", Color.gray);
                player.setStatus("Empate");
            } else {
                frame.showMessage("El jugador ha ganado", Color.green);
                player.setStatus("Ganador");
            }
            frame.getGamePanel().setAllButtonUnable();
            player.setDateTime(new Date());
            triki.saveData();
        } else {
            String bootButtonName = triki.botSelect(isCircle ? "X" : "O");
            JButton bootButton = frame.getGamePanel().getButton(bootButtonName);
            Image image = new ImageIcon(isCircle ? "src/resources/Cruz.jpeg" : "src/resources/Circulo.jpeg").getImage();
            bootButton.setIcon(new ImageIcon(image.getScaledInstance(145, 145, Image.SCALE_SMOOTH)));
            bootButton.setEnabled(false);

            System.out.println(triki.myToString());
            frame.revalidate();
            frame.repaint();
            if (triki.getFinish()) {
                frame.showMessage("El jugador ha perdido", Color.red);
                player.setStatus("Perdedor");
                player.setDateTime(new Date());
                frame.getGamePanel().setAllButtonUnable();
                triki.saveData();
            }
            frame.revalidate();
            frame.repaint();
        }

    }

    private void addNewPlayer() {
        String name = frame.getGameSelectionPanel().getNameTextField().getText();
        String shape = frame.getGameSelectionPanel().getBox().getSelectedItem().toString();
        if (name.equals("")) {
            frame.showMessage("Ingrese un nombre");
        } else {
            triki.addPlayer(name, shape);
            frame.remove(frame.getGameSelectionPanel());
            frame.add(frame.getGamePanel());
            frame.getGamePanel().setPlayerName(name);
            frame.getGamePanel().setPlayerShape(shape);
            frame.revalidate();
            frame.repaint();
        }
    }
}