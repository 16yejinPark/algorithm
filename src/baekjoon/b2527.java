package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//직사각형
public class b2527 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int i=0;i<4;i++) {
			st = new StringTokenizer(br.readLine());
			
			int rec1_x=Integer.parseInt(st.nextToken());
			int rec1_y=Integer.parseInt(st.nextToken());
			int rec1_p=Integer.parseInt(st.nextToken());
			int rec1_q=Integer.parseInt(st.nextToken());
			int rec2_x=Integer.parseInt(st.nextToken());
			int rec2_y=Integer.parseInt(st.nextToken());
			int rec2_p=Integer.parseInt(st.nextToken());
			int rec2_q=Integer.parseInt(st.nextToken());
			
			char type='0';
			
			//점
			if((rec1_p==rec2_x&&rec1_q==rec2_y) || (rec2_p==rec1_x&&rec2_q==rec1_y) || (rec2_x==rec1_x&&rec2_q==rec1_y)||(rec2_p==rec1_x&&rec2_q==rec1_y))
				type='c';
			//선분
			//else if()
				type='b';
			//안겹침
			//else if()
				type='d';
			//직사각형
			//else
				type='a';
			System.out.println(type);
		}

	}

}
