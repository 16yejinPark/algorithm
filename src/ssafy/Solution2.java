package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//비상구 탈출
public class Solution2 {
	static int map[][][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			
			ArrayList<ArrayList<Integer>> tasks = new  ArrayList<ArrayList<Integer>>();
			for(int i=0;i<N+1;i++) {
				tasks.add(new ArrayList<Integer>());
			}
			
			for(int i=1;i<=N;i++) {	//업무 입력받기
				st = new StringTokenizer(br.readLine());
				while(true) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp==0)break;
					tasks.get(i).add(temp);
				}
			}
			
			boolean[] canWork = new boolean[N+1];
			for(int i=1;i<=N;i++) {
				if(tasks.get(i).size()==1) {
					canWork[i] = true;
				}
			}
			System.out.printf("#%d %d\n",t,t);
		}
	}	
}
