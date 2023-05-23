package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Frame extends JFrame{
    private GamePanel gamePanel;
    private GameSelectionPanel gameSelectionPanel;
    private MainPanel mainPanel;
    private RecordsPanel recordsPanel;
    private ActionListener listener;
    
    public Frame(ActionListener actionListener){
        this.listener = actionListener; 
        gamePanel = new GamePanel(actionListener);
        gameSelectionPanel = new GameSelectionPanel(actionListener);
        mainPanel = new MainPanel(actionListener);
        recordsPanel = new RecordsPanel(actionListener);
        setTitle("Triki");
        setMaximumSize(new Dimension(700, 500));
        setMinimumSize(new Dimension(700, 500));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addListerBack();
        addListerPlay();
        addHistoryListener();
        add(mainPanel);
        setVisible(true);
    }
    private void addListerBack() {
        gameSelectionPanel.getBackButton().addActionListener(e -> {
                remove(gameSelectionPanel);
                add(mainPanel);
                revalidate();
                repaint();
        });
    }
    private void addListerPlay() {
        mainPanel.getPlayButton().addActionListener(e -> {
                remove(mainPanel);
                add(gameSelectionPanel);
                revalidate();
                repaint();
        });
    }
    private void addHistoryListener() {
        mainPanel.getRecordsButton().addActionListener(e -> {
                remove(mainPanel);
                add(recordsPanel);
                revalidate();
                repaint();
        });
        mainPanel.getRecordsButton().addActionListener(listener);
        mainPanel.getRecordsButton().setActionCommand("history");
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public GameSelectionPanel getGameSelectionPanel() {
        return gameSelectionPanel;
    }
    public void setGameSelectionPanel(GameSelectionPanel gameSelectionPanel) {
        this.gameSelectionPanel = gameSelectionPanel;
    }
    public MainPanel getMainPanel() {
        return mainPanel;
    }
    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    public RecordsPanel getRecordsPanel() {
        return recordsPanel;
    }
    public void setRecordsPanel(RecordsPanel recordsPanel) {
        this.recordsPanel = recordsPanel;
    }
    public void showMessage(String string) {
        JOptionPane.showMessageDialog(this, string);
    }
    public void showMessage(String string, Color color) {
        JDialog dialog = new JDialog(this, "Informacion", true);
        dialog.setLayout(new FlowLayout());
        dialog.getContentPane().setBackground(color);
        JLabel label = new JLabel(string);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(label);
        JButton button = new JButton("Regresar");
        button.addActionListener(e -> {
                dialog.dispose();
                remove(gamePanel);
                add(mainPanel);
                revalidate();
                repaint();
        });
        dialog.add(button);
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(this);
        dialog.setUndecorated(true);
        dialog.setVisible(true);
        dialog.pack();
    }
    public void setDefaultGamePanel() {
        
        remove(gamePanel);
        gamePanel = new GamePanel(listener);
        add(gamePanel);
        revalidate();
        repaint();
    }
    public void setDefaultGameSelectionPanel() {
        gameSelectionPanel.getNameTextField().setText("");
        gameSelectionPanel.getBox().setSelectedIndex(0);
    }

}
