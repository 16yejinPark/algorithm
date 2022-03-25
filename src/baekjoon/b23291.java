package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//어항정리
public class b23291 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Floor[] floors;
	static int N;
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//어항의 수
		K = Integer.parseInt(st.nextToken());	//가장 많이 들어있는 어항과 가장 적게 들어있는 어항의 물고기 수 차이가 K 이하
		st = new StringTokenizer(br.readLine());
		
		boolean debug = true;
		
		floors = new Floor[N];
		for(int i=0;i<N;i++) {
			floors[i] = new Floor();
			floors[0].list.addLast(Integer.parseInt(st.nextToken()));
		}
		
		int t = 0;
		while(true) {
			if(debug)
			System.out.println("-------------------------------------------------------- "+t);
			//1. 먼저, 물고기의 수가 가장 적은 어항에 물고기를 한 마리 넣는다.
			int min = floors[0].getMin();
			int size = floors[0].list.size();
			for(int i=0;i<size;i++) {
				int temp = floors[0].list.removeFirst();
				if(temp==min)temp++;
				floors[0].list.addLast(temp);
			}

			//2. 어항을 쌓는다. 
			int x = 1;
			int y = 1;
			int nx = x;
			int ny = y;
			while(x <= floors[0].list.size()-y) {
				x = nx;
				y = ny;
				
				Floor[] deques = new Floor[x+1];
				for(int i=0;i<=x;i++) {
					deques[i] = new Floor();
				}
				
				for(int i=x-1;i>0;i--) {
					for(int j=0;j<y;j++) {
						int temp = floors[j].list.removeFirst();
						deques[i].list.addLast(temp);
					}
				}
				
				for(int i=x;i>0;i--) {
					int s = deques[i].list.size();
					for(int k=0;k<s;k++) {
						int temp = deques[i].list.removeFirst();
						floors[i].list.addLast(temp);
					}
					
				}				
				if(x==y) nx++;
				else ny++;	
			}
			print(debug,x,"2단계 끝 ");

			//3. 어항에 있는 물고기의 수를 조절한다. (동시에 발생함에 주의!)
			controlFishes(floors[0].list.size());	
			print(debug,x,"3단계 끝 ");
			
			//4. 다시 일렬로 놓는다.
			setFlat(x,y);
			print(debug,x,"4단계 끝 ");
			
			//5. 가운데를 중심으로 왼쪽 N/2개를 공중 부양시켜 전체를 시계 방향으로 180도 회전 시킨 다음, 오른쪽 N/2개의 위에 놓아야 한다. 이 작업은 두 번 반복해야한다.
			for(int i=0;i<N/2;i++) {
				floors[1].list.addFirst(floors[0].list.removeFirst());
			}
			print(debug,x,"4.5단계 끝 ");
			
			for(int i=0;i<2;i++) {
				for(int j=0;j<N/4;j++) {
					int temp = floors[i].list.removeFirst();
					floors[4-1-i].list.addFirst(temp);
				}
			}
			print(debug,x,"5단계 끝 ");
			
			//6. 어항에 있는 물고기의 수를 조절한다.	
			controlFishes(Math.max(4,floors[0].list.size()));	
			print(debug,x,"6단계 끝 ");
			
			//7. 다시 일렬로 놓는다.
			setFlat(4,N/4);
			print(debug,x,"7단계 끝 ");
			
			t++;
			if(floors[0].getMax()-floors[0].getMin()<=K) {
				break;
			}
			
		}
		System.out.println(t);
	}
	
	static void controlFishes(int n) {
		
		int[][] map = new int[n][n];
		for(int i=0;i<n;i++) {
			int s = floors[i].list.size();
			for(int j=0;j<s;j++) {
				int temp = floors[i].list.removeFirst();
				map[n-i-1][j]=temp;
				floors[i].list.addLast(temp);
			}	
		}
		
		int[][] temp = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				visited[i][j]=true;
				if(map[i][j]!=0) {
					for(int d=0;d<4;d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if(nx>=0&&nx<n&&ny>=0&&ny<n&&map[nx][ny]!=0&&!visited[nx][ny]&&Math.abs(map[nx][ny]-map[i][j])/5>0) {
							int num = Math.abs(map[nx][ny]-map[i][j])/5;
							if(map[nx][ny]-map[i][j]>0) {
								temp[i][j]+=num;
								temp[nx][ny]-=num;
							}else {
								temp[i][j]-=num;
								temp[nx][ny]+=num;
							}
						}
					}
				}
			}	
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] += temp[i][j];
			}
		}
		
		for(int i=0;i<n;i++) {
			int s = floors[i].list.size();
			for(int j=0;j<s;j++) {
				floors[i].list.removeFirst();
				floors[i].list.addLast(map[n-i-1][j]);
			}	
		}
		
		
	}
	
	static void setFlat(int x,int y) {
		ArrayDeque<Integer> tempDeque = new ArrayDeque<Integer>();		
		for(int i=0;i<y;i++) {
			for(int j=0;j<x;j++) {
				int temp = floors[j].list.removeFirst();
				tempDeque.addLast(temp);
			}
		}
		int size = tempDeque.size();
		for(int i=0;i<size;i++) {
			int temp = tempDeque.removeLast();
			floors[0].list.addFirst(temp);
		}
	}
	
	static void printDeque(ArrayDeque<Integer> deque) {
		int sss = deque.size();
		for(int i=0;i<sss;i++) {
			int temp = deque.removeFirst();
			System.out.printf("%5d ",temp);
			deque.addLast(temp);
		}System.out.println();
	}
	
	static void print(boolean debug,int x,String memo) {
		if(debug) {
		memo+="///////////////";
		System.out.println(memo);
		for(int i=x;i>=0;i--) {
			System.out.print(i+"| ");
			printDeque(floors[i].list);
		}}
	}
	
	static class Floor{
		ArrayDeque<Integer> list = new ArrayDeque<Integer>();
		public int getMin() {
			PriorityQueue<Integer> q = new PriorityQueue<>();
			int size = list.size();
			for(int i=0;i<size;i++) {
				int temp = list.removeFirst();
				q.add(temp);
				list.addLast(temp);
			}
			return q.remove();
		}
		public int getMax() {
			PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
			int size = list.size();
			for(int i=0;i<size;i++) {
				int temp = list.removeFirst();
				q.add(temp);
				list.addLast(temp);
			}
			return q.remove();
		}
	}
}
