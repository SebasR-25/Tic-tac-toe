package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private JPanel labelPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JButton playButton;
    private JButton recordsButton;

    public MainPanel(ActionListener actionListener) {
        setVisible(true);
        addComp();
    }

    private void addComp() {
        labelPane();
        buttonPane();
        add(labelPanel);
        add(buttonPanel);
    }

    private void labelPane() {
        labelPanel.setPreferredSize(new Dimension(700, 220));
        labelPanel.setLayout(null);
        JLabel label = new JLabel("BIENVENIDO A TIC TAC TOE", SwingConstants.CENTER);
        label.setFont(new Font("times new roman", Font.BOLD, 30));
        label.setBounds(120, 100, 460, 20);
        labelPanel.setVisible(true);
        labelPanel.add(label);
    }

    private void buttonPane() {
        buttonPanel.setPreferredSize(new Dimension(700, 280));
        buttonPanel.setLayout(null);
        playButton = new JButton("JUGAR");
        playButton.setBounds(300, 50, 100,40);
        recordsButton = new JButton("VER HISTORIAL DE PARTIDAS");
        recordsButton.setBounds(240, 140, 220,40);
        buttonPanel.add(playButton);
        buttonPanel.add(recordsButton);
    }

    public AbstractButton getPlayButton() {
        return playButton;
    }
    public JButton getRecordsButton() {
        return recordsButton;
    }
}
