import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener{
	private final String[] str = {
		"1","2","3","/",
		"4","5","6","*",
		"7","8","9","-",
		".","0","=","+"
	};
	JButton[] buttons = new JButton[str.length];
	JButton reset = new JButton("RE");
	JTextField display = new JTextField("0");

	public Calculator(){
		super("Calculator");
		Font font = new Font("Consolas", Font.BOLD, 18);

		//add two panel components
		JPanel pnlHead = new JPanel(new BorderLayout());
		pnlHead.add(display, BorderLayout.CENTER);
		pnlHead.add(reset, BorderLayout.EAST);
		display.setFont(font);
		reset.setFont(font);

		JPanel pnlBody = new JPanel(new GridLayout(4,4));
		for(int i = 0;i < str.length;i++){
			buttons[i] = new JButton(str[i]);
			buttons[i].setFont(font);
			pnlBody.add(buttons[i]);
		}

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnlHead,BorderLayout.NORTH);
		getContentPane().add(pnlBody,BorderLayout.CENTER);

		for(int i = 0;i < str.length;i++){
			buttons[i].addActionListener(this);
		}
		display.addActionListener(this);
		reset.addActionListener(this);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(300,280);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		Object sourse = e.getSource();
		String cmd = e.getActionCommand();
		if(sourse == reset){
			handleReset();
		}
		else if("0123456789.".indexOf(cmd) >= 0){
			handleNumber(cmd);
		}
		else{
			handleOperator(cmd);
		}
	}

	boolean isFirstDigit = true;
	double number = 0.0;
	String operator = "=";

	public void handleNumber(String key){
		if(isFirstDigit)
			display.setText(key);
		else if(!key.equals("."))
			display.setText(display.getText()+key);
		else if(display.getText().indexOf(".") < 0)
			display.setText(display.getText() + ".");
	}


	public void handleReset(){
		isFirstDigit = true;
		operator = "=";
		number = 0.0;
		display.setText("0");
	}

	public void handleOperator(String cmd){
		double dDisplay = Double.valueOf(display.getText());
		switch(operator){
			case "+": number += dDisplay;break;
			case "-": number -= dDisplay;break;
			case "/": number /= dDisplay;break;
			case "*": number *= dDisplay;break;
			case "=": number = dDisplay;break;
		}
		display.setText(String.valueOf(number));
		operator = cmd;
		isFirstDigit = true;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			new Calculator();
		});
	}
}