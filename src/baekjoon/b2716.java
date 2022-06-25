package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//원숭이 매달기
public class b2716 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++) {
			char tree[] = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<>();
			int d=0;
			for(int i=0;i<tree.length;i++) {
				if(tree[i]==']') {
					stack.pop();
				}else {
					stack.push(tree[i]);
				}
				d = Math.max(d, stack.size());
			}
			System.out.println((int)Math.pow(2, d));
		}
	}
}
