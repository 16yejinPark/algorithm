package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


//펠린드롬 만들기
public class b1254 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		
		int cnt = 0;
		for(int i=0;i<str.length;i++) {
			char[] part = Arrays.copyOfRange(str,i,str.length);
			if(i==str.length-1) {
				break;
			}
			if(isPalindrome(part)) {
				break;
			}
			cnt++;
		}
		System.out.println(str.length + cnt);
	}
	public static boolean isPalindrome(char[] str) {
		for(int i=0;i<str.length/2;i++) {
			if(str[i]!=str[str.length-i-1])
				return false;
		}
		return true;
	}
}
