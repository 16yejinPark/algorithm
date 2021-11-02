package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&problemLevel=3&contestProbId=AV134DPqAA8CFAYh&categoryId=AV134DPqAA8CFAYh&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=1&problemLevel=2%2C3&&&&&&&&&

public class p1206 {
	static int[][] map = null;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int[] answer= new int[10];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		//10번의 테스트케이스
		for(int i=0;i<10;i++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[255][N+4];
			
			//건물입력받기
			st = new StringTokenizer(br.readLine()," ");
			
			for(int j=0;j<N;j++) {
				int y = Integer.parseInt(st.nextToken());
				for(int k=0;k<y;k++) {
					map[254-k][j+2] = 1;
				}
			}
			
			for(int j=0;j<N;j++) {
				for(int k=0;k<255;k++) {
					if(map[254-k][j+2]==1) {
						//양옆 조망 체크
						if(map[254-k][j]!=1 && map[254-k][j+1]!=1 && map[254-k][j+3]!=1 && map[254-k][j+4]!=1)
							answer[i]++;
					}
					else {
						break;
					}
				}
				
			}
		}
		
		for(int i=0;i<10;i++) {
			System.out.printf("#%d %d\n",i+1,answer[i]);
		}
		
	}
	
	


}
