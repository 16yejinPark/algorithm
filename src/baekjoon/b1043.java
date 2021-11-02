package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//거짓말
public class b1043 {
	static int[] parent;

	static int find(int x) {
		if(parent[x]==x) {
			return x;
		}else {
			return find(parent[x]);
		}
	}
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		parent[y] = x;
	}
	static boolean sameParent(int x, int y) {
		return (find(x)==find(y));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//사람의 수
		int M = Integer.parseInt(st.nextToken());	//파티의 수
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int tn = Integer.parseInt(st.nextToken());	//진실임을 아는 사람의 수
		int[] truePeople = new int[tn];
		for(int i=0;i<tn;i++) {
			truePeople[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] parties = new int[M][N];
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int i=0;i<n;i++) {
				parties[m][i]=Integer.parseInt(st.nextToken());
				if(i>0) {
					//System.out.printf("union %d %d\n",parties[m][i],parties[m][i-1]);
					union(parties[m][i],parties[m][i-1]);
				}
			}
		}
		
		int cnt = 0;
		for(int[] peoples : parties) {
			party:for(int p : peoples) {
				for(int tp : truePeople) {
					if(sameParent(tp,p)) {
						cnt--;
						break party;
					}
				}
			}
			cnt++;
		}		
		System.out.println(cnt);
	}
}
