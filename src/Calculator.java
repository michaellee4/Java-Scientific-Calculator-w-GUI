import sun.security.util.Length;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Calculator extends JFrame {
    private JButton buttonNum0, buttonNum1, buttonNum2, buttonNum3,buttonNum4,buttonNum5,
            buttonNum6,buttonNum7,buttonNum8,buttonNum9, buttonAdd, buttonSub, buttonEq, buttonMult, buttondiv,
            buttonClear, buttonMod, buttonDec;
    private JTextArea textArea;
    private JLabel label;
    private String currentTotal;
    private String currentNum;
    private Stack<String> operators;
    private Stack<Double> operands;


    public Calculator(){
        currentTotal = currentNum = "0";
        operators = new Stack<String>();
        operands = new Stack<Double>();
        makeFrame();
    }


    public static void main(String[] args){
        Calculator c = new Calculator();
    }
    private void makeFrame(){
        setSize(new Dimension(150,275));
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        getContentPane().add(panel);

        label = new JLabel(currentNum);
        label.setMinimumSize(new Dimension(125,15));
        label.setPreferredSize(new Dimension(125,15));
        label.setMaximumSize(new Dimension(125,15));
        panel.add(label);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(false);
        textArea.setSize(new Dimension(125,5));
        panel.add(textArea);

        buttonNum7 = new JButton("7");
        buttonNum7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum7.getText());
            }
        });
        panel.add(buttonNum7);

        buttonNum8 = new JButton("8");
        buttonNum8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum8.getText());
            }
        });
        panel.add(buttonNum8);

        buttonNum9 = new JButton("9");
        buttonNum9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum9.getText());
            }
        });
        panel.add(buttonNum9);

        buttonNum4 = new JButton("4");
        buttonNum4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum4.getText());
            }
        });
        panel.add(buttonNum4);

        buttonNum5 = new JButton("5");
        buttonNum5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum5.getText());
            }
        });
        panel.add(buttonNum5);

        buttonNum6 = new JButton("6");
        buttonNum6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum6.getText());
            }
        });
        panel.add(buttonNum6);

        buttonNum1 = new JButton("1");
        buttonNum1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum1.getText());
            }
        });
        panel.add(buttonNum1);

        buttonNum2 = new JButton("2");
        buttonNum2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum2.getText());
            }
        });
        panel.add(buttonNum2);

        buttonNum3 = new JButton("3");
        buttonNum3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum3.getText());
            }
        });
        panel.add(buttonNum3);

        buttonNum0 = new JButton("0");
        buttonNum0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonNum0.getText());
            }
        });
        panel.add(buttonNum0);

        buttonAdd = new JButton("+");
        buttonAdd.addActionListener(new opActionListener(buttonAdd.getText()));
        panel.add(buttonAdd);

        buttonSub = new JButton("-");
        buttonSub.addActionListener(new opActionListener(buttonSub.getText()));
        panel.add(buttonSub);

        buttonMod = new JButton("%");
        buttonMod.addActionListener(new opActionListener(buttonMod.getText()));
        panel.add(buttonMod);

        buttonMult = new JButton("x");
        buttonMult.addActionListener(new opActionListener(buttonMult.getText()));
        panel.add(buttonMult);

        buttondiv = new JButton("/");
        buttondiv.addActionListener(new opActionListener(buttondiv.getText()));
        panel.add(buttondiv);


        buttonEq = new JButton("=");
        buttonEq.addActionListener(new opActionListener(buttonEq.getText()));
        panel.add(buttonEq);

        buttonDec = new JButton(".");
        buttonDec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateLabel(buttonDec.getText());
            }
        });
        panel.add(buttonDec);

        buttonClear = new JButton("C/CE");
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (currentNum.equals("0")) {
                    currentTotal = "0";
                    operands.removeAllElements();
                    operators.removeAllElements();
                    label.setText(currentNum);
                }
                else {
                    currentNum = "0";
                    label.setText(currentNum);
                }

            }
        });
        panel.add(buttonClear);
        setVisible(true);
    }
    private void updateLabel(String n){
        if(n.equals(".")&&!currentNum.contains(n)){
            currentNum+=n;
            label.setText(currentNum);
        }
        else if (currentNum.equals("0")){
            currentNum=n;
            label.setText(currentNum);
        }
        else if (!n.equals(".")){
            currentNum+=n;
            label.setText(currentNum);
        }
    }
    private void performOp(){
        double temp = operands.pop();
        String op = operators.pop();
        if (op.equals("+")){
            double val = temp+Double.valueOf(currentNum);
            operands.push(val);
        }
        if (op.equals("-")){
            double val = temp-Double.valueOf(currentNum);
            operands.push(val);
        }
        if(op.equals("x")) {
            operands.push(temp * Double.valueOf(currentNum));
        }
        else if(op.equals("%"))
            operands.push(temp%Double.valueOf(currentNum));
        else if(op.equals("/"))
            operands.push(temp/Double.valueOf(currentNum));
        label.setText(""+operands.peek());
    }
    public class opActionListener implements ActionListener{
        String op;
        public opActionListener(String op){
            this.op = op;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            if (op.equals("+")||op.equals("-")){
                if(operands.isEmpty()){
                    operators.push(op);
                    operands.push(Double.valueOf(currentNum));
                }
                else{
                    if(operators.isEmpty()){
                        operators.push(op);
                    }
                    else {
                        performOp();
                        operators.push(op);
                        label.setText("" + operands.peek());
                    }
                }
            }
            if(op.equals("x")||op.equals("/")||op.equals("%")){
                if(operands.isEmpty()){
                    operators.push(op);
                    operands.push(Double.valueOf(currentNum));
                }
                else{
                    if(operators.isEmpty()){
                        operators.push(op);
                    }
                    else {
                        String tempOp = operators.peek();
                        if(!tempOp.equals("+")&&!tempOp.equals("-")){
                            performOp();
                        }
                        else{
                            operands.push(Double.valueOf(currentNum));
                        }
                        operators.push(op);
                        label.setText("" + operands.peek());
                    }
                }
            }
            if (op.equals("=")){

                operands.push(Double.valueOf(currentNum));
                    while(!operators.isEmpty()){
                        double t2 = operands.pop(), t1 = operands.pop();
                        String opt = operators.pop();
                        if(opt.equals("x")){
                            operands.push(t1*t2);
                        }
                        if(opt.equals("+")){
                            operands.push(t1+t2);
                        }
                        if(opt.equals("/"))
                            operands.push(t1/t2);
                        if(opt.equals("-"))
                            operands.push(t1-t2);
                        if (opt.equals("%"))
                            operands.push(t1%t2);
                    }

                    label.setText(""+operands.peek());
            }
            currentNum = "0";

        }

    }

}
