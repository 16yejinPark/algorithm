package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//주사위 윷놀이
public class b17825 {
	static int[] horse = {0,0,0,0};
	static boolean[] arrive;
	static HashMap<Integer,Kan> board;
	static int[] move;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		move = new int[10];
		arrive = new boolean[4];
		for(int i=0;i<10;i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		
		board = new HashMap<Integer,Kan>();
		board.put(0, new Kan(1,0));
		board.put(1, new Kan(2,2));
		board.put(2, new Kan(3,4));
		board.put(3, new Kan(4,6));
		board.put(4, new Kan(5,8));
		board.put(5, new Kan(21,10));
		board.put(6, new Kan(7,12));
		board.put(7, new Kan(8,14));
		board.put(8, new Kan(9,16));
		board.put(9, new Kan(10,18));
		board.put(10, new Kan(25,20));
		board.put(11, new Kan(12,22));
		board.put(12, new Kan(13,24));
		board.put(13, new Kan(14,26));
		board.put(14, new Kan(15,28));
		board.put(15, new Kan(27,30));
		board.put(16, new Kan(17,32));
		board.put(17, new Kan(18,34));
		board.put(18, new Kan(19,36));
		board.put(19, new Kan(20,38));
		board.put(20, new Kan(99,40));	//99가 도착점
		board.put(21, new Kan(22,13));
		board.put(22, new Kan(23,16));
		board.put(23, new Kan(24,19));
		board.put(24, new Kan(30,25));
		board.put(25, new Kan(26,22));
		board.put(26, new Kan(24,24));
		board.put(27, new Kan(28,28));
		board.put(28, new Kan(29,27));
		board.put(29, new Kan(24,26));
		board.put(30, new Kan(31,30));
		board.put(31, new Kan(20,35));
		dfs(0,0);
		System.out.println(max);
	}
	
	static void dfs(int cnt, int score) {
		if(cnt==10) {
			max = Math.max(max, score);
			return;
		}else {
			//0번 말 move
			moveHorse(0,cnt,score);
			
			//1번 말 move
			//moveHorse(1,cnt,score);
			
			//2번 말 move
			//moveHorse(2,cnt,score);
			
			//3번 말 move
			//moveHorse(3,cnt,score);
		}
	}
	
	static void moveHorse(int n,int cnt,int score) {
		int temp;
		if(!arrive[n]) {
			System.out.print(horse[n]+">>>>>>>>>>");
			temp = horse[n];
			for(int i=0;i<move[cnt];i++) {
				horse[n] = board.get(horse[n]).next;
				if(horse[n]==99) {
					arrive[n] = true;
					break;
				}
			}
			System.out.println(horse[n]);
			if(!board.get(horse[n]).isEmpty) {
				horse[n] = temp;
			}else if(horse[n]==99) {	//도착
				dfs(cnt+1,score+board.get(horse[n]).score);
				horse[n] = temp;
			}else {
				board.get(horse[n]).isEmpty = false;
				dfs(cnt+1,score+board.get(horse[n]).score);
				board.get(horse[n]).isEmpty = true;
				horse[n] = temp;
			}
		}
	}
	
	static class Kan{
		int next;
		int score;
		boolean isEmpty;
		public Kan(int next,int score) {
			super();
			this.next = next;
			this.score = score;
			isEmpty = true;
		}
	}
	
}
