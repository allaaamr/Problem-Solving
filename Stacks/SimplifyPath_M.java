package Stacks;
import java.util.*;
public class SimplifyPath_M {


        public static String simplifyPath(String path) {

            Stack stack = new Stack();
            String[] parts = path.split("/");

            for(int i=0; i<=parts.length-1; i++){
                if(parts[i].equals(".") || parts[i].equals(""))
                    continue;
                else if(parts[i].equals("..")) {
                    if (!stack.isEmpty())
                        stack.pop();
                }
                else
                    stack.push(parts[i]);
            }
            String res = "";
            while(!stack.isEmpty()){
                res = "/" + stack.pop() + res;
            }
            if(res != "")
                return res;
            return "/";
        }
        public static void main(String[] args){
            String test = "/a/..";
            System.out.println(simplifyPath(test));
        }

}
