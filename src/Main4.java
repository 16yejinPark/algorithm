/*import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.*;

public class Main4 {

    public static String solution(String input){
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(input);
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        sb.append(M);sb.append(" ");sb.append(N);sb.append("\n");
        
        int day = 0;
        int show = 0;
        int bannedDay=0;
        boolean nega = false;
        
        while(st.hasMoreTokens()) {
        	String i = st.nextToken();
        	
        	if(bannedDay>0) {
    			sb.append("0\n");
    			continue;
    		}else if(nega) {
    			sb.append("0\n");
    			bannedDay = M;
    			continue;
    		}
        	
        	switch(i) {
        	case "SHOW": 
 
        		show++;
        		sb.append("1\n");
        		if(show>=N){
            		bannedDay = M+1;
        		//break;
        	case "NEGATIVE": 
        		nega = true;
        		bannedDay=M+1;
        		sb.append("0\n");
        		break;
        	case "NEXT": 
        		day++;
        		bannedDay--;
        		if(day==M) {
        			day=0;
        			show=0;
        			nega=false;
        		}else if(bannedDay==0) {
            		day = 0;
            		show = 0;
            		nega = false;
            	}
        		sb.append("-\n");
        		break;
        	case "EXIT": 
        		sb.append("BYE");
        		return sb.toString();
        	default :
        		sb.append("ERROR\n");
        		break;
        	}
        	
        }
         
        return sb.toString();
    }
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int[][] i = null;
		
		System.out.println((solution("1 3\n" + 
				"SHOW\n" + 
				"NEXT\n" + 
				"EXIT")));
		
	}

}
*/