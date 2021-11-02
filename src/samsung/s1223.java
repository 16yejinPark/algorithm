package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//계산기
public class s1223 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int T=1;T<=10;T++) {

			char[] infix = br.readLine().toCharArray();
			List<Character> postfix = new ArrayList<>();
			Stack<Character> stack = new Stack<>();
			for(int i=0;i<infix.length;i++) {
				if(infix[i]>='0'&&infix[i]<='9') {
					postfix.add(infix[i]);
				}else if(infix[i]=='+'){
					while(!stack.isEmpty()) {
						postfix.add(stack.pop());
					}
					stack.add(infix[i]);
				}else if(infix[i]=='*'){
					while(!stack.isEmpty()&&stack.peek()=='*') {
						postfix.add(stack.pop());
					}
					stack.add(infix[i]);
				}
			}
			while(!stack.isEmpty()) {
				postfix.add(stack.pop());
			}
			
			for(char c:postfix) {
				System.out.print(c);
			}
			System.out.printf("\n#%d %d\n",T,T);
		}

	}

}
