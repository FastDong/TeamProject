package test;
import java.util.*;

public class Infix2Postfix {
	public static String convert(String exp) {
		if(exp == null || exp.length() == 0) return null;
		StringTokenizer st = new StringTokenizer(exp, "+-*/", true);
		Stack<String> stack = new Stack<String>();
		StringBuffer buf = new StringBuffer();
		while(st.hasMoreTokens()) {
			String tok = st.nextToken();
			if(opType(tok) > 0) {
				while(!stack.empty()) {
					String op2 = stack.peek();
					int p1 = getPriority(tok.charAt(0));
					int p2 = getPriority(tok.charAt(0));
					if(p1 < p2) {
						buf.append(op2);
						buf.append(" ");
					}else break;
				}
				stack.push(tok.trim());
			} else {
				buf.append(tok.trim());
				buf.append(" ");
			}
		}
		while(!stack.empty()) {
			buf.append(stack.pop());
			buf.append(" ");
		}
		return buf.toString();
	}

	public static int opType(String op) {
		op = op.trim();
		if(op.length() > 1 || op.length()== 0) {
			return -1;
		}
		char c =op.charAt(0);
		switch (c) {
		case '+':
			return 1;
		case '-':
			return 2;
		case '*':
			return 3;
		case '/':
			return 4;
		}
		return -1;
	}
	
	private static int getPriority(char op) {
		switch (op) {
		case'+':
		case'-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			return -1;
		}
	}
	
	public static void main(String[] args) {
		String exp = "12 + 2.5 * 5";
		System.out.printf("%s => %s %n", exp,
				Infix2Postfix.convert(exp));
	}
}