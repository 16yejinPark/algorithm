package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//설탕배달
//dp의 기본
public class b2839 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] d = new int[N+1];
		for(int i=0;i<=N;i++) {
			d[i]=-1;
		}
		if(N>=3)
			d[3] = 1;
		if(N>=5)
			d[5] = 1;
		for(int i=6;i<=N;i++) {
			if(d[i-3]==-1&&d[i-5]==-1)
				continue;
			else if(d[i-3]==-1)
				d[i]=d[i-5]+1;
			else if(d[i-5]==-1)
				d[i]=d[i-3]+1;
			else
				d[i]=Math.min(d[i-3]+1, d[i-5]+1);
		}
		System.out.println(d[N]);
	}

}
