package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//모노미노도미노2
public class b20061 {
	static boolean blue[][] = new boolean[4][6];
	static boolean green[][] = new boolean[6][4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine()); // 블록을 놓은 횟수
		int score = 0;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken()); // 1:1X1 2:1X2 3:2X1
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int greenTop = 5;
			int blueTop=5;
			switch (t) {
			case 1:
				for(int i=2;i<6;i++) {
					if(green[i][y]) {
						greenTop = i-1;
						break;
					}
				}
				for(int i=2;i<6;i++) {
					if(blue[x][i]) {
						blueTop = i-1;
						break;
					}
				}
				green[greenTop][y] = true;
				blue[x][blueTop] = true;
				break;
			case 2:
				for(int i=2;i<6;i++) {
					if(green[i][y]) {
						greenTop = i-1;
						break;
					}
					if(green[i][y+1]) {
						greenTop = i-1;
						break;
					}
				}
				for(int i=2;i<6;i++) {
					if(blue[x][i]) {
						blueTop = i-1;
						break;
					}
				}
				green[greenTop][y] = true;
				green[greenTop][y + 1] = true;

				blue[x][blueTop] = true;
				blue[x][blueTop-1] = true;
				break;
			case 3:
				for(int i=2;i<6;i++) {
					if(green[i][y]) {
						greenTop = i-1;
						break;
					}
				}
				for(int i=2;i<6;i++) {
					if(blue[x][i]) {
						blueTop = i-1;
						break;
					}
					if(blue[x+1][i]) {
						blueTop = i-1;
						break;
					}
				}
				green[greenTop][y] = true;
				green[greenTop-1][y] = true;

				blue[x][blueTop] = true;
				blue[x + 1][blueTop] = true;
				break;
			}

			// 점수 더하기
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (!green[i][j]) {
						break;
					}
					if (j == 3) {
						score++;
						pull(i - 1, 0, 'G');
					}
				}
			}
			for (int j = 0; j < 6; j++){
				for (int i = 0; i < 4; i++){
					if (!blue[i][j]) {
						break;
					}
					if (i == 3) {
						score++;
						pull(j - 1, 0, 'B');
					}
				}
			}

			// 0,1영역 침범 체크
			//green
			for (int j = 0; j < 4; j++) {
				if(green[0][j]) {
					pull(3, 0, 'G');
					pull(4, 1, 'G');
					break;
				}
			}
			
			for (int j = 0; j < 4; j++) {
				if(green[1][j]) {
					pull(4, 1, 'G');
					break;
				}
			}
			
			// 0,1영역 침범 체크
			// blue
			for (int j = 0; j < 4; j++) {
				if(blue[j][0]) {
					pull(3, 0, 'B');
					pull(4, 1, 'B');
					break;
				}
			}
			
			for (int j = 0; j < 4; j++) {
				if(blue[j][1]) {
					pull(4, 1, 'B');
					break;
				}
			}
			
			
//			System.out.println("/////////////////////////////"+(n+1));
//			for(int i=0;i<6;i++) {
//				for(int j=0;j<4;j++) {
//					System.out.print(green[i][j]+" ");
//				}System.out.println();
//			}System.out.println();
//			
//			for(int i=0;i<4;i++) {
//				for(int j=0;j<6;j++) {
//					System.out.print(blue[i][j]+" ");
//				}System.out.println();
//			}System.out.println();
		}

		System.out.println(score);
		int cnt=0;
		for(int i=0;i<6;i++) {
			for(int j=0;j<4;j++) {
				if(green[i][j]) {
					cnt++;
				}
			}
		}
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<6;j++) {
				if(blue[i][j]) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	static void pull(int s, int e, char color) {
		if (color == 'G') {
			for (int i = s; i >= e; i--) {
				for (int j = 0; j < 4; j++) {
					green[i + 1][j] = green[i][j];
				}
			}
			for (int j = 0; j < 4; j++) {
				green[e][j]=false;
			}
		}

		if (color == 'B') {
			for (int j = s; j >= e; j--) {
				for (int i = 0; i < 4; i++) {
					blue[i][j + 1] = blue[i][j];
				}
			}
			for (int i = 0; i < 4; i++) {
				blue[i][e]=false;
			}
		}
			
	}
}
