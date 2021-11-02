package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

//문자열 폭발
public class b9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		
		ArrayList<Character> list = new ArrayList<Character>();

		int idx = 0;
		while(true) {
			list.add(str1[idx]);
			boolean explosion = true;
			for(int i=str2.length-1;i>=0;i--) {
				if(list.size()-1-(str2.length-1-i)<0||str2[i]!=list.get(list.size()-1-(str2.length-1-i))) {
					//System.out.printf("%c %c\n",str2[i],list.get(list.size()-1-(str2.length-1-i)));
					explosion = false;
					break;
				}
			}
			
			if(explosion) {
				for(int i=str2.length-1;i>=0;i--) {
					list.remove(list.size()-1);
				}
			}
			
			if(idx==str1.length-1) {
				break;
			}
			idx++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<=list.size()-1;i++) {
			sb.append(list.get(i));
		}
		System.out.println(sb.toString().length()==0?"FRULA":sb.toString());
		
	}
}
