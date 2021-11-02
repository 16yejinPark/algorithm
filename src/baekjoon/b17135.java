package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class b17135 {
	public static int N;
	public static int M;
	public static int D;
	public static int[][] enemy_map;
	public static int[][] shinGung;
	public static int[] selected;
	public static int maxKill = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		enemy_map = new int[15][15];
		shinGung = new int[3][];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				enemy_map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		selected = new int[3];
		getShinGungLoc(0, 0);
		System.out.println(maxKill);
	}

	private static void getShinGungLoc(int cnt, int idx) {
		if(cnt==3) {
			int[][] enemy = new int[N][M];
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					enemy[i][j] = enemy_map[i][j];
				}
			}
			
			int kill=0;	
			//n은 신궁의 row
			int n=N;
			for(int i=0;i<3;i++) {
				shinGung[i] = new int[]{n,selected[i]};
			}
			//배열의 가장 왼쪽 밑부터 탐색
			for(int round=0;round<N;round++) {
				List<Integer[]> target = new ArrayList<>();
				for(int i=0;i<3;i++) {
					int er=-1;
					int ec=-1;
					int min_dis=-1;
					for(int c=0;c<M;c++){
						for(int r=N-1-round;r>=0;r--){
							if(enemy[r][c]==1&&(Math.abs(shinGung[i][1]-c)+(shinGung[i][0]-r-round))<=D&&(min_dis==-1||(Math.abs(shinGung[i][1]-c)+(shinGung[i][0]-r)<min_dis))) {
								er = r;
								ec = c;
								min_dis = Math.abs(shinGung[i][1]-c)+(shinGung[i][0]-r);
							}
						}
					}
					if(er!=-1&&ec!=-1) {
						target.add(new Integer[]{er,ec});
					}
				}
				int num=0;
				for(int i=0;i<target.size();i++) {
					if(enemy[target.get(i)[0]][target.get(i)[1]]==0) {
						continue;
					}
					else{
						enemy[target.get(i)[0]][target.get(i)[1]]=0;
						num++;
					}
				}
				kill+=num;
			}
			
			if(maxKill<kill) {
				maxKill=kill;
			}
			return;
		}
		for(int i=idx;i<M;i++) {
				selected[cnt] = i;
				getShinGungLoc(cnt+1,i+1);
		}
	}
}
