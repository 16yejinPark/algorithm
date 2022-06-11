package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b21610_2try {
	static int dx[] = {0,0,-1,-1,-1,0,1,1,1};
	static int dy[] = {0,-1,-1,0,1,1,1,0,-1};
	static int ddx[] = {-1,-1,1,1};
	static int ddy[] = {1,-1,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int map[][] = new int[N+1][N+1];
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean cloud[][] = new boolean[N+1][N+1];
		cloud[N][1]=true;
		cloud[N][2]=true;
		cloud[N-1][1]=true;
		cloud[N-1][2]=true;
		
		for(int m=0;m<M;m++){
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			S = S%N;
			boolean temp[][] = new boolean[N+1][N+1];
			//1. 구름이 이동한다.
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(cloud[i][j]){
						int ni = i+(dx[D]*S);
						if(ni<=0){
							ni += N;
						}else if(ni>N){
							ni -= N;
						}
						
						int nj = j+(dy[D]*S);
						if(nj<=0){
							nj += N;
						}else if(nj>N){
							nj -= N;
						}
						
						
						temp[ni][nj] = true;
					}
				}
			}
			//clone
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					cloud[i][j] = temp[i][j];
				}
			}
			
			//2.각 구름에서 비가 내려 물의양이 1씩 증가
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(cloud[i][j]){
						map[i][j]++;
					}
				}
			}
			
			//3.구름이 모두 사라진다.
			//4.물복사버그 마법 시전
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(cloud[i][j]){
						int cnt = 0;
						for(int d=0;d<4;d++){
							int ni = i + ddx[d];
							int nj = j + ddy[d];
							if(ni>0&&ni<=N&&nj>0&&nj<=N&&map[ni][nj]>0){
								cnt++;
							}
						}
						map[i][j] += cnt;
					}
				}
			}
			
			//5.구름 생성 
			temp = new boolean[N+1][N+1];
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(!cloud[i][j]&&map[i][j]>=2){
						temp[i][j] = true;
						map[i][j]-=2;
					}
				}
			}
			
			//clone
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					cloud[i][j] = temp[i][j];
				}
			}
		}
		int total = 0;
		for(int i=1;i<=N;i++){
			for(int j=1;j<=N;j++){
				total += map[i][j];
			}
		}
		System.out.println(total);
	}
}
