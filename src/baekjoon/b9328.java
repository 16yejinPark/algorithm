package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//열쇠
//열쇠 똑같은거 여러게 주웠을때 제대로 처리 안해줘서 틀림 ㅜ
public class b9328 {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean changed;
	static boolean[][] visited;
	static long key;
	static int answer;
	static int H;
	static int W;
	static char[][] building;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			building = new char[H][W];
			for(int h=0;h<H;h++) {
				String floor = br.readLine();
				for(int w=0;w<W;w++) {
					building[h][w]=floor.charAt(w);
				}
			}
			
			key = 0L;
			String str = br.readLine();
			if(str.equals("0")) {
			}else {
				for(int i=0;i<str.length();i++) {
					findKey(str.charAt(i));
				}
			}
			
			answer = 0;
			changed = true;
			while(changed) {
				changed = false;
				visited = new boolean[H][W];
				for(int i=0;i<H;i++) {
					for(int j=0;j<W;j++) {
						if(i==0||i==H-1||j==0||j==W-1) {
							if(!visited[i][j]&&building[i][j]!='*') {
								bfs(i,j);
							}
						}
					}
				}
			}
			System.out.println(answer);
		}
	}
	
	static boolean haveKey(char c) {
		int n = 25-(c-'A');
		return (key >> n)%2==1;
	}

	static void findKey(char c) {
		int n = 25-(c-'a');
		if(haveKey((char) (c-32))) {
			return;
		}
		key += 1<< n;
	}
	
	static void bfs(int h,int w) {

		Queue<int[]> q = new LinkedList<>();
		visited[h][w] = true;
		
		if(building[h][w]=='.') {
			q.add(new int[] {h,w});
		}else if(building[h][w]=='$') {
			answer++;
			building[h][w]='.';
			q.add(new int[] {h,w});
		}else if(building[h][w]>='a'&&building[h][w]<='z') {
			changed = true;
			findKey(building[h][w]);
			building[h][w]='.';
			q.add(new int[] {h,w});
		}else if(building[h][w]>='A'&&building[h][w]<='Z') {
			if(haveKey(building[h][w])) {
				building[h][w]='.';
				q.add(new int[] {h,w});
			}
		}

		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			//System.out.printf("%d %d\n",x,y);
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<H&&ny>=0&&ny<W&&!visited[nx][ny]&&building[nx][ny]!='*') {
					visited[nx][ny] = true;
					if(building[nx][ny]=='.') {
						q.add(new int[] {nx,ny});
					}else if(building[nx][ny]=='$') {
						answer++;
						building[nx][ny]='.';
						q.add(new int[] {nx,ny});
					}else if(building[nx][ny]>='a'&&building[nx][ny]<='z') {
						changed = true;
						findKey(building[nx][ny]);
						building[nx][ny] = '.';
						q.add(new int[] {nx,ny});
					}else if(building[nx][ny]>='A'&&building[nx][ny]<='Z') {
						if(haveKey(building[nx][ny])) {
							building[nx][ny] = '.';
							q.add(new int[] {nx,ny});
						}
					}
				}
			}
			
		}
	}
	
}
