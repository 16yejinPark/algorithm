package samsung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class b1233 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int T=1;T<=10;T++) {
			int N = Integer.parseInt(br.readLine());
			int result = 2;
			for(int i = 0;i<N;i++) {
				String nodeInfo[] = br.readLine().split(" ");
				if(result==0) {
					continue;
				}else {
					if(nodeInfo[1].equals("-")||nodeInfo[1].equals("+")||nodeInfo[1].equals("*")||nodeInfo[1].equals("/")) {
						if(nodeInfo.length!=4) {
							result = 0;
						}	
					}else if(nodeInfo.length==3) {
						result = 0;
					}else if(nodeInfo.length==2) {
						if(nodeInfo[1].equals("-")||nodeInfo[1].equals("+")||nodeInfo[1].equals("*")||nodeInfo[1].equals("/"))
							result = 0;	
					}
					if(i==(N-1))
						result=1;
				}
			}
			bw.write("#"+T+" "+result+"\n");
		}
		bw.flush();
		bw.close();
	}

}
