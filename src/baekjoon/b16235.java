package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//나무 재테크
public class b16235 {
	static int[] dx = {-1,-1,-1,0,1,1,1,0};
	static int[] dy = {-1,0,1,1,1,0,-1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//땅의 크기
		int M = Integer.parseInt(st.nextToken());	//나무의 개수
		int K = Integer.parseInt(st.nextToken());	//K년이 지난 후
		int nrshmt[][] = new int[N+1][N+1];
		int map[][] = new int[N+1][N+1];
		PriorityQueue<int[]> trees = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2],o2[2]);
			}});
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				nrshmt[i][j] = Integer.parseInt(st.nextToken());
				map[i][j]=5;
			}
		}
		
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());	//나무의 나이
			trees.add(new int[] {x,y,z});
		}
		
		for(int k=0;k<K;k++) {	
			//봄
			ArrayList<int[]> addList = new ArrayList<int[]>();
			ArrayList<int[]> removeList = new ArrayList<int[]>();
			while(!trees.isEmpty()) {
				int[] tree = trees.poll();
				int x = tree[0];
				int y = tree[1];
				if(map[x][y]>=tree[2]) {
					map[x][y]-=tree[2];
					tree[2]++;
					addList.add(tree);
				}else {
					removeList.add(tree);
				}
			}
			for(int[] tree : addList) {
				trees.add(tree);
			}
			
			//여름
			for(int[] tree : removeList) {
				int x = tree[0];
				int y = tree[1];
				map[x][y] += tree[2]/2;
			}
			
			
			//가을
			for(int i=0; i<addList.size();i++) {
				int[] tree = addList.get(i);
				if(tree[2]%5==0) {
					int x = tree[0];
					int y = tree[1];
					for(int d=0;d<8;d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if(nx>0&&nx<=N&&ny>0&&ny<=N) {
							trees.add(new int[] {nx,ny,1});
						}
					}
				}
			}
			
			//겨울
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					map[i][j] += nrshmt[i][j];
				}
			}
			
		}
		System.out.println(trees.size());
	}
}
