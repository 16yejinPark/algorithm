package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//요리사
public class s4012 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0;i<T;i++) {
			
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			for(int f1=0;f1<N;f1++) {
				st = new StringTokenizer(br.readLine());
				for(int f2=0;f2<N;f2++) {
					map[f1][f2]=Integer.parseInt(st.nextToken());
				}
			}
			
			for(int a=0;a<N;a++) {
				for(int b=a+1;b<N;b++) {
					List<Integer> list = new ArrayList<>();
					for(int n=0;n<N;n++)
						list.add(n);
					int s1 = map[a][b]+map[b][a];
					//int s2 = 
				}
			}
			
			
		}

	}
	
	
	
}
