package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class b14890 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		boolean isRamp[][] = new boolean[N][N];
		//가로 탐색
		for(int i=0;i<N;i++) {
			line : for(int j=1;j<N;j++) {
				//두 칸 이상 차이날 때, break;
				if(Math.abs(map[i][j-1]-map[i][j])>1) {
					break;
				}
				//n+1 -> n
				else if(map[i][j-1]>map[i][j]) {
					for(int k=0;k<L;k++) {
						if(j+k<N&&!isRamp[i][j+k]&&map[i][j]==map[i][j+k]) {
							isRamp[i][j+k]= true;
						}else {
							break line;
						} 
					}
				}
				//n -> n+1
				else if(map[i][j-1]<map[i][j]) {
					for(int k=1;k<=L;k++) {
						if(j-k>=0&&!isRamp[i][j-k]&&map[i][j-1]==map[i][j-k]) {
							isRamp[i][j-k]= true;
						}else {
							break line;
						} 
					}
				}
				if(j==N-1) {
					cnt++;
				}
			}
		}
		
		isRamp = new boolean[N][N];
		//세로 탐색
		for(int j=0;j<N;j++){
			colume : for(int i=1;i<N;i++){
				//두 칸 이상 차이날 때, break;
				if(Math.abs(map[i-1][j]-map[i][j])>1) {
					break;
				}
				//n+1 -> n
				else if(map[i-1][j]>map[i][j]) {
					for(int k=0;k<L;k++) {
						if(i+k<N&&!isRamp[i+k][j]&&map[i][j]==map[i+k][j]) {
							isRamp[i+k][j]= true;
						}else {
							break colume;
						} 
					}
				}
				//n -> n+1
				else if(map[i-1][j]<map[i][j]) {
					for(int k=1;k<=L;k++) {
						if(i-k>=0&&!isRamp[i-k][j]&&map[i-1][j]==map[i-k][j]) {
							isRamp[i-k][j]= true;
						}else {
							break colume;
						} 
					}
				}
				if(i==N-1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
