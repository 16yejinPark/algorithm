package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//A와 B
public class b12904 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();

		int result = 0;
		while(S.length()<=T.length()) {
			//System.out.println(S + " " + T);
			if(T.equals(S)) {
				result = 1;
				break;
			}
			if(T.charAt(T.length()-1)=='A') {
				T = T.substring(0, T.length()-1);
			}else if(T.charAt(T.length()-1)=='B') {
				T = T.substring(0, T.length()-1);
				StringBuilder st = new StringBuilder();
				for(int i=T.length()-1;i>=0;i--) {
					st.append(T.charAt(i));
				}
				T = st.toString();
			}
		}
		System.out.println(result);
	}
}
