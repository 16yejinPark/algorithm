package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//참외밭
public class b2477 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int trueWhy = Integer.parseInt(br.readLine());
		int[] len = new int[6];
		
		//1, 2, 3, 4 >> 동, 서, 남, 북
		int x = 0;
		int y = 0;
		int x_idx = 0;
		int y_idx = 0;
		for(int i=0;i<6;i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			len[i] = Integer.parseInt(st.nextToken());
			
			if((dir==1||dir==2)&&x<len[i]) {
				x=len[i];
				x_idx = i;
			}else if((dir==3||dir==4)&&y<len[i]) {
				y=len[i];
				y_idx = i;
			}
		}
		
		int mini_x = 0;
		int mini_y = 0;
		if(Math.abs(x_idx-y_idx)==5) {
			mini_x = len[2];
			mini_y = len[3];
		}
		
		else if(x_idx<y_idx) {
			mini_x = len[(y_idx+2)%6];
			
			if(x_idx-2<0)
				mini_y = len[x_idx-2+6];
			else 
				mini_y = len[x_idx-2];
		}else {
			mini_y = len[(x_idx+2)%6];			
			if(y_idx-2<0)
				mini_x = len[y_idx-2+6];
			else 
				mini_x = len[y_idx-2];
		}
		
		System.out.println(((x*y)-(mini_x*mini_y))*trueWhy);
		
	}

}
