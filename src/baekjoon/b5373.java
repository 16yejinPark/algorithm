package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

//큐빙
public class b5373 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			Cube cube = new Cube();
			int N = Integer.parseInt(br.readLine());	//큐브를 돌린 횟수
			
			// U: 윗 면, D: 아랫 면, F: 앞 면, B: 뒷 면, L: 왼쪽 면, R: 오른쪽 면
			String[] method = br.readLine().split(" ");
			for(int n=0;n<N;n++) {
				cube.turn(method[n].charAt(0), method[n].charAt(1));
			}
			
			//윗면 색상 출력
			cube.printTopSide();
		}
		
	}
	static class Cube{
		private char[][][] cube = {
				{{'o','o','o'}
				,{'o','o','o'}
				,{'o','o','o'}},	//back(0)
				
				{{'w','w','w'}
				,{'w','w','w'}
				,{'w','w','w'}},	//top(1)
				
				{{'r','r','r'}
				,{'r','r','r'}
			    ,{'r','r','r'}},	//front(2)
				 
				 {{'y','y','y'}
				,{'y','y','y'}
				,{'y','y','y'}},	//down(3)
					
				{{'g','g','g'}
				,{'g','g','g'}
				,{'g','g','g'}},	//left(4)
					
				{{'b','b','b'}
				,{'b','b','b'}
				,{'b','b','b'}}	    //right(5)
		};

		private int[][] relation = {
				{Side.UP.ordinal(),Side.LEFT.ordinal(),Side.DOWN.ordinal(),Side.RIGHT.ordinal()},	// Back   //Top부터 시계방향
				{Side.BACK.ordinal(),Side.RIGHT.ordinal(),Side.FRONT.ordinal(),Side.LEFT.ordinal()},// Top
				{Side.UP.ordinal(),Side.RIGHT.ordinal(),Side.DOWN.ordinal(),Side.LEFT.ordinal()},	// Front
				{Side.FRONT.ordinal(),Side.RIGHT.ordinal(),Side.BACK.ordinal(),Side.LEFT.ordinal()},// Down
				{Side.UP.ordinal(),Side.FRONT.ordinal(),Side.DOWN.ordinal(),Side.BACK.ordinal()},	// Left
				{Side.UP.ordinal(),Side.BACK.ordinal(),Side.DOWN.ordinal(),Side.FRONT.ordinal()}	// Right
		};
		
		HashMap<Integer,HashMap<Integer,int[][]>> idxMap = new HashMap<>();
		
		public Cube() {
			super();
			int[][][] idxArr= {
					{{0,0},{0,1},{0,2}},
					{{0,2},{1,2},{2,2}},
					{{2,2},{2,1},{2,0}},
					{{2,0},{1,0},{0,0}}
			};
			
			for(int i=0;i<6;i++) {
				HashMap<Integer,int[][]> temp = new HashMap<>();
				for(int j=0;j<4;j++) {
					temp.put(relation[i][j], idxArr[j]);	//앞에꺼가 메인일때 뒤에 idxList들이 돌아감
				}
				idxMap.put(i, temp);
			}
		}
		
		private void turn(char meon, char dir) {
			ArrayDeque<int[]> indexDeque = new ArrayDeque<>();
			ArrayDeque<Character> colorDeque = new ArrayDeque<>();
			
			int main = 0;
			switch(meon) {
			case 'B': main = Side.BACK.ordinal(); break;
			case 'F': main = Side.FRONT.ordinal(); break;
			case 'U': main = Side.UP.ordinal(); break;
			case 'D': main = Side.DOWN.ordinal(); break;
			case 'R': main = Side.RIGHT.ordinal(); break;
			case 'L': main = Side.LEFT.ordinal(); break;
			}
			
			for(int i=0;i<4;i++) {
				int side = relation[main][i];
				int[][] idx = idxMap.get(side).get(main);
				if(dir=='+') {
					for(int j=0;j<3;j++) {
						indexDeque.addLast(new int[] {side,idx[j][0],idx[j][1]});
						colorDeque.addLast(cube[side][idx[j][0]][idx[j][1]]);
					}
				}else {
					for(int j=0;j<3;j++) {
						indexDeque.addFirst(new int[] {side,idx[j][0],idx[j][1]});
						colorDeque.addFirst(cube[side][idx[j][0]][idx[j][1]]);
					}
				}
			}
			
			for(int i=0;i<3;i++) {
				Character temp = colorDeque.removeLast();
				colorDeque.addFirst(temp);
			}
			
			int rep = colorDeque.size();
			for(int i=0;i<rep;i++) {
				int[] idx = indexDeque.removeFirst();
				Character color = colorDeque.removeFirst();
				cube[idx[0]][idx[1]][idx[2]] = color;
			}
			
			char[][] tempMain = new char[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(dir=='+') 
						tempMain[j][2-i] = cube[main][i][j];
					else if(dir=='-') 
						tempMain[2-j][i] = cube[main][i][j];
				}
			}
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					cube[main][i][j] = tempMain[i][j];
				}
			}
		}
		
		public void printTopSide() {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					sb.append(cube[Side.UP.ordinal()][i][j]);
				}
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
	}
	
	public enum Side{
		BACK, UP, FRONT, DOWN, LEFT, RIGHT
		
	}
}
