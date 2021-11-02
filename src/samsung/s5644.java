package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//무선충전
public class s5644 {
	public static int[] dx = {0,-1,0,1,0};
	public static int[] dy = {0,0,1,0,-1};
	public static int A;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int t =1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());	
			int M = Integer.parseInt(st.nextToken());	//총이동시간
			A = Integer.parseInt(st.nextToken());	//BC의 개수
			
			StringTokenizer p1 = new StringTokenizer(br.readLine());	//사람1의 이동경로
			StringTokenizer p2 = new StringTokenizer(br.readLine());	//사람2의 이동경로
			
			//BC입력받기
			int[][] BC = new int[A+1][4];
			for(int a=1;a<=A;a++) {
				st = new StringTokenizer(br.readLine());	
				BC[a][1] = Integer.parseInt(st.nextToken());	//x좌표
				BC[a][0] = Integer.parseInt(st.nextToken());	//y좌표
				BC[a][2] = Integer.parseInt(st.nextToken());	//C(가용거리)
				BC[a][3] = Integer.parseInt(st.nextToken());	//P(power)
			}
			
			int px1=1; 
			int py1=1;
			int px2=10; 
			int py2=10;
			int sum=0;
			for(int m=0;m<M;m++) {
				
				int[] power = whichBestBC(px1,py1,px2,py2,BC);
				
				sum+=power[0];
				sum+=power[1];
				//System.out.printf("@%d %d %d\n",m,power[0],power[1]);
				
				int n1 = Integer.parseInt(p1.nextToken());
				int n2 = Integer.parseInt(p2.nextToken());
				px1+=dx[n1];
				py1+=dy[n1];
				px2+=dx[n2];
				py2+=dy[n2];
			}
		
			int[] lastPower = whichBestBC(px1,py1,px2,py2,BC);
			sum+=lastPower[0];
			sum+=lastPower[1];
			
			//System.out.printf("%d %d\n",lastPower[0],lastPower[1]);
			System.out.printf("#%d %d\n",t,sum);
			
			
		}

	}
	public static int distance(int x1,int y1,int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	} 
	
	public static int[] whichBC(int x, int y, int[][]BC) {
		int whichBc = 0;
		int secondBc = 0;
		int bc = 0;
		for(int a=1;a<=A;a++) {	
			if(distance(x,y,BC[a][0],BC[a][1])<=BC[a][2]){
				if(whichBc<BC[a][3]) {
					bc=a;
					if(secondBc<whichBc) {
						secondBc=whichBc;
					}
					whichBc=BC[a][3];
				}
				else if(secondBc<BC[a][3])
					secondBc=BC[a][3];
			}
		}
		return new int[] {whichBc, secondBc, bc};
	} 
	
	public static int[] whichBestBC(int x1, int y1, int x2, int y2, int[][]BC) {
		int[] BC_info_1 = whichBC(x1,y1,BC);
		int[] BC_info_2 = whichBC(x2,y2,BC);
		if(BC_info_1[2]==BC_info_2[2]) {
			if(BC_info_1[1]==0&&BC_info_2[1]==0) {
				BC_info_1[0] /= 2;
				BC_info_2[0] /= 2;
			}
			else if(BC_info_1[1]<=BC_info_2[1]) 
				BC_info_2[0] = BC_info_2[1];
			else if(BC_info_1[1]>BC_info_2[1]) 
				BC_info_1[0] = BC_info_1[1];
		}
		return new int[] {BC_info_1[0], BC_info_2[0]};
	}
	

}
