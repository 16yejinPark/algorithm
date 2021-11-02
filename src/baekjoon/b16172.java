package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//나는 친구가 적다
public class b16172 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		String keyword = br.readLine();
		for(int i=0;i<str.length;i++) {
			if(str[i]=='0'||str[i]=='1'||str[i]=='2'||str[i]=='3'||str[i]=='4'||
					str[i]=='5'||str[i]=='6'||str[i]=='7'||str[i]=='8'||str[i]=='9') {
				str[i] =' ';
			}
		}
		String newString = String.valueOf(str).replaceAll(" ","");
		System.out.println((newString.contains(keyword)?1:0));
	}
}
