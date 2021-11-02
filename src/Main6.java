import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.*;

public class Main6 {
	static long cnt=0;
	public static long solution(int numOfStairs) {
		stair(0,numOfStairs);
        return cnt;
    }
	
	public static void stair(int num,int total_num) {
		if(num>total_num) {
        	return;
        }else if(num==total_num) {
        	cnt++;
        }else {
        	
        	stair(num+1,total_num);
        	stair(num+2,total_num);
        	stair(num+3,total_num);
        }
    }
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int[][] i = null;
		
		//System.out.println(Arrays.toString(solution(i)));
		
	}

}
