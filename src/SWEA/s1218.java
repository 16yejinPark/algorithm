package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//괄호 짝짓기
public class s1218 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<10;i++) {
			int result=1;
			int n = Integer.parseInt(br.readLine());
			char[] lids = br.readLine().toCharArray();
			Stack<Character> s = new Stack<Character>();
			outer:for(int j=0;j<n;j++) {
				if(lids[j]=='['||lids[j]=='{'||lids[j]=='('||lids[j]=='<') {
					s.push((Character)lids[j]);
				}else {	
					if((!s.empty())){
						result=0;
						break outer;
					}
					if((s.peek()=='{'&&lids[j]=='}')||(s.peek()=='<'&&lids[j]=='>')||(s.peek()=='('&&lids[j]==')')||(s.peek()=='['&&lids[j]==']')){
						s.pop();
						continue;
					}else {
						result=0;
						break outer;
					}
				}
			}
			if(!s.empty())result=0;
			System.out.printf("#%d %d\n",i+1,result);
		}
	}
}
