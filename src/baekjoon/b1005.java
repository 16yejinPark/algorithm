package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//ACM Craft
public class b1005 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		

		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//정점
			int K = Integer.parseInt(st.nextToken());	//간선
			
			int[] weight = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++) {
				weight[n] = Integer.parseInt(st.nextToken());
			}
			
			int[] nodeNum = new int[N+1];
			ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
			for(int n=0;n<=N;n++) {
				adjList.add(new ArrayList<Integer>());
			}
			for(int k=1;k<=K;k++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				adjList.get(A).add(B);
				nodeNum[B]++;
			}
			
			int answer = 0;
			int target = Integer.parseInt(br.readLine());
			
			PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));	//{현재 정점 , 총비용}
			for(int i=1;i<=N;i++) {
				if(nodeNum[i]==0) {
					q.add(new int[] {i, weight[i]});
				}
			}
			
			for(int i=0;i<N;i++) {
				int[] temp = q.poll();
				int n = temp[0];
				int total = temp[1];
				
				if(n==target) {
					answer = total; 
					break;
				}
				
				for(int j=0;j<adjList.get(n).size();j++) {
					int nn = adjList.get(n).get(j);
					nodeNum[nn]--;
					if(nodeNum[nn]==0) {
						q.add(new int[] {nn,total+weight[nn]});
					}
				}
			}
			
			System.out.println(answer);
			
		}

	}

}
