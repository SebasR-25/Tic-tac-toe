package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private JTabbedPane tabbed;
    private JPanel triki;
    private JPanel information;
    private JPanel about;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton boton5;
    private JButton boton6;
    private JButton boton7;
    private JButton boton8;
    private JButton boton9;
    private JLabel name;
    private JLabel nameLabel;
    private JLabel shape;
    private JLabel shapeLabel;
    private JLabel info;
    private Image uptcImage;
    private JLabel uptc;
    private ActionListener listener;

    public GamePanel(ActionListener actionListener) {
        this.listener = actionListener;
        setVisible(true);
        setLayout(null);
        initComp();
        addComp();
    }

    private void initComp() {
        initPanels();
        initButtons();
        buttonsEvent();
        addButtons();
        intiLabels();
        information.add(name);
        information.add(nameLabel);
        information.add(shape);
        information.add(shapeLabel);
        about.add(uptc);
        about.add(info);
        ubicateLabels();
        addFont();
        tabbed.setBounds(120, 10, 440, 440);
        tabbed.setTabPlacement(JTabbedPane.TOP);
    }

    private void buttonsEvent() {
        boton1.addActionListener(listener);
        boton2.addActionListener(listener);
        boton3.addActionListener(listener);
        boton4.addActionListener(listener);
        boton5.addActionListener(listener);
        boton6.addActionListener(listener);
        boton7.addActionListener(listener);
        boton8.addActionListener(listener);
        boton9.addActionListener(listener);
        boton1.setActionCommand("mark");
        boton2.setActionCommand("mark");
        boton3.setActionCommand("mark");
        boton4.setActionCommand("mark");
        boton5.setActionCommand("mark");
        boton6.setActionCommand("mark");
        boton7.setActionCommand("mark");
        boton8.setActionCommand("mark");
        boton9.setActionCommand("mark");
    }

    private void initPanels() {
        tabbed = new JTabbedPane();
        triki = new JPanel();
        information = new JPanel();
        about = new JPanel();
        triki.setPreferredSize(new Dimension(300, 200));
        triki.setLayout(new GridLayout(3, 3));
        information.setLayout(null);
        about.setLayout(null);
    }

    private void intiLabels() {
        uptcImage = new ImageIcon("src/resources/uptc.png").getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        name = new JLabel("<html>NOMBRE DEL JUGADOR:</html>");
        nameLabel = new JLabel("dasdasdasd");
        shape = new JLabel("FIGURA:");
        shapeLabel = new JLabel("dasdasdasd");
        info = new JLabel("<html><pre>Sebastian Rueda-202114011             Faltudad de Ingeniería<br>                                      Escuela de Ingeniería<br>                                      de Sistemas y<br>                                      Computación<br>                                      2023-1</pre></html>");
        uptc = new JLabel(new ImageIcon(uptcImage));    
    }

    private void addFont() {
        Font font = new Font("Arial", Font.BOLD, 20);
        name.setFont(font);
        nameLabel.setFont(font);
        shape.setFont(font);
        shapeLabel.setFont(font);
    }

    private void ubicateLabels() {
        name.setBounds(50, 115, 180, 60);
        nameLabel.setBounds(250, 130, 160, 20);
        shape.setBounds(50, 250, 90, 20);
        shapeLabel.setBounds(250, 250, 140, 20);
        info.setBounds(0, 0, 440,220);
        uptc.setBounds(0, 220, 440, 220);
    }

    private void initButtons() {
        boton1 = new JButton("00");
        boton2 = new JButton("01");
        boton3 = new JButton("02");
        boton4 = new JButton("10");
        boton5 = new JButton("11");
        boton6 = new JButton("12");
        boton7 = new JButton("20");
        boton8 = new JButton("21");
        boton9 = new JButton("22");
        boton1.setName("00");
        boton2.setName("01");
        boton3.setName("02");
        boton4.setName("10");
        boton5.setName("11");
        boton6.setName("12");
        boton7.setName("20");
        boton8.setName("21");
        boton9.setName("22");
    }

    private void addButtons() {
        triki.add(boton1);
        triki.add(boton2);
        triki.add(boton3);
        triki.add(boton4);
        triki.add(boton5);
        triki.add(boton6);
        triki.add(boton7);
        triki.add(boton8);
        triki.add(boton9);
    }

    private void addComp() {
        tabbed.addTab("Juego", triki);
        tabbed.addTab("Información del jugador", information);
        tabbed.addTab("Créditos", about);
        add(tabbed);
    }

    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }

    public JButton getButton(String bootButtonName) {
        switch (bootButtonName) {
            case "00":
                return boton1;
            case "01":
                return boton2;
            case "02":
                return boton3;
            case "10":
                return boton4;
            case "11":
                return boton5;
            case "12":
                return boton6;
            case "20":
                return boton7;
            case "21":
                return boton8;
            case "22":
                return boton9;
            default:
                return null;
        }
    }

    public void setAllButtonUnable() {
        boton1.setEnabled(false);
        boton2.setEnabled(false);
        boton3.setEnabled(false);
        boton4.setEnabled(false);
        boton5.setEnabled(false);
        boton6.setEnabled(false);
        boton7.setEnabled(false);
        boton8.setEnabled(false);
        boton9.setEnabled(false);
    }

    public void setPlayerName(String name) {
        nameLabel.setText(name);
    }

    public void setPlayerShape(String shape) {
        shapeLabel.setText(shape); 
    }
}
