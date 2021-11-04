package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//좋은 수열
public class b2661 {
	static int N;
	static boolean findAnswer = false;
	static String answer = "";
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		permutation(0,"");
		System.out.println(answer);
	}
	
	static void permutation(int cnt,String num) {
		//System.out.printf("%d %s\n",cnt,num);
		if(findAnswer)return;
		if(cnt==N) {
			answer = num;
			findAnswer = true;
			return;
		}else {

			if(isValid(num + "1")) {
				permutation(cnt+1,num + "1");
			}

			if(isValid(num + "2")) {
				permutation(cnt+1,num + "2");
			}

			if(isValid(num + "3")) {
				permutation(cnt+1,num + "3");
			}
		}
	}
	static boolean isValid(String str) {
		int idx = str.length()-1;
		for(int i=0;i<str.length()/2;i++) {
			if(str.substring(idx-i, idx+1).equals(str.substring(idx-i-i-1, idx-i))) {
				return false;
			}
		}
		return true;
	}
}
