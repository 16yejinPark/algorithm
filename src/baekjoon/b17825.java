package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//주사위 윷놀이
//백트레킹을 좀 이상하게했음,, 처음에 move인덱스만큼 반복문 안에 넣었다가 처참히 야근함.
public class b17825 {
	static int max = 0;
	static int move[]  = new int[10];;
	static Horse horses[] = new Horse[5]; 
	static Node[] board = new Node[35];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i=0;i<10;i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		
		//idx, score, color, nextRed, nextBlue
		board[0] = new Node(0,0,'R',1,0);
		board[1] = new Node(1,2,'R',2,0);
		board[2] = new Node(2,4,'R',3,0);
		board[3] = new Node(3,6,'R',4,0);
		board[4] = new Node(4,8,'R',5,0);
		
		board[5] = new Node(5,10,'B',6,11);
		board[6] = new Node(6,12,'R',7,0);
		board[7] = new Node(7,14,'R',8,0);
		board[8] = new Node(8,16,'R',9,0);
		board[9] = new Node(9,18,'R',10,0);
		
		board[10] = new Node(10,20,'B',16,14);
		board[11] = new Node(11,13,'R',12,0);
		board[12] = new Node(12,16,'R',13,0);
		board[13] = new Node(13,19,'R',20,0);
		board[14] = new Node(14,22,'R',15,0);
		board[15] = new Node(15,24,'R',20,0);

		board[16] = new Node(16,22,'R',17,0);
		board[17] = new Node(17,24,'R',18,0);
		board[18] = new Node(18,26,'R',19,0);
		board[19] = new Node(19,28,'R',24,0);
		board[20] = new Node(20,25,'R',25,0);
		
		board[21] = new Node(21,26,'R',20,0);
		board[22] = new Node(22,27,'R',21,0);
		board[23] = new Node(23,28,'R',22,0);
		board[24] = new Node(24,30,'B',27,23);
		board[25] = new Node(25,30,'R',26,0);
		
		board[26] = new Node(26,35,'R',31,0);
		board[27] = new Node(27,32,'R',28,0);
		board[28] = new Node(28,34,'R',29,0);
		board[29] = new Node(29,36,'R',30,0);
		board[30] = new Node(30,38,'R',31,0);
		
		board[31] = new Node(31,40,'R',32,0);
		board[32] = new Node(32,0,'R',32,0);
	
		for(int i=1;i<=4;i++) {
			horses[i] = new Horse();
		}
		
		dfs(0,0);
		System.out.println(max);
	}
	
	static void dfs(int n,int sum) {
		if(n==10) {
			max = Math.max(max, sum);
			return;
		}
		
		
		for(int j=1;j<=4;j++) {
			int moveNum = 0;
			if(horses[j].arrive) {
				continue;
			}else {
				int nxtIdx = 0;
				if(board[horses[j].idx].color=='B') {
					nxtIdx=board[horses[j].idx].nxtBlueIdx;
				}else {
					nxtIdx=board[horses[j].idx].nxtRedIdx;
				}
				
				//J번 말을 이동
				int copyIdx = horses[j].idx;
				boolean arrive = false;
				for(int k=0;k<move[n];k++) {
					horses[j].idx = nxtIdx;
					if(horses[j].idx==32) {
						arrive = true;
						break;
					}
					nxtIdx = board[horses[j].idx].nxtRedIdx;
				}
				
				if(board[horses[j].idx].isEmpty||arrive) {
					if(arrive) horses[j].arrive = true;
					board[copyIdx].isEmpty = true;
					board[horses[j].idx].isEmpty = false;
					//다음 턴으로 가자
					dfs(n+1,sum+board[horses[j].idx].score);
					if(arrive) horses[j].arrive = false;
					board[horses[j].idx].isEmpty = true;
					board[copyIdx].isEmpty = false;
				}
				//백트레킹
				horses[j].idx = copyIdx;
			}
		}
		
	}
	
	static class Horse{
		int idx=0;
		boolean arrive = false;
	}
	
	static class Node{
		int idx;
		int score;
		char color;
		int nxtRedIdx;	//다음 빨간 노드
		int nxtBlueIdx;	//다음 파란 노드
		boolean isEmpty = true;
		public Node(int idx, int score, char color, int nxtRedIdx, int nxtBlueIdx) {
			super();
			this.idx = idx;
			this.score = score;
			this.color = color;
			this.nxtRedIdx = nxtRedIdx;
			this.nxtBlueIdx = nxtBlueIdx;
		}
	}	
}