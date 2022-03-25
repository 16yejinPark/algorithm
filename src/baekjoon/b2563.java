package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//색종이
public class b2563 {
	public static boolean[][] paper = new boolean[100][100];
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int dx=0;dx<10;dx++) {
				for(int dy=0;dy<10;dy++) {
					paper[y+dy][x+dx]=true;
				}
			}
		}
		
		int size =0 ;
		for(int x=0;x<100;x++){
			for(int y=0;y<100;y++){
				if(paper[y][x])
					size++;
			}
		}
		
		System.out.println(size);
	}

}
