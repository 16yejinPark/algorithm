package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 레벨 햄버거
public class b16974 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N  = Integer.parseInt(st.nextToken());
		long X  = Long.parseLong(st.nextToken());
		
		long[] memo = new long[N+1];
		memo[0] = 1;
		for(int i=1;i<=N;i++) {
			memo[i]=memo[i-1]*2+3;	//전체 요소의 수
		}
		
		long p = 0;
		long pos = 0;
		int n = N;
		while(pos!=X) {
			if(n==0) {
				p++;
				break;
			}
			if(pos + memo[n]/2+1<=X) {
				p+=memo[n-1]/2+2;
				if(pos + memo[n]/2+1==X) {
					break;
				}
				pos += memo[n]/2+1;
			}else if(pos + memo[n]/2+1>X){
				pos ++;
			}
			n--;
		}
		System.out.println(p);
	}

}
