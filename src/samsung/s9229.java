package samsung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class s9229 {
	public static int[] weight=null; 
	public static int N;
	public static int M;
	public static int MAX;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			weight = new int[N];
			MAX=-1;
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				weight[j] = Integer.parseInt(st.nextToken());
			}
			snackPackage(0,0,0);
			bw.write("#"+(i+1)+" "+MAX+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void snackPackage(int cnt,int start, int w_sum) {
		if(w_sum>M) {
			return;
		}
		if(cnt==2) {
			if(MAX<w_sum)
				MAX=w_sum;
			return;
		}
		for(int i=start;i<N;i++) {
			snackPackage(cnt+1,i+1, w_sum+weight[i]);
		}
		
	}

}
