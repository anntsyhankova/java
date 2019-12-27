package com.company.bsu.rfe.java.group7.lab3.Tsyhankova.varB7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static java.lang.Math.*;

public class Main extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private JTextField textFieldResult;
    private JTextField memoryTextField;

    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioMemoryButtons = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemoryType = Box.createHorizontalBox();

    private int formulaID = 1;
    private int variableID = 1;

    private Double memory_1 = new Double(0);
    private Double memory_2 = new Double(0);
    private Double memory_3 = new Double(0);

    Double calculate1(Double x, Double y, Double z){
        return pow(cos(exp(y)) + exp(y*y) + sqrt(1 / x), 0.25) / pow(cos(PI * z*z*z) + log(pow(1 + z, 2)), sin(y));
    }

    Double calculate2(Double x, Double y, Double z){
        return pow(1 + x*x, 1 / y) / exp(sin(z)+x);
    }

    private void addRadioButtonF(String buttonName, final int formulaID){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                Main.this.formulaID = formulaID;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private void addMemoryRadioButton (String buttonName, final int variableID){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)	{
                Main.this.variableID = variableID;
                if (variableID == 1) memoryTextField.setText(memory_1.toString());
                if (variableID == 2) memoryTextField.setText(memory_2.toString());
                if (variableID == 3) memoryTextField.setText(memory_3.toString());
            }
        });
        radioMemoryButtons.add(button);
        hboxMemoryType.add(button);
    }

    public Main(){
        super("Вычисление формулы");

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButtonF("Formula 1", 1);
        addRadioButtonF("Formula 2", 2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X: ");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y: ");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z: ");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVaribles = Box.createHorizontalBox();
        hboxVaribles.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVaribles.add(Box.createHorizontalGlue());

        hboxVaribles.add(labelForX);
        hboxVaribles.add(Box.createHorizontalStrut(5));
        hboxVaribles.add(textFieldX);
        hboxVaribles.add(Box.createVerticalStrut(50));
        hboxVaribles.add(Box.createHorizontalStrut(5));

        hboxVaribles.add(labelForY);
        hboxVaribles.add(Box.createHorizontalStrut(5));
        hboxVaribles.add(textFieldY);
        hboxVaribles.add(Box.createHorizontalGlue());
        hboxVaribles.add(Box.createHorizontalStrut(5));

        hboxVaribles.add(labelForZ);
        hboxVaribles.add(Box.createHorizontalStrut(5));
        hboxVaribles.add(textFieldZ);

        hboxVaribles.add(Box.createHorizontalGlue());
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);

        JLabel labelForResult = new JLabel("Result: ");
        textFieldResult = new JTextField("0", 20);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonCalc = new JButton("Calculate");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaID == 1){
                        result = calculate1(x, y, z);
                        textFieldResult.setText(result.toString());
                    }
                    else {
                        result = calculate2(x, y, z);
                        textFieldResult.setText(result.toString());
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(Main.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);}
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        hboxMemoryType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("X's memory",1);
        addMemoryRadioButton("Y's memory",2);
        addMemoryRadioButton("Z's memory",3);
        hboxMemoryType.add(Box.createVerticalGlue());
        JButton MC = new JButton("MC");

        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (variableID == 1) textFieldX.setText("0");
                if (variableID == 2) textFieldY.setText("0");
                if (variableID == 3) textFieldZ.setText("0");
            }
        });
        hboxMemoryType.add(MC);

        JButton Mplus = new JButton("M+");

        Mplus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (variableID == 1){
                    memory_1 = new Double(Double.valueOf(textFieldResult.getText()) + Double.valueOf(textFieldX.getText()));
                    textFieldResult.setText(memory_1.toString());
                }
                if (variableID == 2) {
                    memory_2 = new Double(Double.valueOf(textFieldResult.getText()) + Double.valueOf(textFieldY.getText()));
                    textFieldResult.setText(memory_2.toString());
                }
                if (variableID == 3) {
                    memory_3 = new Double(Double.valueOf(textFieldResult.getText()) + Double.valueOf(textFieldZ.getText()));
                    textFieldResult.setText(memory_3.toString());
                }

            }
        });
        hboxMemoryType.add(Mplus);

        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        Box memory_result = Box.createHorizontalBox();
        memory_result.add(Box.createHorizontalGlue());

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVaribles);
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

