package view;

import java.awt.event.ActionListener;

import javax.swing.*;

public class GameSelectionPanel extends JPanel {
    private String [] shapes = {"Circulo", "Cruz"};
    private JLabel label;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel shapeLabel;
    private JComboBox<String> box;
    private JButton backButton;
    private JButton continueButton;
    private ActionListener listener;

    public GameSelectionPanel(ActionListener actionListener){
        this.listener = actionListener;
        setVisible(true);
        setLayout(null);
        initComp();
        addComp();
    }
    private void initComp(){
        label = new JLabel("Pantalla de Registro de jugador");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(260, 100, 180, 20);
        nameLabel = new JLabel("Nombre del jugador");
        nameLabel.setBounds(200, 240, 140, 20);
        nameTextField = new JTextField(50);
        addListenerText();
        nameTextField.setBounds(400, 240, 140, 20);
        shapeLabel = new JLabel("Elija la figura a usar");
        shapeLabel.setBounds(200, 300, 140, 20);
        box = new JComboBox<String>(shapes);
        box.setBounds(400, 300, 70, 20);
        backButton = new JButton("VOLVER");
        backButton.setBounds(200, 360, 140, 20);
        continueButton = new JButton("CONTINUAR");
        continueButton.setBounds(400, 360, 140, 20);
        continueButtonEvent();
    }
    private void continueButtonEvent() {
        continueButton.addActionListener(listener);
        continueButton.setActionCommand("continue");
    }
    private void addComp(){
        add(label);
        add(nameLabel);
        add(nameTextField);
        add(shapeLabel);
        add(box);
        add(backButton);
        add(continueButton);
    }
    public JButton getBackButton(){
        return backButton;
    }
    private void addListenerText(){
        nameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (nameTextField.getText().length() > 10) {
                    nameTextField.setText(nameTextField.getText().substring(0, 10));
                }
            }
        });
    }
    public String[] getShapes() {
        return shapes;
    }
    public void setShapes(String[] shapes) {
        this.shapes = shapes;
    }
    public JLabel getLabel() {
        return label;
    }
    public void setLabel(JLabel label) {
        this.label = label;
    }
    public JLabel getNameLabel() {
        return nameLabel;
    }
    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }
    public JTextField getNameTextField() {
        return nameTextField;
    }
    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }
    public JLabel getShapeLabel() {
        return shapeLabel;
    }
    public void setShapeLabel(JLabel shapeLabel) {
        this.shapeLabel = shapeLabel;
    }
    public JComboBox<String> getBox() {
        return box;
    }
    public void setBox(JComboBox<String> box) {
        this.box = box;
    }
    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
    public JButton getContinueButton() {
        return continueButton;
    }
    public void setContinueButton(JButton continueButton) {
        this.continueButton = continueButton;
    }
    
}
