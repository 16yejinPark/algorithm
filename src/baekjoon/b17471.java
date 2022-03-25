package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//게리맨더링
public class b17471 {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int population[];
	static int total = 0;
	static boolean checked[];
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		checked = new boolean[N+1];
		population = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			population[i] = Integer.parseInt(st.nextToken());
			total += population[i];
		}
		
		adjList.add(new ArrayList<Integer>());
		for(int i=1;i<=N;i++) {
			adjList.add(new ArrayList<Integer>());
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j=0;j<n;j++) {
				int t = Integer.parseInt(st.nextToken());
				adjList.get(i).add(t);
			}
		}
		
		getSubset(1);
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}
	
	static void getSubset(int i) {
		if(i>N) {
			if(isConnected()) {
				getDiff();
			}
			return;
		}
		checked[i] = true;
		getSubset(i+1);
		checked[i] = false;
		getSubset(i+1);
	}
	
	static void getDiff() {
		int sum = 0;
		for(int i=1;i<=N;i++) {
			if(checked[i]) {
				sum += population[i];
			}
		}
		min = Math.min(Math.abs(total-sum-sum), min);
	}
	
	static boolean isConnected() {
		
		ArrayList<Integer> first = new ArrayList<Integer>();
		ArrayList<Integer> second = new ArrayList<Integer>();
		for(int i=1;i<=N;i++) {
			if(checked[i]) {
				first.add(i);
			}else {
				second.add(i);
			}
		}
		
		if(first.size()==0||second.size()==0)
			return false;
		
		boolean visited[] = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(first.get(0));
		visited[first.get(0)] = true;
		
		while(!q.isEmpty()) {
			int x = q.remove();
			for(int i=0;i<adjList.get(x).size();i++) {
				int nx = adjList.get(x).get(i);
				if(checked[nx]&&!visited[nx]) {
					visited[nx] = true;
					q.add(nx);
				}
			}
		}
		
		for(int i=0;i<first.size();i++) {
			if(!visited[first.get(i)]) {
				return false;
			}
		}
		
		visited = new boolean[N+1];
		q.add(second.get(0));
		visited[second.get(0)] = true;
		
		while(!q.isEmpty()) {
			int x = q.remove();
			for(int i=0;i<adjList.get(x).size();i++) {
				int nx = adjList.get(x).get(i);
				if(!checked[nx]&&!visited[nx]) {
					visited[nx] = true;
					q.add(nx);
				}
			}
		}
		
		for(int i=0;i<second.size();i++) {
			if(!visited[second.get(i)]) {
				return false;
			}
		}
		return true;
	}
}
