package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

//전화번호 목록
public class b5052 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<String> q = new PriorityQueue<String>((o1,o2)->(Integer.compare(o1.length(), o2.length())));
			ArrayList<String> numbers = new ArrayList<String>();
			for(int i=0;i<N;i++) {
				String number = br.readLine();
				numbers.add(number);
			}
			String answer = "YES";
			Collections.sort(numbers);
			for(int i=0;i<numbers.size()-1;i++) {
				if(numbers.get(i+1).startsWith(numbers.get(i))) {
					answer = "NO";
					break;
				}
			}
			System.out.println(answer);
		}
	}
}
