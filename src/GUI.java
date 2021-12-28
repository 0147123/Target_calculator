import javax.swing.*;

import java.awt.event.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

public class GUI{
    private JFrame frame;
    private DataStore validation;

    
	private JPanel contentPane;
	private JTextField inputTextField [];
    private JLabel displayLabel [];
    private Point initialClick;


    public GUI(){
        validation = new DataStore();
        frame = new JFrame();

        frame.setUndecorated(true);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
             public void componentResized(ComponentEvent e) {
                frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 15, 15));
             }
         });
        frame.setSize(420, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this method should call after using setSize() method!!
        frame.setLocationRelativeTo(null);


		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setPreferredSize(new Dimension(440, 340));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setOpaque(false);
		frame.setContentPane(contentPane);
		
		JPanel exitPanel = new JPanel();
		exitPanel.setBackground(Color.decode("#233E6F"));
		exitPanel.setPreferredSize(new Dimension(440, 35));
		exitPanel.setLayout(null);
		contentPane.add(exitPanel, BorderLayout.NORTH);
		
        ImageIcon closeimage = new ImageIcon("close.png");
		JButton closeButton = new JButton("", closeimage);
		closeButton.setRolloverIcon(closeimage);
		closeButton.setBounds(398, 2, 30, 30);
		closeButton.setBackground(Color.decode("#233E6F"));
        closeButton.setBorderPainted(false);
        closeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		exitPanel.add(closeButton);
		
		JPanel DataPanel = new JPanel();
		DataPanel.setPreferredSize(new Dimension(440, 250));
		DataPanel.setBackground(Color.decode("#5F8ACB"));
		contentPane.add(DataPanel, BorderLayout.CENTER);
		DataPanel.setLayout(null);
		
		displayLabel = new JLabel[4];
		displayLabel[0] = new JLabel("Current CGPA");
		displayLabel[1] = new JLabel("Desire CGPA");
		displayLabel[2] = new JLabel("Credits earned");
		displayLabel[3] = new JLabel("Remaining credit");

		inputTextField = new JTextField[4];



		//setting format
		for (int i = 0; i <=3; i++) {
			//set format for displayLabel
			displayLabel[i].setForeground(Color.WHITE);
			displayLabel[i].setFont(new Font("Arial", Font.PLAIN, 20));
			displayLabel[i].setBounds(40, 26+i*58, 158, 24);
			DataPanel.add(displayLabel[i]);

			//set format for inputTextField
			inputTextField[i] = new RoundTextfield("Suitable Credit Range");
			inputTextField[i].setOpaque(false);
			inputTextField[i].setBounds(199, 24+i*58, 200, 25);
			inputTextField[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
            inputTextField[i].setFont(new Font("Arial", Font.PLAIN, 16));
			inputTextField[i].setBackground(Color.decode("#BECFE8"));
			DataPanel.add(inputTextField[i]);
			inputTextField[i].setColumns(10);
		}


		
		JPanel ComfirmPanel = new JPanel();
		ComfirmPanel.setLayout(null);
		ComfirmPanel.setPreferredSize(new Dimension(440, 55));
		ComfirmPanel.setBackground(Color.decode("#5F8ACB"));
		contentPane.add(ComfirmPanel, BorderLayout.SOUTH);
		
		JButton calButton = new CalButton();
		calButton.setText("Calculate");
		calButton.setOpaque(false);
		calButton.setBackground(new Color(35, 62, 111, 170));
		calButton.setForeground(Color.WHITE);
		calButton.setFont(new Font("Arial", Font.PLAIN, 14));
		calButton.setBounds(312, 2, 100, 30);
		calButton.setBorderPainted(false);
		ComfirmPanel.add(calButton);

        //register event handlers
        Handler handler = new Handler();
      
        calButton.addActionListener(handler);

        for (JTextField i : inputTextField){
            i.addActionListener(handler);
        }

        //register event handlers
        inputTextField[0].addFocusListener(new defaultText("Range: 0-4.3"));
        inputTextField[1].addFocusListener(new defaultText("Range: 0-4.3"));
        inputTextField[2].addFocusListener(new defaultText("Suitable Credit Range"));
        inputTextField[3].addFocusListener(new defaultText("Suitable Credit Range"));

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        exitPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                exitPanel.getComponentAt(initialClick);
            }
        });
    
        exitPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
    
                // get location of Window
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;
    
                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
    
                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
            }
        });

        frame.pack();
        frame.setVisible(true);

    }

    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(validation.checkCgpa(inputTextField[0].getText(), 1) && validation.checkCgpa(inputTextField[1].getText(), 2) 
               && validation.checkCred(inputTextField[2].getText(), 1) && validation.checkCred(inputTextField[3].getText(), 2)){
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
            if (e.getSource() instanceof JTextField) {
                JTextField textField = (JTextField) e.getSource();
                if(!isModify) 
                    textField.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() instanceof JTextField) {
                JTextField textField = (JTextField) e.getSource();
                if(textField.getText().isEmpty())
                    textField.setText(hint);
                else if (textField.getText().equals(hint))
                    isModify = false;
                else
                    isModify = true;
            }

        }

    }

	private class RoundTextfield extends JTextField{
	    private Shape shape;

        public RoundTextfield(String title){
            super(title);
        }
	
	    protected void paintComponent(Graphics g) {
			Graphics2D graphics = (Graphics2D) g;
			//
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

			graphics.setColor(new Color(0, 0, 0, 100));
			graphics.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);// arc Dimension

			graphics.setColor(getBackground());
	        graphics.fillRoundRect(0, 0, getWidth(), getHeight()-3, 5, 5);
	        super.paintComponent(g);
	    }

	}
	
	private class CalButton extends JButton{
		@Override
	    protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
	        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
	        super.paintComponent(g);
	    }

	    protected void paintBorder(Graphics g) {
	        g.setColor(new Color(0, 0, 0, 0));
	        g.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10); 
	    }

	    Shape shape;
	    public boolean contains(int x, int y) {
	        if (shape == null || 
	                !shape.getBounds().equals(getBounds())) {
	            shape = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10);
	        }
	        return shape.contains(x, y);
	    }

	}

}
