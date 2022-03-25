package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

//드래곤 커브
public class b15685 {
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	static boolean map[][] = new boolean[101][101];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int Y = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());	//시작방향
			int G = Integer.parseInt(st.nextToken());	//세대
			
			ArrayList<Integer> dragon = new ArrayList<Integer>();
			Stack<Integer> s = new Stack<Integer>();
			dragon.add(D);

			for(int i=0;i<(int)Math.pow(2, G)-1;i++) {
				if(s.isEmpty()) {
					for(Integer d : dragon) {
						s.push(d);
					}
				}
				int d = s.pop();
				d = (d+1)%4;
				dragon.add(d);
			}
			
			map[X][Y] = true;
			for(Integer d : dragon) {
				X += dx[d];
				Y += dy[d];
				map[X][Y] = true;
			}
		}
		
		//답 출력
		int cnt = 0;
		for(int i=0;i<100;i++){
			for(int j=0;j<100;j++){
				if(map[i][j]&&map[i][j+1]&&map[i+1][j]&&map[i+1][j+1]) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

}
