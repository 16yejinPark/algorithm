
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Main2 {
	
	static boolean[][] selected;
	static int[][] dist;
	static int[] dx  = {-1,1,0,0};
	static int[] dy  = {0,0,-1,1};
	
	
    public static int[] solution(int n, int k) {    
        int r = 0;
        int c = 0;
        selected = new boolean[n+1][n+1];
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)-> {
        	if(o1[1]==o2[1]) {
        		return Integer.compare(o1[0], o2[0]);
        	}
        	return Integer.compare(o1[1], o2[1]);
        });
        
        for(int rep =0;rep<k;rep++){
        	if(rep == 0) {
        		selected[1][1] = true;
        		r = 1;
        		c = 1;
        	}else {
        		dist = new int[n+1][n+1];
        		int max = 0;
        		for(int i=1;i<=n;i++) {
        			for(int j=1;j<=n;j++) {
        				if(!selected[i][j]) {
        					dist[i][j] = bfs(i,j,n);
                			max = Math.max(max, dist[i][j]);
        				}
            		}
        		}
        		
        		for(int i=1;i<=n;i++) {
        			for(int j=1;j<=n;j++) {
        				System.out.print(dist[i][j]+" ");
        			}System.out.println();
            	}System.out.println();
            	
        		q.clear();
        		for(int i=1;i<=n;i++) {
        			for(int j=1;j<=n;j++) {
            			if(dist[i][j]==max) {
            				q.add(new int[] {i,j});
            			}
            		}
        		}
        		int temp[] = q.poll();
        		r = temp[0];
        		c = temp[1];
        		selected[r][c] = true;
        	}
        	
        }
        
        return new int[] {r,c};
    }
    
    
    
	private static int bfs(int r, int c, int n) {
		int[][] visited = new int[n+1][n+1];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>0&&nx<=n&&ny>0&&ny<=n&&visited[nx][ny]==0&&!(r==nx&&c==ny)) {
					if(selected[nx][ny]) {
						return visited[x][y] + 1;
					}else {
						 visited[nx][ny] = visited[x][y] + 1;
						 q.add(new int[] {nx,ny});
					}
				}
			}
		}
		return 0;
	}



	public static void main(String[] args) throws IOException, ParseException {
		
		int[] result = solution(4,16);
		for(int i : result) {
			System.out.print(i+" ");
		}
	}

}
