package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//줄세우기
//위상 정렬
public class b2252 {
	static int N;
	static int M;
	static int[] edgeNum;
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//학생 수
		M = Integer.parseInt(st.nextToken());	//비교 횟수
		
		for(int i=0;i<=N;i++) {
			adjList.add(new ArrayList<Integer>());
		}
		
		edgeNum = new int[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	
			int B = Integer.parseInt(st.nextToken());	
			adjList.get(A).add(B);
			edgeNum[B]++;
		}
		
		sort();
		System.out.println(sb.toString());
		
	}
	
	static void sort() {
		
		Queue<Integer> q = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			if(edgeNum[i]==0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int n = q.poll();
			for(int i=0;i<adjList.get(n).size();i++) {
				int nn = adjList.get(n).get(i);
				edgeNum[nn]--;
				if(edgeNum[nn]==0) {
					q.add(nn);
				}
			}
			sb.append(n).append(" ");
		}
	}

}
