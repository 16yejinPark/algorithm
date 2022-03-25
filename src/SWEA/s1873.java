package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s1873 {
	private static int X;
	private static int Y;
	private static int tank_x;
	private static int tank_y;
	private static int bomb_x;
	private static int bomb_y;
	private static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			
			//map 생성
			map=new char[X][Y];
			for(int j=0;j<X;j++) {
				String mapLine = br.readLine();
				for(int k=0;k<Y;k++) {
					char temp = mapLine.charAt(k);
					map[j][k] = temp;
					if(temp=='<'||temp=='^'||temp=='v'||temp=='>') {
						tank_x=j;
						tank_y=k;
					}
				}
			}
			
			//move and shoot
			int input_num = Integer.parseInt(br.readLine());
			String command = br.readLine();
			for(int j=0;j<input_num;j++) {
				if(command.charAt(j)=='S') {
					shoot();
				}else {
					go(command.charAt(j));
				}
			}
			
			//output
			System.out.print("#"+(i+1)+" ");
			for(int j=0;j<X;j++) {
				for(int k=0;k<Y;k++) {
					System.out.print(map[j][k]);
				}
				System.out.println("");
			}
			
		}
	}
	
	public static void go(char key) {
		map[tank_x][tank_y]='.';
		
		if(key=='U' && isRange(tank_x-1, tank_y)) {
			if(map[tank_x-1][tank_y]=='.') {
				tank_x--;
			}
		}else if(key=='D' && isRange(tank_x+1, tank_y)) {
			
			if(map[tank_x+1][tank_y]=='.') {
				tank_x++;
			}

		}else if(key=='L' && isRange(tank_x, tank_y-1)) {
			if(map[tank_x][tank_y-1]=='.') {
				tank_y--;
			}

		}else if(key=='R' && isRange(tank_x, tank_y+1)) {
			if(map[tank_x][tank_y+1]=='.') {
				tank_y++;
			}
		}
		
		switch(key) {
			case 'U': map[tank_x][tank_y]='^';break;
			case 'D': map[tank_x][tank_y]='v';break;
			case 'L': map[tank_x][tank_y]='<';break;
			case 'R': map[tank_x][tank_y]='>'; break;
		}
	}
	
	public static void moveTank(char tank_dir) {
		switch(tank_dir) {
			case 'R': tank_y++; break;
			case 'U': tank_x--; break;
			case 'D': tank_x++; break;
			case 'L': tank_y--; break;
		}
	}
	
	public static void moveBomb(char tank_dir) {
		switch(tank_dir) {
			case '>': bomb_y++; break;
			case '^': bomb_x--; break;
			case 'v': bomb_x++; break;
			case '<': bomb_y--; break;
		}
	}
	
	public static void shoot() {
		bomb_x=tank_x;
		bomb_y=tank_y;
		
		while(isRange(bomb_x, bomb_y)) {
			if(map[bomb_x][bomb_y]=='*') {
				map[bomb_x][bomb_y]='.';
				return;
			}else if(map[bomb_x][bomb_y]=='#') {
				return;
			}
			moveBomb(map[tank_x][tank_y]);
		}
	}
	
	public static boolean isRange(int x, int y) {
		if(0<=x && X>x && 0<=y && Y>y )
			return true;
		else
			return false;
	}
	
}
