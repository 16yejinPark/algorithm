package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class b1158 {

	public static void main(String[] args) throws IOException {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int gab = Integer.parseInt(st.nextToken());
			List<Integer> list = new LinkedList<Integer>();
			int start = 0;
			for(int i=1;i<=N;i++) {
				list.add(i);
			}
			
			bw.write("<");
			while(list.size()!=1) {
				start =  (start+gab-1)%list.size();
				bw.write(list.get(start)+", ");
				list.remove(start);
			}
			bw.write(list.get(0)+"");
			bw.write(">");
			bw.flush();
			bw.close();
	}

}
