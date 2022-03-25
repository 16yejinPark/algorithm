package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//행렬제곱
public class b10830 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long[][] A = new long[N][N];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int nn=0;nn<N;nn++) {
				A[n][nn]=Long.parseLong(st.nextToken());
			}
		}
		
		long[][] answer = power(A,N,B,1000);
		for(int n=0;n<N;n++) {
			for(int nn=0;nn<N;nn++) {
				System.out.print(answer[n][nn]+" ");
			}
			System.out.println();
		}
	}
	
	static long[][] power(long[][] a,int N, long b, int p) {
		long[][] res = new long[N][N];
	
		//init matrix
		for(int n=0;n<N;n++) {
			for(int nn=0;nn<N;nn++) {
				if(n==nn) {
					res[n][nn]=1L;
				}else {
					res[n][nn]=0L;
				}
			}
		}

		//pow
		while(b>0) {
			if(b%2==1) {
				res = mulMatrix(N,res,a,1000);
			}
			b = b >> 1;
			a = mulMatrix(N ,a,a,1000);
		}
		return res;
	}
	
	static long[][] mulMatrix(int N, long[][] a,long[][] b, int p){
		long[][] res = new long[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//i,j번째 수
				long sum = 0;
				for(int n=0;n<N;n++) {
					sum += (a[i][n]*b[n][j])%p;
				}
				res[i][j] = sum%p;
			}
		}
		return res;
	}
	
}
