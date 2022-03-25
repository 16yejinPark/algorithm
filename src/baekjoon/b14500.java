package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b14500 {
	static int[][][] tetromino = {
			{{1,0},{2,0},{3,0}},	//0. ㅡ
			{{0,1},{0,2},{0,3}},	//1. ㅣ
			{{1,1},{0,1},{1,0}},		//2. ㅁ
			{{1,0},{2,0},{2,1}},	//3. L
			{{1,0},{2,0},{2,-1}},	//4.
			{{0,1},{0,2},{1,2}},	//5.
			{{0,1},{0,2},{-1,2}},	//6.
			{{-1,0},{-2,0},{-2,1}},	//7.
			{{-1,0},{-2,0},{-2,-1}},//8.
			{{0,-1},{0,-2},{1,-2}},	//9.
			{{0,-1},{0,-2},{-1,-2}},//10.
			{{1,0},{1,1},{2,1}},		//11. ㄹ
			{{1,0},{1,-1},{2,-1}},		//12.
			{{0,1},{-1,1},{-1,2}},		//13.
			{{0,1},{1,1},{1,2}},		//14.
			{{0,1},{0,2},{1,1}},	//15. ㅏ
			{{0,1},{0,2},{-1,1}},	//16.
			{{1,0},{2,0},{1,1}},	//17.
			{{1,0},{2,0},{1,-1}}	//18.
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		
		int max = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int k=0;k<19;k++) {
					int sum = board[i][j];
					boolean findTetro = false;
					for(int l=0;l<3;l++) {
						int ni = i + tetromino[k][l][0];
						int nj = j + tetromino[k][l][1];
						if(ni<0||ni>=N||nj<0||nj>=M) {
							continue;
						}
						sum+=board[ni][nj];
						if(l==2) {
							findTetro = true;
						}
					}
					if(findTetro) {
						max = Math.max(max,sum);
					}
				}
			}			
		}
		System.out.println(max);
	}
}
