package test;
import java.util.*;

// 1. 중위표기식을 후위표기식으로 반환한다.
	//1-1. 연산자간의 우선순위
	//1-2. 소괄호처리
	//1-3. 음수처리
// 2. 후위표기식을 받아 결과값을 반환한다.
	//2-1. 연산자, 숫자 구분

public class postfix{
	
	// 2. 후위표기식을 받아 결과값을 반환한다.
	public static double evaluate(String exp) {
		Stack<Double> stack = new Stack<>();
		double result;
		
		for(int i =0;i < exp.length();i++) {
			char c = exp.charAt(i);
			
			if(isNum(c)) {
				String n = String.valueOf(c);
				while(isNum(exp.charAt(i+1))) {	//연속된 숫자처리
					n = n + exp.charAt(++i);
				}
				System.out.println(n);
				stack.push(Double.valueOf(n));
			}
			else if(isOp(c)) {
				if(!stack.isEmpty()) {
					result = op( c , stack);
					stack.push(result);
				}
			}
			
		}
		return stack.pop();
	}
	public static double op(char c, Stack<Double> num) {
		double num1 = num.pop();
		double num2 = num.pop();
		switch(c) {
		case '-':
			return num2 - num1;
		case '+':
			return num2 + num1;
		case '*':
			return num2 * num1;
		case '/':
			return num2 / num1;
		case '@':	//음수처리
			return num2 - num1;
		default:
			return 0;
		}
	}
	// 여기까지가 후위표기식을 받아 결과값을 반환한다.
	
	//2-1. 연산자, 숫자 구분
	public static boolean isNum(char c) {
		if(isOp(c)) {
			return false;
		}else if (c == ' '){
			return false;
		}else {
			return true;
		}
		
	}
	
	public static boolean isOp(char c) {
		switch(c) {
			case '-':
			case '+':
			case '*':
			case '/':
			case '@':
				return true;
			default:
				return false;
		}
	}
	
	//여기까지가 2-1. 연산자, 숫자 구분
	
	// 1. 중위표기식을 후위표기식으로 반환한다.
	public static String toPostfix(String exp) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        char c;
        for (int i = 0; i < exp.length(); i++) {
        	
        	c = exp.charAt(i);
            if (Character.isDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {	//1-2. 소괄호처리
                stack.push(c);
            } else if (c == ')') {
            	
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // '(' 제거
            } else { // 연산자일 경우
            	if((i > 0 && c == '-' && isOp(exp.charAt(i-1)))) {
        			c = '@';
        		}
            	postfix.append(" ");
            	while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop()).append(" ");
            	}

            	stack.push(c);

                if( i == 0 && c=='-' ){ // 음수처리
                	postfix.append(0).append(" ");
        		} else if(i > 0 && c == '-' && exp.charAt(i-1) == '('){	//1-2. 소괄호다음 '-'처리
        			postfix.append(0).append(" ");
        		} else if((i > 0 && c == '@' && isOp(exp.charAt(i-1)))) {
        			System.out.println(true);
        			postfix.append(0).append(" ");
        		}
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }
        System.out.println(postfix.toString().trim());
        return postfix.toString().trim();
    }
	
	
	//1-1. 연산자간의 우선순위
    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '@':
                return 2;
            default:
                return -1; // 괄호 등 다른 연산자가 아닌 경우
        }
    }

	//1-3. 음수처리
	
}