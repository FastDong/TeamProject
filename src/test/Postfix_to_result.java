package test;
import java.util.*;

// 코드 정리
// chapter 1. 입력받은 중위표기식을 후위표기식으로 변환
	// step 1. 숫자와 기호, 괄호에 따라 분류한다.
	// step 2. '-'기호가 특정조건에서 음수로써 기능하도록한다.
	// step 3. 우선순위에 따라서 후위표기식에 연산자를 추가한다.
	// step 4. 중위표기식의 판별이 종료된 후 후위표기식을 반환한다.

// chapter 2. 입력받은 후위표기식을 계산한 결과를 출력
	// step 1. 하나씩 String 숫자 , 연산자를 판별한다.
	// step 2. 판별된 타입에 따라서 명령을 처리한다.
	// step 3. 입력받은 후위표기식의 판별이 종료된 후 stack에 남은 결과값을 반환한다.


public class Postfix_to_result{
	
	//chapter 1
	public static String Infix_to_Postfix(String 수식) {
        StringBuilder 후위표기식 = new StringBuilder(); 	//StringTokenizor 대신 문자열을 가변적으로 조작가능한 StringBuilder사용
        Stack<Character> 연산자 = new Stack<>();
        char 문자;
        
        for (int i = 0; i < 수식.length(); i++) {
        	문자 = 수식.charAt(i);
        	
        	//step 1
            if (Character.isDigit(문자)){
            	후위표기식.append(문자);
            }
            else if (문자 == '('){
                연산자.push(문자);
            }
            else if (문자 == ')'){
            	
                while (!연산자.isEmpty() && 연산자.peek() != '(') {		//소괄호에 따른 연산우선순위처리
                	후위표기식.append(연산자.pop()).append(" ");		//StringTokenizor를 쓰지않음으로써 연산자와 숫자를 구분하기위해 공백을 추가
                }
                연산자.pop();
                
            }
            else{	
            	//step 2-1         
            	if((i > 0 && 문자 == '-' && isOp(수식.charAt(i-1)))){		//이전문자가 연산자인 경우 음수처리용 연산자 '@'로 변경
            		문자 = '@';
            	}
            	후위표기식.append(" ");	//StringTokenizor를 쓰지않음으로써 연산자와 숫자를 구분하기위해 공백을 추가
            	
            	//step 3
            	while (!연산자.isEmpty() && precedence(문자) <= precedence(연산자.peek())){
            		후위표기식.append(연산자.pop()).append(" ");
            	}
            
            	연산자.push(문자);

            	//step 2-2
            	if( i == 0 && 문자=='-' ){	//수식의 맨앞 수가 음수인 경우 음수처리
            		후위표기식.append(0).append(" ");		// 음수처리를 위해 "0 - 숫자" 연산을 하도록 후위표기식에 '0'을 추가
            	}
            	//step 2-3
            	else if(i > 0 && 문자 == '-' && 수식.charAt(i-1) == '(') {		//'('다음에 바로 음수가 오는 경우 음수처리
            		후위표기식.append(0).append(" ");
            	}
            	else if((i > 0 && 문자 == '@' && isOp(수식.charAt(i-1)))){		//다른 연산자다음에 음수가 오는 경우 음수처리
            		System.out.println(true);	//해당 수가 음수처리 되었는지 여부출력
            		후위표기식.append(0).append(" ");
            	}
            }
        }

        //step 4
        while (!연산자.isEmpty()) {
        	후위표기식.append(연산자.pop()).append(" ");
        }
        System.out.println(후위표기식.toString().trim());
        
        return 후위표기식.toString().trim();
    }
    
	
	//chapter 2
    public static double Evaluate(String 수식){
		Stack<Double> 숫자 = new Stack<>();
		double result;
		
		for(int i =0;i < 수식.length();i++){
			char 문자 = 수식.charAt(i);
			
			//step 1
			if(isNum(문자)){
				//step 2
				String n = String.valueOf(문자);
				while(isNum(수식.charAt(i+1))){	//여러자리를 가지는 수에 대한 처리
					n = n + 수식.charAt(++i);
				}
				
				System.out.println(n); //여러자릿수로 올바르게 처리되었는지 여부출력
				숫자.push(Double.valueOf(n));
				}
			else if(isOp(문자)){
				//step 2
				if(!숫자.isEmpty()){
					result = op( 문자 , 숫자 );
					숫자.push(result);
				}
			}	
		}
		
		//step 3
		return 숫자.pop();
	}
    
    
    
    //연산을 수행할 함수
    public static double op(char 문자, Stack<Double> 숫자){
    	//stack의 맨위 두 숫자를 가져옴
		double num1 = 숫자.pop();
		double num2 = 숫자.pop();
		
		switch(문자){
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
    
    //숫자를 판별하는 함수
	public static boolean isNum(char 문자) {
		if(isOp(문자)) {
			return false;
		}else if (문자 == ' '){
			return false;
		}else {
			return true;
		}
		
	}
	
	//연산자를 판별하는 함수
	public static boolean isOp(char 문자) {
		switch(문자) {
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
	
	//연산자의 우선순위를 정하는 함수
	public static int precedence(char 연산자) {
        switch (연산자) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '@':
                return 2;
            default:
                return -1;
        }
    }
	
}