package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s10059 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine()) ;
		for(int i=0;i<T;i++) {
			String answer = null;
			String input = br.readLine() ;
			int front = Integer.parseInt(input.substring(0,2));
			int back = Integer.parseInt(input.substring(2,input.length()));
			boolean[] div = {false, false};
			
			if(0<front && front<=12) {
				div[0] = true;
			}
			if(0<back && back<=12) {
				div[1] = true;
			}
			
			if(div[0] && div[1]) {
				answer = "AMBIGUOUS";
			}else if(!(div[0] || div[1])) {
				answer = "NA";
			}else if(div[0]==true && div[1]==false) {
				answer = "MMYY";
			}else if(div[0]==false && div[1]==true) {
				answer = "YYMM";
			}
			System.out.printf("#%d %s\n",i+1,answer);
		}
		
	}
}
