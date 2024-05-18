package test;
import java.util.*;

//exp라는 문자열을 받아서 후위표기로 변환하는 함수 //
public class Infix2Postfix {
	public static String convert(String exp) {
		if(exp == null || exp.length() == 0) return null;	
		//아래의 StringTokenizer 메소드는 +-*/가 문자열 exp를 나누는 구분자로 사용됨. 
		//true는 구분자 역시 하나의 토큰으로 취급하여 문자열과 같이 반환하라는 의미.
		//--> 결과적으로 객체 st에는 분리된 토큰들이 담기게 됨.
		StringTokenizer st = new StringTokenizer(exp, "+-*/()", true);
		//스택 객체 생성 
		Stack<String> stack = new Stack<String>();
		// 버퍼 객체 생성 
		StringBuffer buf = new StringBuffer();
		//has.MoreTokens()는 StringTokenizer의 객체가 더 이상 토큰을 반환할 수 있는 지에 따라 true false를 반환.
		if(st.hasMoreTokens()) {
			String FirstTok = st.nextToken();
			if(FirstTok.equals("-")) {
				buf.append(FirstTok).append(st.nextToken()).append(" ");
			}
			else if (FirstTok.equals("(")) {
				stack.push(FirstTok.trim());
				String nextToken = st.nextToken();
				if(nextToken.equals("-")) {
					buf.append("-");
				}
				else {
					stack.push(nextToken.trim());
				}
			}
			else if (opType(FirstTok) < 0){
				buf.append(FirstTok.trim());
				buf.append(" ");
			}
			else if(FirstTok.equals("+")) {
				buf.append(FirstTok).append(st.nextToken()).append(" ");
			}
		}
		while(st.hasMoreTokens()) {
			String tok = st.nextToken();
			//opType은 아래에서 정의한, 연산자에 대해서 각각 1,2,3,4를 부여하는 함수이다.
			if(opType(tok) >= 0) {
				if (tok.equals("(")) {
					stack.push(tok.trim());
					String nextToken = st.nextToken();
					if(nextToken.equals("-")) {
						buf.append("-");
					}
					else if(opType(tok)<0){
						buf.append(tok);
					}
					else {
						stack.push(nextToken);
					}
				}
				else if(tok.equals(")")){
					while (!stack.empty()){
						if (stack.peek().equals("(")){
							stack.pop();
							break;
						}
						else {
							buf.append(stack.pop());
							buf.append(" ");
						}
					}
				}
				else if (tok.equals("+") || tok.equals("-") || tok.equals("*") || tok.equals("-")) {
					while(!stack.empty()) {
						String op2 = stack.peek();
						//getPriority는 아래에서 정의된 
						//charAt()는 해당 인덱스의 문자를 반환하는 메소드이다.
						int p1 = getPriority(tok.charAt(0));
						int p2 = getPriority(op2.charAt(0));
						if(p1<p2) {
							buf.append(op2);
							buf.append(" ");
						} else {
							break;
						}
					}
					stack.push(tok.trim());
				}	
			} 	
			else if (opType(tok) < 0){
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
	
	// 연산자 분리 
	public static int opType(String op) {
		op = op.trim();
		if(op.length() > 1 || op.length()==0) {
			return -1;
		}
		char c = op.charAt(0);
		// switch는 입력된 변수에 대하여 여러가지 케이스 중에 해당하는 값을 return 해주는 함수이다.
		switch (c) {
			case'(':
			case')':
				return 0;
			case'+':
				return 1;
			case'-':
				return 2;
			case'*':
				return 3;
			case'/':
				return 4;			
		}
		return -1;
	}
	//주어진 연산자에 대한 우선순위를 반환하는 메소드이다.
	private static int getPriority(char op){
		switch (op) {
		case'+':
		case'-':
			return 1;
		case'*':
		case'/':
			return 2;
		default:
			return -1;
		}
	}
}
