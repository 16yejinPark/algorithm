package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//개미
public class b10158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		
		//짝수면 0에서 출발, 홀수면 벽에서 출발
		boolean moveX = (x+T)/W%2==0;
		boolean moveY = (y+T)/H%2==0;
		
		x=(x+T)%W;
		if(!moveX) {
			x=W-x;
		}
		
		y=(y+T)%H;
		if(!moveY) {
			y=H-y;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(x);sb.append(" ");sb.append(y);
		bw.write(x+" "+y);
		bw.flush();
		bw.close();
	}

}
