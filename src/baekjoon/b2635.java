package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

//수 이어가기
public class b2635 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int first_n=Integer.parseInt(br.readLine());
		int max_size = 0;
		List<Integer> answer = null;
		for(int second_n=first_n;second_n>=first_n/2;second_n--) {
			List<Integer> list = new ArrayList<>();	
			list.add(first_n);
			list.add(second_n);
			int idx = 2;
			int gab = first_n - second_n;
			while(true) {
				gab = list.get(idx-2)-list.get(idx-1);
				if(gab<0)
					break;
				list.add(gab);
				idx++;
			}
			if(max_size<list.size()) {
				max_size=list.size();
				answer = new ArrayList<>(list);
			}
			
		}
		
		bw.write(max_size+"\n");
		for(int n : answer) {
			bw.write(n+" ");	
		}
		bw.flush();
		bw.close();
	}
}
