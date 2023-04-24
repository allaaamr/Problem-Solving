package Stacks;
import java.util.*;

public class Calculator_H {

    public static int evaluate (String op, int a, int b){
        if (op.equals("-")) {
            return a - b;
        }
        else {
            return a + b;
        }
    }
    public static boolean isNum(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static int calculate(String s) {
        Stack<String> stack = new Stack<String>();
        for(int i=0; i<=s.length()-1; i++){
            if(stack.isEmpty() || s.charAt(i) == '(')
                stack.push(s.charAt(i)+"");
            else if(isNum(stack.peek()) && isNum(s.charAt(i)+"")){
                stack.push(stack.pop()+s.charAt(i));
            }
            else if (stack.peek().equals("-")){
                int operand = Integer.parseInt(s.charAt(i)+"");
                if(stack.size()>1) { // negative might be a unary operator
                    String operation = stack.pop();
                    int op1 = Integer.parseInt(stack.pop());
                    int eval = evaluate(operation, op1, operand);
                    stack.push(eval+"");
                }
                else {
                    stack.push((-1*operand)+"");
                }
            }

            else if (stack.peek().equals("+")){
                String operation = stack.pop();
                int op1 = Integer.parseInt(stack.pop());
                int eval = evaluate(operation, op1, Integer.parseInt(s.charAt(i)+""));
                stack.push(eval+"");
            }
            else if(s.charAt(i)==')') {
                int num = Integer.parseInt(stack.pop());
                stack.pop(); //remove "("
                if(stack.isEmpty())
                    stack.push(num+"");
                else{
                    String operation = stack.pop();
                    int op1 = Integer.parseInt(stack.pop());
                    int eval = evaluate(operation, op1, num);
                    stack.push(eval+"");
                }
            }
            else{
                stack.push(s.charAt(i)+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String [] args){
        int result = calculate("(-(3+5))");
        System.out.println(result);
    }
}
