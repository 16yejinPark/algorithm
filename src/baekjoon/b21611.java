package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//마법사 상어와 블리자드
//틀린 이유 : 덱에 마지막 원소에 대한 처리가 미흡했다.
public class b21611 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//가로,세로 크기
		int M = Integer.parseInt(st.nextToken());	//시행하는 횟수
		
		ArrayDeque<Integer> map = new ArrayDeque<>();
		int temp[][] = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				temp[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int rep = 2;
		int no = 1;
		int tx = N/2;
		int ty = N/2;
		int tdx[] = {1,0,-1,0};
		int tdy[] = {0,1,0,-1};
		
		for(int r=0;r<N/2;r++) {
			for(int i=0;i<4;i++) {
				for(int j=0;j<rep;j++) {
					tx += tdx[i];
					ty += tdy[i];
					if(temp[tx][ty]!=0) {
						map.addLast(temp[tx][ty]);
					}
					temp[tx][ty] = no++;
				}
			}
			rep+=2;
			tx--;
			ty--;
		}		
		
		
		
		int dx[] = {0,-1,1,0,0};
		int dy[] = {0,0,0,-1,1};
		int sx = N/2+1;
		int sy = N/2+1;
		int one = 0;
		int two = 0;
		int three = 0;
		for(int r=0;r<M;r++) {
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());	//방향
			int S = Integer.parseInt(st.nextToken());	//거리
			
			//상어가 마법으로 구슬을 파괴
			ArrayList<Integer> boomList = new ArrayList<Integer>();
			int nx = sx;
			int ny = sy;
			for(int i=0;i<S;i++) {
				nx += dx[D];
				ny += dy[D];
				boomList.add(temp[nx][ny]);
			}
			
			int size = map.size();
			for(int i=1;i<=size;i++) {
				int out = map.removeFirst();
				if(boomList.contains(i)) {
				}else {
					map.addLast(out);
				}
			}
			
			//구슬이 폭발하고 이동
			boolean changed = true;
			while(changed&&!map.isEmpty()) {
				changed = false;
				size = map.size();
				
				int before = map.peekFirst();
				int num = 0;
				for(int i=0;i<size;i++) {
					int out = map.removeFirst();
					if(out == before) {
						num++;
					}else {
						//폭발 처리 
						if(num>=4) {
							changed = true;
							switch(before) {
							case 1: one += num;
								break;
							case 2: two += num;
								break;
							case 3: three += num;
								break;
							}
							for(int j=0;j<num;j++) {
								map.removeLast();
							}
						}
						before = out;
						num = 1;
					}
					map.addLast(out);
					
					//마지막 원소에 대한 처리..
					if(i==size-1&&num>=4) {
						changed = true;
						switch(before) {
						case 1: one += num;
							break;
						case 2: two += num;
							break;
						case 3: three += num;
							break;
						}
						for(int j=0;j<num;j++) {
							map.removeLast();
						}
					}
					
					
				}				
			}
			
			
			//구슬이 변화
			if(!map.isEmpty()) {
				size = map.size();
				int before = map.peekFirst();
				int num = 0;
				int cnt = 0;
				boolean sizeOver = false;
				for(int i=0;i<size;i++) {
					int out = map.removeFirst();
					if(sizeOver) {
						continue;
					}else {
						if(out == before) {
							num++;
						}else {
							//변화 처리
							if(cnt+2<N*N) {
								map.addLast(num);
								map.addLast(before);
								cnt+=2;
							}else {
								sizeOver = true;	
							}
							before = out;
							num = 1;
						}
					}
					
					//마지막 원소에 대한 처리..
					if(i==size-1&&cnt+2<N*N) {
						map.addLast(num);
						map.addLast(before);
					}		
				}
			}	
		}
		System.out.println((1*one)+(2*two)+(3*three));
	}
	
	static void print(ArrayDeque<Integer> map,String memo) {
		int s= map.size();
		System.out.println(memo);
		for(int i=0;i<s;i++) {
			int out = map.removeFirst();
			System.out.print(out+" ");
			map.addLast(out);
		}System.out.println();
	}
	
}
