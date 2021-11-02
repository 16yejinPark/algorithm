package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//원재의 메모리 복구
public class s1289 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			String[] str = br.readLine().split("");
			int cnt=0;
			if(str[0].equals("1")) {
				cnt++;
			}
			for(int j=1;j<str.length;j++) {
				if(!str[j-1].equals(str[j]))
					cnt++;
			}
			System.out.printf("#%d %d\n",i+1,cnt);
		}
	}
}
