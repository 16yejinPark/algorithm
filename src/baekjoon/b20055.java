package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//컨베이어 벨트 위의 로봇
//ArrayDeque쓸꺼면 size좀 따로 저장해놔라 진짜
public class b20055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] belt = new int[2*N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<2*N;i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[] isFull = new boolean[2*N];
		boolean keepGoing = true;
		ArrayDeque<Integer> robotList = new ArrayDeque<Integer>();
		int liftPoint = 0;
		int dropPoint = N-1;
		int level = 0;
		while(keepGoing) {
			level++;

			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			liftPoint--;
			if(liftPoint<0) {
				liftPoint = 2*N-1;
			}
			
			dropPoint--;
			if(dropPoint<0) {
				dropPoint = 2*N-1;
			}
			
			int size = robotList.size();
			for(int i=0;i<size;i++) {
				int loc = robotList.removeFirst();
				isFull[loc] = false;
				//내리는 위치라면 로봇을 내린다.
				if(loc!=dropPoint) {
					isFull[loc] = true;
					robotList.addLast(loc);
				}
			}
			
//			System.out.printf("lift = %d    \ndrop = %d\n",liftPoint,dropPoint);
//			print(belt,N*2);
//			for(int i=0;i<2*N;i++) {
//				if(isFull[i])System.out.print("T"+" ");
//				if(!isFull[i])System.out.print("F"+" ");
//			}System.out.println();
//			for(int i=0;i<robotList.size();i++) {
//				int loc = robotList.removeFirst();
//				System.out.print(loc+" ");
//				robotList.addLast(loc);
//			}System.out.println();System.out.println();
			
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			//     로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
			size = robotList.size();
			for(int i=0;i<size;i++) {
				int loc = robotList.removeFirst();
				int nLoc = (loc+1)%(2*N);
				isFull[loc] = false;
				
				if(belt[nLoc]>0&&!isFull[nLoc]) {
					belt[nLoc]--;
					loc = nLoc;
				}
				
				if(loc!=dropPoint) {
					isFull[loc] = true;
					robotList.addLast(loc);
				}
			}
				
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(belt[liftPoint]>0&&!isFull[liftPoint]) {
				belt[liftPoint]--;
				isFull[liftPoint] = true;
				robotList.addLast(liftPoint);
			}
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			int cnt = 0;
			for(int i=0;i<2*N;i++) {
				if(belt[i]==0) {
					cnt++;// 3. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
					if(cnt>=K) {
						keepGoing = false;
						break;
					}
				}
			}
		}
		

		System.out.println(level);
		
		
		
	}
	
	static void print(int[] belt, int size) {
		for(int i=0;i<size;i++) {
			System.out.print(belt[i] + " ");
		}System.out.println();
	}
}
