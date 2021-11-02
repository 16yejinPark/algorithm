package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s3289 {

	public static void makeSet() {
		for(int i=1;i<=n;i++)
			parent[i] = i;
	}
	public static int findSet(int x) {
		if(x==parent[x]) {
			return x;
		}
		parent[x]=findSet(parent[x]);
		return parent[x];
	}
	public static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if(px != py) {
			parent[py] = px;
		}
	}
	
	private static int[] parent;
	private static int n;
	private static int m;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			st= new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			parent = new int[n+1];
			makeSet();
			
			for(int i=0;i<m;i++) {
				st= new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				
				if(command==0) {
					union(x,y);
				}else if(command==1){
					int px = findSet(x);
					int py = findSet(y);
					if(px == py) {
						sb.append("1");
					}else {
						sb.append("0");
					}
				}
			}
			System.out.println(sb.toString());
		}

	}

}
