package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//근손실
public class b18429 {
	static int[] kit;
	static int total=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		kit = new int[N];
		boolean[] visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			kit[i]=Integer.parseInt(st.nextToken());
		}
		getCase(N,K,0,500,visited);
		System.out.println(total);
	}
	static void getCase(int N,int K,int cnt,int w,boolean[] visited) {
		if(w<500) {
			return;
		}else if(cnt==N){
			total++;
			return;
		}
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i]=true;
				getCase(N,K,cnt+1,w+kit[i]-K,visited);
				visited[i]=false;
			}
		}
	}
}
