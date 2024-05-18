package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyCalc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private String exp="";
	private JButton btnNewButton_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyCalc frame = new MyCalc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyCalc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Status");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(5, 4, 10, 5));
		
		JButton btnNewButton_16 = new JButton("(");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "(";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton(")");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + ")";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_17);
		
		JButton btnNewButton_10 = new JButton("c");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = " ";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_10);
		
		JButton btnNewButton_3 = new JButton("5");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "5";
				textField.setText(exp);
			}
		});
		
		JButton btnNewButton_8 = new JButton("4");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "4";
				textField.setText(exp);
			}
		});
		
		JButton btnNewButton_15 = new JButton("X");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "*";
				textField.setText(exp);
			}
		});
		
		JButton btnNewButton_14 = new JButton("/");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "/";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_14);
		
		btnNewButton_4 = new JButton("7");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "7";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_4);
		
		JButton btnNewButton_2 = new JButton("9");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "9";
				textField.setText(exp);
			}
		});
		
		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				exp = exp + "8";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_1);
		panel_2.add(btnNewButton_2);
		panel_2.add(btnNewButton_15);
		panel_2.add(btnNewButton_8);
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("6");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "6";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_12 = new JButton("-");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "-";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_12);
		
		JButton btnNewButton_5 = new JButton("3");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "3";
				textField.setText(exp);
			}
		});
		
		JButton btnNewButton_9 = new JButton("2");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "2";
				textField.setText(exp);
			}
		});
		
		JButton btnNewButton_6 = new JButton("1");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "1";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_6);
		panel_2.add(btnNewButton_9);
		panel_2.add(btnNewButton_5);
		
		JButton btnNewButton_13 = new JButton("+");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "+";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_13);
		
		JButton btnNewButton_11 = new JButton("=");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String postfix = Infix2Postfix.convert(exp);
				double value = Calc.eval(postfix);
				textField.setText(String.valueOf(value));
			}
		});
		
		JButton btnNewButton_7 = new JButton("0");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + "0";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_7);
		
		JButton btnNewButton_18 = new JButton(".");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp + ".";
				textField.setText(exp);
			}
		});
		panel_2.add(btnNewButton_18);
		panel_2.add(btnNewButton_11);
	}

	public JTextField getTextField() {
		return textField;
	}
	public JButton getBtnNewButton_4() {
		return btnNewButton_4;
	}
}
