package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1251 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			Island[] island = new Island[N];
			StringTokenizer xs = new StringTokenizer(br.readLine());
			StringTokenizer ys = new StringTokenizer(br.readLine());
			for(int n=0;n<N;n++) {
				int x = Integer.parseInt(xs.nextToken());
				int y = Integer.parseInt(ys.nextToken());
				island[n]=new Island(x,y);
			}
			double E = Double.parseDouble(br.readLine());
			
			
			PriorityQueue<Tunnel> q = new PriorityQueue<Tunnel>();
			boolean[] visited = new boolean[N];
			q.add(new Tunnel(0,0));
			int cnt=0;
			double total=0;
			while(!q.isEmpty()) {
				Tunnel tunnel = q.poll();
				if(visited[tunnel.dstn])continue;
				visited[tunnel.dstn]=true;
				
				
				total+=(tunnel.length*tunnel.length*E);
				for(int i=0;i<N;i++) {
					if(!visited[i]) {
						q.add(new Tunnel(i,getDistance(island[tunnel.dstn],island[i])));
					}
				}
				if(++cnt==N)break;
				//System.out.printf("#%d %d %d\n",cnt,island[tunnel.dstn].x,island[tunnel.dstn].y);
			}
			
			System.out.format("#%d %.0f\n",t,total);
		}

	}
	private static double getDistance(Island i1, Island i2) {
		return Math.sqrt(Math.pow(i1.x-i2.x,2)+ Math.pow(i1.y-i2.y,2));
	}

}

class Island{
	public int x;
	public int y;
	public Island(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

class Tunnel implements Comparable<Tunnel>{
	public int dstn;
	public double length;
	public Tunnel(int dstn, double length) {
		super();
		this.dstn = dstn;
		this.length = length;
	}
	@Override
	public int compareTo(Tunnel o) {
		return Double.compare(this.length, o.length);
	}
}
