package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//낚시왕
public class b17143_2try {
	static int R;
	static int C;
	static int M;
	static int dx[] = {0,-1,1,0,0};
	static int dy[] = {0,-0,0,1,-1};
	static int map[][];
	static int temp[][];
	static Shark sharks[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		sharks = new Shark[M+1];
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());	//상어의 위치
			int s = Integer.parseInt(st.nextToken());	//속력
			int d = Integer.parseInt(st.nextToken());	//방향
			int z = Integer.parseInt(st.nextToken());	//크기
			map[r][c] = i;
			sharks[i] = new Shark(i,r,c,s,d,z);
		}
		
		int fishingKing = 0;
		int sum = 0;
		while(true) {			
			//낚시왕이 오른쪽으로 한 칸 이동한다.
			fishingKing++;
			if(fishingKing==C+1) {
				break;
			}
			
			//낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
			for(int i=1;i<=R;i++) {
				if(map[i][fishingKing]!=0) {
					int no = map[i][fishingKing];
					sum += sharks[no].z;
					sharks[no].catched = true;
					map[i][fishingKing]=0;
					break;
				}
			}

			//상어가 이동한다.
			temp = new int[R+1][C+1];
			for(int i=1;i<=M;i++) {
				if(!sharks[i].catched) {
					sharks[i].move();
				}
			}
			for(int i=1;i<=R;i++) {
				for(int j=1;j<=C;j++) {
					map[i][j] = temp[i][j];
				}
			}
		}
		System.out.println(sum);
	}
	
	static class Shark{
		int no;
		int r;
		int c;
		int s;
		int d;
		int z;
		boolean catched = false;
		public Shark(int no, int r, int c, int s, int d, int z) {
			super();
			this.no = no;
			this.r = r;
			this.c = c;
			if(d==1||d==2) {
				s%=((R-1)*2);
			}else if(d==3||d==4) {
				s%=((C-1)*2);
			}
			this.s = s;
			this.d = d;
			this.z = z;
		}
		public void move() {
			for(int i=0;i<s;i++) {
				int nr = r + dx[d];
				int nc = c + dy[d];
				if(nr>0&&nr<=R&&nc>0&&nc<=C) {
					r = nr;
					c = nc;
				}else {
					switch(d) {
					case 1: d = 2; break;
					case 2: d = 1; break;
					case 3: d = 4; break;
					case 4: d = 3; break;
					}
					i--;
				}
			}
			
			if(temp[r][c]==0) {
				temp[r][c]=no;
			}else {
				if(sharks[temp[r][c]].z<z) {
					sharks[temp[r][c]].catched = true;
					temp[r][c]=no;
				}else {
					catched = true;
				}
			}
		}
	}
}
