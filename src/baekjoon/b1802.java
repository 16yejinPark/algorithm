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
			check(arr.length/2,arr.length);
			System.out.println(answer?"YES":"NO");
		}
	}
	
	public static void check(int idx,int n) {
		System.out.println(idx+" "+n);
		if(!answer)return;
		if(idx==0) {
			return;
		}
		for(int i=n-1,j=0;j<idx;i--,j++) {
			if(arr[i]+arr[j]!=1) {
				answer = false;
				return;
			}
		}
		check(idx/2,n/2);
	}
}
