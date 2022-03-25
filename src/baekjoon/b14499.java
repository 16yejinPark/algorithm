package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//주사위 굴리기
public class b14499 {
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		int x = Integer.parseInt(st.nextToken()); // 주사위 위치
		int y = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken()); // 명령의 개수
		int map[][] = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		//명령 입력 받기
		//동 서 북 남(1,2,3,4)
		st = new StringTokenizer(br.readLine());
		Dice dice = new Dice();
		for (int l = 0; l < L; l++) {
			int d = Integer.parseInt(st.nextToken());
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx>=0&&nx<N&&ny>=0&&ny<M) {
				dice.roll(d);
				System.out.println(dice.one);
				if(map[nx][ny]==0) {
					map[nx][ny]=dice.six;
				}else {
					dice.six = map[nx][ny];
					map[nx][ny]=0;
				}
				x = nx;
				y = ny;
			}
		}
	}
	
	static class Dice{
		int one;
		int two;
		int three;
		int four;
		int five;
		int six;
		public Dice() {
			one=0;two=0; three=0; four=0;five=0;six=0;
		}
		public void roll(int d) {
			int temp;
			switch(d) {
			case 1:	//동
				temp = three;
				three = one;
				one = four;
				four = six;
				six = temp;
				break;
			case 2:	//서
				temp = three;
				three = six;
				six = four;
				four = one;
				one = temp;
				break;
			case 3:	//북
				temp = one;
				one = five;
				five = six;
				six = two;
				two = temp;
				break;
			case 4:	//남
				temp = one;
				one = two;
				two = six;
				six = five;
				five = temp;
				break;
			}
		}
	}
}
