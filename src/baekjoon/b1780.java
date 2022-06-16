package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 종이의 개수
public class b1780 {
	static int[][] paper;
	static int a =0;
	static int b =0;
	static int c =0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int X = Integer.parseInt(br.readLine());
		paper = new int[X][X];
		
		for(int i=0;i<X;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<X;j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		check(0,0,X);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}
	
	public static void check(int sx,int sy, int s) {
		if(s==1) {
			add(paper[sx][sy]);
			return;
		}else {
			if(isOneType(sx,sy,s)) {
				add(paper[sx][sy]);
			}else {
				int ns = s/3;
				for(int i=sx;i<sx+s;i+=ns) {
					for(int j=sy;j<sy+s;j+=ns) {
						check(i,j,ns);
					}
				}
			}
		}
	}
	
	
	public static boolean isOneType(int sx,int sy, int s) {
		int cmp = paper[sx][sy];
		for(int i=sx;i<sx+s;i++) {
			for(int j=sy;j<sy+s;j++) {
				if(cmp != paper[i][j])
					return false;
			}
		}
		return true;
	}
	
	public static void add(int no) {
		if(no==-1)a++;
		else if(no==0)b++;
		else if(no==1)c++;
		return;
	}
}
