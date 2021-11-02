package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1859 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			int days = Integer.parseInt(br.readLine());
			int[] sale_price = new int[days];
			
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<days;j++) {
				sale_price[j] = Integer.parseInt(st.nextToken());
			}
			
			int qty=0;
			long price=0;

			outer:for(int j=days-1;j>=1;j--) {
				for(int k=j-1;k>=0;k--) {
					int margin = sale_price[j] - sale_price[k];
					if(margin > 0) {
						price+=margin;
						if(k==0)break outer;
					}else {
						j = k+1;
						break;
					}
				}
			}
			
			
			System.out.printf("#%d %d\n",i+1,price);
		}

	}

}
