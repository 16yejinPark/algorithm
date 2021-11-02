import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.*;

public class Main3 {

    public static boolean solution(String amountText) {
        boolean answer = true;
        
        char[] checkNum = amountText.toCharArray();
       
        //0으로 시작? ,로 시작?
        if((checkNum.length!=1 && checkNum[0]=='0')||checkNum[0]==',') {
        	return false;
        }
        
        //,로 끝?
        if(checkNum[checkNum.length-1]==',') {
        	return false;
        }
        
        //0~9사이의 숫자와 구분자만 있는가?
        for(int i=0;i<checkNum.length;i++) {
        	if(checkNum[i]==','||(checkNum[i]>=48&&checkNum[i]<=57)) {
        		continue;
        	}else {
        		return false;
        	}
        }
        
        //반점이 있다면 3개마다 있는가?
        String[] checkSeparator = amountText.split(",");
        if(checkSeparator.length!=1) {
        	if(checkSeparator[0].split("").length>3)
        		return false;
        	for(int i=1;i<checkSeparator.length;i++) {
        		if(checkSeparator[i].split("").length!=3)
        			return false;
        	}
        }
        
        return answer;
    }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		String amountText = br.readLine();
		
		System.out.println(solution(amountText));
		
	}

}
