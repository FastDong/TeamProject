package test;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//UI정리
	// 입출력텍스트필드
	// 1~9 버튼 구역
	// 'c','0','=' 버튼 구역
	// 연산기호 버튼 구역
	// 상태텍스트필드


public class testCalc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private String exp ="";
	private String op = "/*+-()";
	private JTextField txtStatus;
	private List<JButton> buttons = new ArrayList<>();
	
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
		
		
		//계산기 입출력필드
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		calc_head.add(textField);
		textField.setText("");
		textField.setColumns(10);
		
		
		//계산기 상태필드
		JPanel calc_bottom = new JPanel();
		contentPane.add(calc_bottom, BorderLayout.SOUTH);
		calc_bottom.setLayout(new BorderLayout(0, 0));
		
		txtStatus = new JTextField();
		txtStatus.setText("입력대기");
		txtStatus.setEditable(false);
		calc_bottom.add(txtStatus);
		txtStatus.setColumns(10);
		
		
		
		
		//계산기 버튼 필드
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
            buttons.add(button);
        }
		
		
		
		// 0,c,= 버튼 생성(위치 : calc_body)
		JButton btn_clear = new JButton("c");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = "";
				textField.setText(exp);
				txtStatus.setText("입력대기");
				setButtonsEnabled(true); // 모든 버튼을 활성화
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
		buttons.add(btn_0);
		
		JButton btn_return = new JButton("=");
		btn_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = String.valueOf(Postfix_to_result.Evaluate(Postfix_to_result.Infix_to_Postfix(exp)));		//연산수행	
				textField.setText(exp);
				txtStatus.setText("계산완료");
				setButtonsEnabled(false); // C 버튼 외 모든 버튼 비활성화
                btn_clear.setEnabled(true); // C 버튼은 항상 활성화
			}
		});
		calc_body.add(btn_return);
		buttons.add(btn_return);
		
		
		
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
            buttons.add(button);
        }
	}
	
	//버튼 활성화함수
	private void setButtonsEnabled(boolean enabled) {
        for (JButton button : buttons) {
            button.setEnabled(enabled);
        }
    }

}
