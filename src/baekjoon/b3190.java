package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//뱀
public class b3190 {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine()); // N*N의 보드
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		int[][] map = new int[N + 1][N + 1];
		map[1][1] = 1;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 4;
		}
		int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
		ArrayList<int[]> turn = new ArrayList<int[]>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			int dir = d.equals("L") ? 1 : 2; // 왼쪽은 1, 오른쪽2
			turn.add(new int[] { t, dir });
		}

		int time = 0;
		int d = 0;
		Deque<int[]> snake = new ArrayDeque<int[]>();
		snake.add(new int[] { 1, 1 });
		while (true) {
			int[] head = snake.getFirst();
			int hx = head[0] + dx[d];
			int hy = head[1] + dy[d];

			// 자기 자신의 몸과 부딪힘
			if (map[hx][hy] == 1) {
				break;
			}
			// 사과가 유무 확인
			if (map[hx][hy] != 4) {
				int[] tail = snake.removeLast();
				map[tail[0]][tail[1]]=0;
			}
			
			//방향 확인
			time++;
			if (turn.size() > 0) {
				if (turn.get(0)[0] == time) {
					if (turn.get(0)[1] == 2) {
						d = (d + 1) % 4;
					} else {
						d = d - 1;
						d = (d >= 0 ? d : d + 4);
					}
					turn.remove(0);
				}
			}
			
			// 벽에 부딪힘
			if((hy==N&&d==0)||(hy==1&&d==2)||(hx==N&&d==1)||(hx==1&&d==3)) {
				break;
			}
			
			map[hx][hy]=1;
			snake.addFirst(new int[] {hx,hy});
		}
		System.out.println(time+1);

	}
}
