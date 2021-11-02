package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//최적경로
public class s1247 {
	public static int N;
	public static int minDistance;
	public static int[] company;
	public static int[] house;
	public static int[][] customer;
	public static int[] visitOrder;
	public static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			minDistance=-1;
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			visitOrder = new int[N];
			visited = new boolean[N];
			company = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			house = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			customer = new int[N][2];
			for(int n=0;n<N;n++) {
				customer[n][0]=Integer.parseInt(st.nextToken());
				customer[n][1]=Integer.parseInt(st.nextToken());
			}
			
			getCase(0);
			System.out.printf("#%d %d\n",t,minDistance);
		}
	}
	
	public static void getCase(int cnt) {
		if(cnt==N) {
			int distance = getDistance(company[0],company[1],customer[visitOrder[0]][0],customer[visitOrder[0]][1]);
			for(int i=0;i<N-1;i++) {
				if(minDistance!=-1&&minDistance<distance)
					return;
				distance += getDistance(customer[visitOrder[i]][0],customer[visitOrder[i]][1],
						customer[visitOrder[i+1]][0],customer[visitOrder[i+1]][1]);
			}
			distance += getDistance(house[0],house[1],customer[visitOrder[N-1]][0],customer[visitOrder[N-1]][1]);
			if(minDistance==-1||minDistance>distance)
				minDistance=distance;
			return;
		}
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i]=true;
				visitOrder[cnt]=i;
				getCase(cnt+1);
				visited[i]=false;
			}
		}
	}
	
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
}
