package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1947 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] M = new int[1000001];
		int answer = 0;
		if(N==1) {
			answer=0;
		}else if(N==2) {
			answer=1;
		}else {
			M[1] = 0;
			M[2] = 1;
			for(int i =3;i<=N;i++) {
				long temp = ((long)(i-1) * ((M[i-1] + M[i-2])))%1000000000;
				M[i] = (int)temp;
			}
			answer = M[N];
		}
		
		
		System.out.println(answer);
	}
}
