package test;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testCalc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private String exp ="";
	private String op = "/*+-()";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testCalc frame = new testCalc();
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
	public testCalc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel calc_head = new JPanel();
		contentPane.add(calc_head, BorderLayout.NORTH);
		calc_head.setLayout(new BorderLayout(0, 0));
		
		
		//계산기 출력부분
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		calc_head.add(textField);
		textField.setText("0");
		textField.setColumns(10);
		
		JPanel calc_bottom = new JPanel();
		contentPane.add(calc_bottom, BorderLayout.SOUTH);
		
		JPanel calc_body = new JPanel();
		contentPane.add(calc_body, BorderLayout.CENTER);
		calc_body.setLayout(new GridLayout(4, 3, 10, 10));
		
		JPanel calc_op = new JPanel();
		contentPane.add(calc_op, BorderLayout.EAST);
		calc_op.setLayout(new GridLayout(6, 0, 10, 10));
		
		
		// 1 ~ 9 까지 버튼 생성(위치 : calc_body)
		for (int i = 1; i <10; i++) {
			final int num = i;
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		char n = (char) ('0'+ num);
    				exp = exp + n;
    				textField.setText(exp);
    			}
    		});
            calc_body.add(button); 
        }
		
		
		// 0,c,= 버튼 생성(위치 : calc_body)
		JButton btn_clear = new JButton("c");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = "";
				textField.setText(exp);
			}
		});
		calc_body.add(btn_clear);
		
		JButton btn_0 = new JButton("0");
		btn_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = exp +'0';
				textField.setText(exp);
			}
		});
		calc_body.add(btn_0);
		
		JButton btn_return = new JButton("=");
		btn_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = String.valueOf(postfix.evaluate(postfix.toPostfix(exp)));
				textField.setText(exp);
			}
		});
		calc_body.add(btn_return);
		
		
		
		//연산자 버튼 생성(위치 : calc_op)
		for (int i = 0; i < op.length(); i++) {
			final char op_char= op.charAt(i);
            JButton button = new JButton(String.valueOf(op.charAt(i)));
            button.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
    				exp = exp + op_char;
    				textField.setText(exp);
    			}
    		});
            calc_op.add(button); 
        }
		
	}

}
