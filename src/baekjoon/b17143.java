package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//낚시왕
public class b17143 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Shark[] shark = new Shark[M];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			shark[i]=new Shark(r,c,s,d,z);
		}
		int[][] map;
		int total=0;
		for(int i=1;i<=C;i++) {
			//catch Shark
			int catchIdx = -1;
			int minRow = Integer.MAX_VALUE;
			for(int m=0;m<M;m++) {
				if(shark[m]!=null&&shark[m].c==i&&shark[m].r<minRow) {
					catchIdx = m;
					minRow = shark[m].r;
				}
			}
			
			//success!
			if(catchIdx!=-1) {
				total += shark[catchIdx].z;
				shark[catchIdx]=null;
			}
			
			//move Shark
			map=new int[R+1][C+1];
			for(int r=0;r<=R;r++) {
				for(int c=0;c<=C;c++) {
					map[r][c]=-1;
				}
			}
			for(int m=0;m<M;m++) {
				if(shark[m]!=null) {
					int s=shark[m].s;
					if(shark[m].d <= 2) 
						s %= (R - 1) * 2;
					else 
						s %= (C - 1) * 2;
					for(int move=0;move<s;move++) {
						switch(shark[m].d) {
						case 1: 
							if(shark[m].r==1) {
								shark[m].d=2;
								shark[m].r++;
								break;
							}
							shark[m].r--;
							break;
						case 2: 
							if(shark[m].r==R) {
								shark[m].d=1;
								shark[m].r--;
								break;
							}
							shark[m].r++;
							break;
						case 3: 
							if(shark[m].c==C) {
								shark[m].d=4;
								shark[m].c--;
								break;
							}
							shark[m].c++;
							break;
						case 4: 
							if(shark[m].c==1) {
								shark[m].d=3;
								shark[m].c++;
								break;
							}
							shark[m].c--;
							break;	
						}
					}
					

					//check shark
					if(map[shark[m].r][shark[m].c]==-1) {
						map[shark[m].r][shark[m].c]=m;
					}else {
						if(shark[map[shark[m].r][shark[m].c]].z<shark[m].z) {
							shark[map[shark[m].r][shark[m].c]]=null;
							map[shark[m].r][shark[m].c]=m;
						}else {
							shark[m]=null;
						}
					}
				}
			}
		}
		System.out.println(total);
	}
	
	static class Shark{
		int r;
		int c;
		int s;
		int d;
		int z;
		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}
