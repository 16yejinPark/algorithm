package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//문제집
public class b1766 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//정점 개수
		int M = Integer.parseInt(st.nextToken());	//간선 개수
		
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			adjList.add(new ArrayList<Integer>());
		}
		
		int nodeNum[] = new int[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	//정점 개수
			int B = Integer.parseInt(st.nextToken());	//간선 개수
			adjList.get(A).add(B);
			nodeNum[B]++;
		}
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for(int i=1;i<=N;i++) {
			if(nodeNum[i]==0) {
				q.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			int n = q.poll();
			for(int j=0;j<adjList.get(n).size();j++) {
				int nn = adjList.get(n).get(j);
				nodeNum[nn]--;
				if(nodeNum[nn]==0) {
					q.add(nn);
				}
			}
			sb.append(n).append(" ");
		}
		System.out.println(sb.toString());
	}
}
