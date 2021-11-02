package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class b3025_r {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int R = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		
		List<Integer[]> X_list = new ArrayList<>();
		List<Integer[]> O_list = new ArrayList<>();
		int[] bottom = new int[C];
		for(int i=0;i<C;i++) {
			bottom[i]=R-1;
		}
		//입력
		for (int r = 0; r < R; r++) {
			String[] temp2 = br.readLine().split("");
			for (int c = 0; c < C; c++) {
				if(temp2[c].charAt(0)=='X') {
					X_list.add(new Integer[] {r,c});
					if(r==R-1) {
						bottom[c]--;
					}
				}
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		for(int n=0;n<N;n++) {
			
			
		}

	}

}
