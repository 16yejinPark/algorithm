package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//배열 돌리기 3
public class b16935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][M+1];
		for(int n=1;n<=N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=1;m<=M;m++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int[][] temp_arr;
		int temp;
		for(int t=1;t<=T;t++) {
			int cs = Integer.parseInt(st.nextToken());
			switch(cs) {
			//상하반전
			case 1:	
				for(int n=1;n<=N/2;n++) {
					for(int m=1;m<=M;m++) {
						temp = arr[n][m];
						arr[n][m] = arr[N-n+1][m];
						arr[N-n+1][m] = temp;
					}
				}break;
			//좌우반전	
			case 2:
				for(int m=1;m<=M/2;m++) {
					for(int n=1;n<=N;n++) {
						temp = arr[n][m];
						arr[n][m] = arr[n][M-m+1];
						arr[n][M-m+1] = temp;
					}
				}
				break;
			//오른쪽으로 90도
			case 3:
				temp_arr = new int[M+1][N+1];
				for(int n=1;n<=N;n++) {
					for(int m=1;m<=M;m++) {
						temp_arr[m][N-n+1] = arr[n][m];
					}
				}
				temp = N;
				N = M;
				M = temp;
				arr = new int[N+1][M+1];
				for(int n=1;n<=N;n++) {
					for(int m=1;m<=M;m++) {
						arr[n][m]=temp_arr[n][m];
					}
				}
				break;
			//왼쪽으로 90도
			case 4:
				temp_arr = new int[M+1][N+1];
				for(int n=1;n<=N;n++) {
					for(int m=1;m<=M;m++) {
						temp_arr[M-m+1][n] = arr[n][m];
					}
				}
				temp = N;
				N = M;
				M = temp;
				arr = new int[N+1][M+1];
				for(int n=1;n<=N;n++) {
					for(int m=1;m<=M;m++) {
						arr[n][m]=temp_arr[n][m];
					}
				}
				break;
			//시계방향
			case 5:
				temp_arr = new int[N+1][M+1];
				for(int n=1;n<=N;n++) {
					for(int m=1;m<=M;m++) {
						if(n<=N/2&&m<=M/2) {
							temp_arr[n][m+(M/2)] = arr[n][m];
						}else if(n<=N/2&&m>M/2) {
							temp_arr[n+(N/2)][m] = arr[n][m];
						}else if(n>N/2&&m>M/2) {
							temp_arr[n][m-(M/2)] = arr[n][m];
						}else if(n>N/2&&m<=M/2) {
							temp_arr[n-(N/2)][m] = arr[n][m];
						}
					}
				}
				for(int n=1;n<=N;n++) {
					for(int m=1;m<=M;m++) {
						arr[n][m]=temp_arr[n][m];
					}
				}
				break;
			//반시계방향
			case 6:
				temp_arr = new int[N+1][M+1];
				for(int n=1;n<=N;n++) {
					for(int m=1;m<=M;m++) {
						if(n<=N/2&&m<=M/2) {
							temp_arr[n+(N/2)][m] = arr[n][m];
						}else if(n<=N/2&&m>M/2) {
							temp_arr[n][m-(M/2)] = arr[n][m];
						}else if(n>N/2&&m>M/2) {
							temp_arr[n-(N/2)][m] = arr[n][m];
						}else if(n>N/2&&m<=M/2) {
							temp_arr[n][m+(M/2)] = arr[n][m];
						}
					}
				}
				for(int n=1;n<=N;n++) {
					for(int m=1;m<=M;m++) {
						arr[n][m]=temp_arr[n][m];
					}
				}
				break;
			}
		}
		
		for(int n=1;n<=N;n++) {
			for(int m=1;m<=M;m++) {
				System.out.print(arr[n][m]+" ");
			}
			System.out.println();
		}
	}

}
