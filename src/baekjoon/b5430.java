package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//AC
public class b5430 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<String> s = new Stack<>();
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			String p = br.readLine();	//수행할 함수
			int n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			str = str.substring(1,str.length()-1);
			String[] arr = str.split(",");
			
			int start = 0;
			int end = n-1;
			boolean reverse = false;
			for(int i=0;i<p.length();i++) {		
				char f = p.charAt(i);
				if(f=='R') {
					if(reverse) {
						reverse = false;
					}else {
						reverse = true;
					}
				}else if(f=='D') {
					if(n==0) {
						n=-1;
						System.out.println("error");
						break;
					}else {
						if(reverse) {
							end--;
						}else {
							start++;
						}
						n--;
					}
				}
			}
			
			if(n>=0) {
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				if(reverse) {
					
					for(int i=end;i>=start;i--) {
						sb.append(arr[i]).append(",");
					}
					
					
				}else {
					for(int i=start;i<=end;i++) {
						sb.append(arr[i]).append(",");
					}
					
				}
				if(n!=0) {
					sb.deleteCharAt(sb.lastIndexOf(","));
				}
				sb.append("]");
				System.out.println(sb.toString());
			}
			
		}
	}

}
