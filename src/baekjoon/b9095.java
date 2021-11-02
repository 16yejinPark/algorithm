package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//dp의 시작
//1,2,3더하기
public class b9095 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] d = new int[11];
		for(int i=0;i<T;i++) {
			int n = Integer.parseInt(br.readLine());
			
			//d[n] = d[n-2]+d[n-1]+d[n]
			d[1]=1;
			d[2]=2;
			d[3]=4;
			for(int j=4;j<=n;j++) {
				d[j] = d[j-1]+d[j-2]+d[j-3];
			}
			System.out.println(d[n]);
		}
	}
}
