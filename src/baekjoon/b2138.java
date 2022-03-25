package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//전구와 스위치
public class b2138 {
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] present = br.readLine().toCharArray();
		char[] target = br.readLine().toCharArray();
		char[] temp = present.clone();
		
		goSwitch(temp, target, 0);
		temp = present.clone();
		temp[0]=(temp[0]=='0'?'1':'0');
		temp[1]=(temp[1]=='0'?'1':'0');
		goSwitch(temp, target, 1);
		if(min==Integer.MAX_VALUE) {
			min=-1;
		}
		System.out.println(min);
	}
	static void goSwitch(char[] temp, char[] target, int cnt) {
		for(int i=1;i<target.length;i++) {	
			if(temp[i-1]!=target[i-1]) {
				temp[i-1]=(temp[i-1]=='0'?'1':'0');
				temp[i]=(temp[i]=='0'?'1':'0');
				if(i+1<target.length)
					temp[i+1]=(temp[i+1]=='0'?'1':'0');
				cnt++;
			}
		}
		if(success(temp,target))
			min = Math.min(min, cnt);
	}
	
	
	static boolean success(char[] present, char[] target) {
		for(int i=0;i<present.length;i++) {
			if(present[i]!=target[i])
				return false;
		}
		return true;
	}
}
