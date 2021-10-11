import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI{
    private JFrame frame;
    private JPanel panel;
    private JLabel desireCgpaLabel;
    private JLabel currCgpaLabel;
    private JLabel completeCredLabel;
    private JLabel remainCredLabel;
    private JTextField desireCgpaTField;
    private JTextField currCgpaTField;
    private JTextField completeCredTField;
    private JTextField remainCredTField;
    private JButton calculateButton;
    private Validation validation;


    public GUI(){
        validation = new Validation();
        panel = new JPanel();
        frame = new JFrame();

        frame.setSize(420, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this method should call after using setSize() method!!
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        panel.setLayout(null);

        //desire Cgpa
        desireCgpaLabel = new JLabel("Your Desire cgpa");
        desireCgpaLabel.setBounds(10, 15, 130, 25);
        panel.add(desireCgpaLabel);

        desireCgpaTField = new JTextField("pls enter in range 0-4.3");
        desireCgpaTField.setBounds(150, 15, 200, 25);
        panel.add(desireCgpaTField);


        //current Cgpa
        currCgpaLabel = new JLabel("Your current cgpa");
        currCgpaLabel.setBounds(10, 50, 130, 25);
        panel.add(currCgpaLabel);

        currCgpaTField = new JTextField("pls enter in range 0-4.3");
        currCgpaTField.setBounds(150, 50, 200, 25);
        panel.add(currCgpaTField);

        //completed Credit
        completeCredLabel = new JLabel("Your completed credit");
        completeCredLabel.setBounds(10, 85, 130, 25);
        panel.add(completeCredLabel);

        completeCredTField = new JTextField("please input a suitable credit range");
        completeCredTField.setBounds(150, 85, 200, 25);
        panel.add(completeCredTField);

        //remaining Credit
        remainCredLabel = new JLabel("Your remaining credit");
        remainCredLabel.setBounds(10, 120, 130, 25);
        panel.add(remainCredLabel);

        remainCredTField = new JTextField("please input a suitable credit range");
        remainCredTField.setBounds(150, 120, 200, 25);
        panel.add(remainCredTField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 155, 100, 25);
        panel.add(calculateButton);


        //register event handlers
        Handler handler = new Handler();
        desireCgpaTField.addActionListener(handler);
        currCgpaTField.addActionListener(handler);
        completeCredTField.addActionListener(handler);
        remainCredTField.addActionListener(handler);
        calculateButton.addActionListener(handler);

        //register event handlers
        desireCgpaTField.addFocusListener(new defaultText("pls enter in range 0-4.3"));
        currCgpaTField.addFocusListener(new defaultText("pls enter in range 0-4.3"));
        completeCredTField.addFocusListener(new defaultText("please input a suitable credit range"));
        remainCredTField.addFocusListener(new defaultText("please input a suitable credit range"));

        frame.setVisible(true);
    }

    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(validation.checkCgpa(desireCgpaTField.getText(), 1) && validation.checkCgpa(currCgpaTField.getText(), 2) && validation.checkCred(completeCredTField.getText(), 1) && validation.checkCred(remainCredTField.getText(), 2)){
                Calculate calculate = new Calculate(validation);
                JOptionPane.showConfirmDialog(null, calculate.tar(), "Calculate Result", 2);
            }
            else{
                JOptionPane.showConfirmDialog(null, "invalid input\npls re-enter message!", "Invalid message", 2);
            }
        }
    }

    private class defaultText implements FocusListener{
        private String hint;
        private boolean isModify;

        public defaultText(String hint){
            this.hint = hint;
            isModify = false;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(e.getSource() == currCgpaTField && !isModify) {
                currCgpaTField.setText("");
            }
            else if(e.getSource() == desireCgpaTField && !isModify)
                desireCgpaTField.setText("");
            else if(e.getSource() == completeCredTField && !isModify)
                completeCredTField.setText("");
            else if(e.getSource() == remainCredTField && !isModify)
                remainCredTField.setText("");
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == currCgpaTField) {
                if(currCgpaTField.getText().isEmpty())
                    currCgpaTField.setText(hint);
                else
                    isModify = true;
            }
            else if(e.getSource() == desireCgpaTField) {
                if(desireCgpaTField.getText().isEmpty())
                    desireCgpaTField.setText(hint);
                else
                    isModify = true;
            }
            else if(e.getSource() == completeCredTField) {
                if (completeCredTField.getText().isEmpty())
                    completeCredTField.setText(hint);
                else
                    isModify = true;
            }
            else if(e.getSource() == remainCredTField) {
                if (remainCredTField.getText().isEmpty())
                    remainCredTField.setText(hint);
                else
                    isModify = true;
            }
        }


    }


}
