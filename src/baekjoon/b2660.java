package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2660 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());	//회원의 수 <= 50
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			list.add(new ArrayList<Integer>());
			answer.add(new ArrayList<Integer>());
		}

		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==-1&&b==-1) {
				break;
			}
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		int minDepth = Integer.MAX_VALUE;
		for(int i=1;i<=N;i++) {
			int depth = 0;
			Queue<int[]> q =new LinkedList<>();
			boolean visited[] = new boolean[N+1];
			visited[i] = true;
			q.add(new int[] {i,0});
			while(!q.isEmpty()) {
				int[] temp = q.poll();
				int n = temp[0];
				int d = temp[1];
				if(depth<d) 
					depth = d;
				for(int j=0;j<list.get(n).size();j++) {
					int nn = list.get(n).get(j);
					if(!visited[nn]) {
						visited[nn] = true;
						q.add(new int[] {nn,d+1});
					}
				}
			}
			if(minDepth>depth) 
				minDepth=depth;
			answer.get(depth).add(i);
		}
		
		StringBuilder sb= new StringBuilder();
		sb.append(minDepth).append(" ").append(answer.get(minDepth).size()).append("\n");
		Collections.sort(answer.get(minDepth));
		for(int i=0;i<answer.get(minDepth).size();i++) {
			sb.append(answer.get(minDepth).get(i)).append(" ");
		}
		System.out.println(sb.toString());
	}
}
