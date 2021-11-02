package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//숨바꼭질
public class b1697 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int subin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());
		int[] loc = new int[100001];
		boolean[] visited = new boolean[100001];
		loc[subin]=0;
		visited[subin]=true;
		Queue<Integer> q = new LinkedList<>();
		q.add(subin);
		while(!q.isEmpty()) {
			int l = q.remove();
			if(l==sister) {
				break;
			}
			if(l>0&&!visited[l-1]) {
				loc[l-1]=loc[l]+1;
				visited[l-1]=true;
				q.add(l-1);
			}
			if(l+1<=100000&&!visited[l+1]) {
				loc[l+1]=loc[l]+1;
				visited[l+1]=true;
				q.add(l+1);
			}
			if(l*2<=100000&&!visited[l*2]) {
				loc[l*2]=loc[l]+1;
				visited[l*2]=true;
				q.add(l*2);
			}
		}
		System.out.println(loc[sister]);
	}
}
