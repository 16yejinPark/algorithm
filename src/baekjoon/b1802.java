package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//종이 접기
public class b1802 {
	static int[] arr;
	static boolean answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++) {
			String[] temp = br.readLine().split("");
			arr = new int[temp.length];
			for(int i=0;i<arr.length;i++) {
				arr[i] = Integer.parseInt(temp[i]);
			}
			answer = true;
			check(0,arr.length);
			System.out.println(answer?"YES":"NO");
		}
	}
	
	public static void check(int i,int n) {
		
		if(!answer)return;

		for(int x=0;x<n/2;x++) {
			if(arr[i+x]+arr[i+n-x-1]!=1) {
				answer = false;
				return;
			}
		}
		
		if(n<=1) {
			return;
		}
		
		check(i,n/2);
		check(i+n/2,n/2);
	}
}
