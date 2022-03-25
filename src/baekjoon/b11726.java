package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b11726 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		int[] map = new int[n+1];
		if(n==1) {
			answer = 1;
		}else if(n==2) {
			answer = 2;
		}else {
			map[1] = 1;
			map[2] = 2;
			for(int i=3;i<=n;i++) {
				map[i] = (map[i-1]+map[i-2])%10007;
			}
			answer = map[n];
		}
		System.out.println(answer);
	}

}
