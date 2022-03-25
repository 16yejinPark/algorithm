package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//사다리 조작
public class b15684 {
	static int ladder[][];
	static int N;
	static int M;
	static int H;
	static boolean findAnswer = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//세로선 개수
		M = Integer.parseInt(st.nextToken());	//가로선 개수
		H = Integer.parseInt(st.nextToken());	//세로선마다 가로선을 놓을 수 있는 위치
		
		ladder = new int[H+1][N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = 1;
			ladder[a][b+1] = -1;
		}
		
		//draw();
		
		if(play()) {
			System.out.println(0);
		}else {
			dfs(0,1);
			if(findAnswer) {
				System.out.println(1);
			}else {
				dfs(0,2);
				if(findAnswer) {
					System.out.println(2);
				}else {
					dfs(0,3);
					if(findAnswer) {
						System.out.println(3);
					}else {
						System.out.println(-1);
					}
				}
			}
		}
	}
	
	static void draw() {
		for(int i=1;i<=H;i++) {
			for(int j=1;j<=N;j++) {
				System.out.printf("%2d",ladder[i][j]);
			}System.out.println();
		}System.out.println();
	}
	
	static boolean play() {
		for(int j=1;j<=N;j++) {
			int line = j;
			for(int i=1;i<=H;i++) {
				if(ladder[i][line]==0) {
					continue;
				}else if(ladder[i][line]==1){
					line++;
				}else if(ladder[i][line]==-1){
					line--;
				}
			}
			if(j==line) {
				continue;
			}else {
				return false;
			}
		}
		return true;
	}
	
	static void dfs(int cnt,int n) {
		if(findAnswer)return;
		else if(cnt==n) {
			if(play()) {
				findAnswer = true;
			}
			return;
		}else {
			for(int i=1;i<=H;i++) {
				for(int j=1;j<N;j++) {
					if(ladder[i][j]==0&&ladder[i][j+1]==0) {
						ladder[i][j]=1;
						ladder[i][j+1]=-1;
						dfs(cnt+1,n);
						ladder[i][j]=0;
						ladder[i][j+1]=0;
					}
				}
			}
		}
	}
	
}
