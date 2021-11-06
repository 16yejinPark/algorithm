package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

//후위표기식
public class b1918 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sik = br.readLine();
		Stack<Character> s = new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		
		HashMap<Character,Integer> weight = new HashMap<Character,Integer>();
		weight.put('(', 0);
		weight.put(')', 0);
		weight.put('+', 1);
		weight.put('-', 1);
		weight.put('*', 2);
		weight.put('/', 2);
		
		for(int i=0;i<sik.length();i++) {
			char c = sik.charAt(i);

			if('A'<=c&&'Z'>=c) {
				sb.append(c);
				continue;
			}
			
			if(s.isEmpty()) {
				s.push(c);
				continue;
			}
			
			if(c=='(') {
				s.push(c);
				continue;
			}
			
			if(c==')') {
				while(s.peek()!='(') {
					sb.append(s.pop());
				}
				s.pop();
				continue;
			}
			
			while(!s.empty()&&weight.get(s.peek())>=weight.get(c)) {
				sb.append(s.pop());
			}
			s.push(c);
		}
		while(!s.isEmpty()) {
			sb.append(s.pop());
		}
			
		System.out.println(sb.toString());
	}
}
