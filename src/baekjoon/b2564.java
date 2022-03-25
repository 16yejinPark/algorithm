package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//경비원
public class b2564 {

	public static int X = 0;
	public static int Y = 0;
	public static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//블록 사이즈와 상점 수 받기
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		int dongDir = 0;
		
		//상점,동근이 위치 받기(point[T][]가 동근이)
		int[][] point = new int[T+1][2];
		for(int i=0;i<T+1;i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			switch(dir) {
			case 1:
				point[i][0]= n;
				point[i][1]= Y;
				break;
			case 2:
				point[i][0]= n;
				point[i][1]= 0;
				break;
			case 3:
				point[i][0]= 0;
				point[i][1]= Y-n;
				break;
			case 4:
				point[i][0]= X;
				point[i][1]= Y-n;
				break;
			}
			if(i==T) {
				dongDir = dir;
			}
		}
		
		int sum = 0;
		for(int i=0;i<T;i++) {
			cnt=0;
			turnClockwise(dongDir,point[T][0],point[T][1],point[i][0],point[i][1]);
			int r = (cnt<(X+Y)?cnt:2*(X+Y)-cnt);
			sum+=r;
			//System.out.println(r);
		}	
		
		System.out.println(sum);
		
	}
	
	public static void turnClockwise(int dir,int x,int y,int tx,int ty) {

		if(x==tx&&y==ty) {
			return;
		}
		cnt++;
		switch(dir) {
			case 3:
				if(y+1==Y)dir=1;
				turnClockwise(dir,x,y+1,tx,ty);
				break;
			case 1:
				if(x+1==X)dir=4;
				turnClockwise(dir,x+1,y,tx,ty);
				break;
			case 4:
				if(y-1==0)dir=2;
				turnClockwise(dir,x,y-1,tx,ty);
				break;
			case 2:
				if(x-1==0)dir=3;
				turnClockwise(dir,x-1,y,tx,ty);
				break;
		}
	}
}
