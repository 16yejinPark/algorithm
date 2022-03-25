package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//톱니바퀴
public class b14891 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<ArrayList<Integer>> gears =new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<=4;i++) {
			gears.add(new ArrayList<Integer>());
		}
	
		for(int i=1;i<=4;i++) {
			String[] temp = br.readLine().split("");
			for(int j=0;j<8;j++) {
				gears.get(i).add(Integer.parseInt(temp[j]));
			}
		}
		
		int K = Integer.parseInt(br.readLine());	//회전 횟수
		for(int i=0;i<K;i++) {
			String[] temp = br.readLine().split(" ");
			int whichGear = Integer.parseInt(temp[0]);
			int turnDir = Integer.parseInt(temp[1]);
			
			int[] move = {0,0,0,0,0};
			move[whichGear] = turnDir;
			
			//왼쪽 조사
			int left = whichGear-1;
			int crt = whichGear;
			int turn = turnDir;
			while(left>=1) {
				if(gears.get(crt).get(6)!=gears.get(left).get(2)) {
					move[left] = move[crt]*(-1);
					crt--;
					left--;
				}else {
					break;
				}
			}
			
			//오른쪽 조사
			crt = whichGear;
			turn = turnDir;
			int right = whichGear+1;
			while(right<=4) {
				if(gears.get(crt).get(2)!=gears.get(right).get(6)) {
					move[right] = move[crt]*(-1);
					crt++;
					right++;
				}else {
					break;
				}
			}
			
			//move
			for(int j=1;j<=4;j++) {
				if(move[j]==1) {	//시계방향				
					int t = gears.get(j).remove(7);
					gears.get(j).add(0, t);
				}else if(move[j]==-1){				//반시계방향
					int t = gears.get(j).remove(0);
					gears.get(j).add(t);
				}
			}
		}
		
		int score = 0;
		for(int i=1;i<=4;i++) {
			if(gears.get(i).get(0)==1) {
				score+=Math.pow(2, i-1);
			}
		}
		System.out.println(score);
	}
}
