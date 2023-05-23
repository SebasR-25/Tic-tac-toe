package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Player;

public class RecordsPanel extends JPanel {
    private List<Player> players = new ArrayList<>();
    private JLabel label = new JLabel("HISTORIAL DE PARTIDAS");
    private JTable table;

    public RecordsPanel(ActionListener actionListener) {
        setLayout(null);
        addComp();
        setVisible(true);
    }

    private void addComp() {
        label.setBounds(250, 20, 200, 20);
        add(label);
        table = new JTable();
        fillTable();
        tableFeatures();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 600, 380);
        add(scrollPane);
    }

    public void fillTable() {
        String[] columnNames = {"Nombre", "Resultado", "Figura", "Fecha"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        sortPlayers();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Player player : players) {
            model.addRow(new String[]{player.getName(), player.getStatus(), player.getFigure(),
                    formatter.format(player.getDateTime())});
        }
        table.setModel(model);
        changeColor();
    }

    private void changeColor() {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (players.get(row).getStatus().equals("Ganador"))
                    component.setBackground(Color.GREEN.brighter());
                else if (players.get(row).getStatus().equals("Perdedor"))
                    component.setBackground(Color.RED.brighter());
                else
                    component.setBackground(Color.GRAY.brighter());
                return component;
            }
        });
    }

    private void sortPlayers() {
        players.sort((p2, p1) -> p1.getDateTime().compareTo(p2.getDateTime()));
    }

    private void tableFeatures() {
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        table.setRowHeight(30);
        table.setEnabled(false);
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void updateTable() {

    }
}
