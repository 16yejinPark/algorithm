package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class b16926 {
	public static int[][] arr = null;
	public static int N_size=0 ;
	public static int M_size=0 ;
	public static int R ;
	public static int temp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M]; 
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		N_size = N;
		M_size = M;
		int x=0;
		int y=0;
		while(N_size>0&&M_size>0) {
			
			for(int i=0;i<R;i++) {
				temp = arr[x][y];
				turnCounterClockwise(1,x,y,x,y,true);
			}
			N_size=N_size-2;
			M_size=M_size-2;
			x++; y++;	
		}
		
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				bw.write(arr[n][m]+"");
				bw.write(" ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void turnCounterClockwise(int dir,int x,int y,int tx,int ty,boolean start) {
		int T;
		switch(dir) {
			case 1:
				T = arr[x+1][y];
				arr[x+1][y] = temp;
				temp = T;
				if((x+1)>=(N_size-1+tx))dir=2;
				turnCounterClockwise(dir,x+1,y,tx,ty,false);
				break;
			case 2:
				T = arr[x][y+1];
				arr[x][y+1] = temp;
				temp = T;
				if((y+1)>=(M_size-1+ty))dir=3;
				turnCounterClockwise(dir,x,y+1,tx,ty,false);
				break;
			case 3:
				T = arr[x-1][y];
				arr[x-1][y] = temp;
				temp = T;
				if(x-1==tx)dir=4;
				turnCounterClockwise(dir,x-1,y,tx,ty,false);
				break;
			case 4:
				T = arr[x][y-1];
				arr[x][y-1] = temp;
				temp = T;
				if(y-1==ty)
					return;
				else
					turnCounterClockwise(dir,x,y-1,tx,ty,false);
					
		}
	}
}
