package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//파티
//https://sskl660.tistory.com/59
public class b1238 {
	static int N;
	static int M;
	static int X;
	static ArrayList<ArrayList<int[]>> adjList = new ArrayList<ArrayList<int[]>>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//정점 수
		M = Integer.parseInt(st.nextToken());	//간선 수
		X = Integer.parseInt(st.nextToken());	//파티 장소
		for(int i=0;i<=N;i++) {
			adjList.add(new ArrayList<int[]>());
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			adjList.get(s).add(new int[] {e,d});
		}
		int[] partyToHome = getMinDist(X);
		int maxDist = 0;
		for(int i=1;i<=N;i++) {
			if(i==X)continue;
			int[] homeToParty = getMinDist(i);
			
			if(maxDist<homeToParty[X]+partyToHome[i]) {
				maxDist=homeToParty[X]+partyToHome[i];
			}	
		}
		System.out.println(maxDist);
	}
	static int[] getMinDist(int i) {
		
		int[] minDist = new int[N+1];
		for(int j=0;j<=N;j++) {
			minDist[j] = Integer.MAX_VALUE;
		}
		PriorityQueue<Integer[]> pq = new PriorityQueue<Integer[]>((o1,o2)->Integer.compare(o1[1],o2[1]));
		pq.add(new Integer[] {i,0});
		minDist[i]=0;
		while(!pq.isEmpty()) {
			Integer[] node = pq.poll();
			int n = node[0];
			int d = node[1];
			
			if(minDist[n] < d) {
				continue;
			}
			
			for(int x = 0; x<adjList.get(n).size();x++) {
				int[] newNode = adjList.get(n).get(x);
				int nn = newNode[0];
				int nd = newNode[1];
				if(minDist[nn]>d+nd) {
					minDist[nn]=d+nd;
					pq.add(new Integer[] {nn,minDist[nn]});
				}
			}
		}
		return minDist;
	}
}
