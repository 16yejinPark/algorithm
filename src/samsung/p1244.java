package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class p1244 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			String original = st.nextToken();
			int change_num = Integer.parseInt(st.nextToken());
			
			StringBuilder builder = null;
			ArrayList<String> nums = new ArrayList<String>();
			nums.add(original);
			for(int j=0;j<change_num;j++) {
				ArrayList<String> temp_nums = new ArrayList<String>();
				for(int m=0;m<nums.size();m++) {
					String num = nums.get(m);
					for(int k=0;k<original.length()-1;k++) {
						builder = new StringBuilder(num);
						char temp1 = builder.charAt(k);
						for(int l=k+1;l<original.length();l++) {
							builder = new StringBuilder(num);
							char temp2 = builder.charAt(l);
							builder.setCharAt(k, temp2);
							builder.setCharAt(l, temp1);
							temp_nums.add(builder.toString());
						}
					}
				}
				HashSet<String> temp = new HashSet<String>(temp_nums);
				nums = new ArrayList<String>(temp);
			}
			
			int max = 0;
			for(String str : nums) {
				if(max < Integer.parseInt(str)) {
					max = Integer.parseInt(str);
				}
			}
			System.out.printf("#%d %d\n",i+1,max);
		}
	}

}
