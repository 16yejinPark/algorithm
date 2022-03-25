package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//어드벤처 게임
public class b2310 {
	static int N;
	static boolean visited[];
	static Room[] rooms;
	static boolean possible;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;

			rooms = new Room[N+1];
			for(int i=1;i<=N;i++) {
				st = new StringTokenizer(br.readLine());
				char type = st.nextToken().charAt(0);
				int fee = Integer.parseInt(st.nextToken());
				Room r = new Room(type,fee);
				while(true) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp==0)
						break;
					r.roomlist.add(temp);
				}
				rooms[i] = r;
			}
			
			int capital =0;
			visited = new boolean[N+1];
			possible=false;
			visited[1]=true;
			if(rooms[1].type=='T') {
				if(rooms[1].fee>0) {
					System.out.println("No");
					continue;
				}
			}
			if(rooms[1].type=='L') {
				capital = rooms[1].fee;
			}
			dfs(0,1);
			System.out.println(possible?"Yes":"No");
		}
	}
	public static void dfs(int capital,int n) {
		if(possible) {
			return;
		}
		if(n==N) {
			possible = true;
			return;
		}
		for(Integer next : rooms[n].roomlist) {
			int money = rooms[next].fee;
			if(!visited[next]) {
				visited[next]=true;
				if(rooms[next].type=='T') {
					if(capital-money>=0) {
						dfs(capital-money, next);
					}
				}
				if(rooms[next].type=='L') {
					if(capital>money) {
						dfs(capital, next);
					}else {
						dfs(money, next);
					}
				}
				if(rooms[next].type=='E') {
					dfs(capital, next);
				}
				visited[next]=false;
			}
		}
	}
	
	
	
	public static class Room{
		char type;
		int fee;
		ArrayList<Integer> roomlist = new ArrayList<Integer>();
		public Room(char type, int fee) {
			super();
			this.type = type;
			this.fee = fee;
		}
	}
}
