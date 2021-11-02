import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.*;
import java.lang.Math;
public class Main {
	public static int solution(int n, int k) {
        int answer = 0;

        int len = 0;
        while(true) {
        	if(n>=Math.pow(k, len)) {
        		len++;
        	}else {
        		break;
        	}
        }

        int num=0;
        int whole=n;
        StringBuilder sb = new StringBuilder();
        while(whole!=0) {
        	len--;
        	num= (int) (whole/Math.pow(k, len));
        	whole -= Math.pow(k, len)*num;
        	sb.append(num);
        	num=0;

        }
        
        //tokenize
        String transformedNum = sb.toString();
        sb.setLength(0);
        List<String> numList = new ArrayList<>();

        for(int i=0;i<transformedNum.length();i++) {
        	if(transformedNum.charAt(i)!='0') {
        		sb.append(transformedNum.charAt(i));
        	}else if(transformedNum.charAt(i)=='0'&&sb.length()>0){
        		numList.add(sb.toString());
        		sb.setLength(0);
        	}
        }
        
        //마지막꺼 있으면 추가
        if(sb.length()>0) {
        	numList.add(sb.toString());
        }
        
        //소수인지 판별
        for(int i=0;i<numList.size();i++) {
        	int prime = Integer.parseInt(numList.get(i));
            boolean isPrime = true;
        	for(int j=2;j<prime;j++) {
        		if(prime%j==0) {
        			isPrime = false;
        			break;
        		}
        	}
        	if(prime!=1&&isPrime==true) {
        		answer++;
        	}

        }
 
        return answer;
    }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		System.out.println(solution(110011,10));

	}

}
