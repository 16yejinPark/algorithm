package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//종이자르기
public class b2628 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> hor = new LinkedList<Integer>();
		List<Integer> ver = new LinkedList<Integer>();
		hor.add(0); 
		ver.add(0);
		hor.add(Integer.parseInt(st.nextToken()));
		ver.add(Integer.parseInt(st.nextToken()));
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int is_hor = Integer.parseInt(st.nextToken());
			int temp = Integer.parseInt(st.nextToken());
			if(is_hor==0) {
				ver.add(temp);
			}else if(is_hor==1) {
				hor.add(temp);
			}
		}
		Collections.sort(hor);
		Collections.sort(ver);
		int max_area = 0;
		for(int i =1;i<hor.size();i++) {
			for(int j =1;j<ver.size();j++) {
				int area = (hor.get(i)-hor.get(i-1)) * (ver.get(j)-ver.get(j-1));
				if(max_area < area) {
					max_area = area;
				}
			}
		}
		bw.write(max_area+" ");
		bw.flush();
		bw.close();
	}
}
