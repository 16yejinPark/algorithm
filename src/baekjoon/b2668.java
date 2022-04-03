package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//숫자 고르기
public class b2668 {
	static int N;
	static int nums[];
	static boolean visited[];
	static PriorityQueue<Integer> answer = new PriorityQueue<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N+1];
		for(int i=1;i<=N;i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		visited = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			visited[i] = true;
			dfs(i,i);
			visited[i] = false;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(answer.size()).append("\n");
		while(!answer.isEmpty()) {
			sb.append(answer.poll()).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void dfs(int s,int e) {
		if(!visited[nums[s]]) {
			visited[nums[s]] = true;
			dfs(nums[s],e);
			visited[nums[s]] = false;
		}
		if(nums[s]==e)answer.add(e);
	}
}
