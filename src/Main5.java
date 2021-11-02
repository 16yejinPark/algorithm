

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main5 {
    public static int[] solution(int[] fruitWeights, int k) {
    	Integer[] answer = new Integer[fruitWeights.length-k+1];
        
        for(int i=0;i<fruitWeights.length-k+1;i++) {
        	int max = 0;
        	for(int j=i;j<(i+k);j++) {
        		max = Math.max(fruitWeights[j], max);
            }
        	System.out.println(max);
        	answer[i] = max;
        }
        
        Arrays.sort(answer, Comparator.reverseOrder());
        int arrSize = answer.length;
        for(int i=0;i<answer.length-1;i++) {
        	if(answer[i]==answer[i+1]) {
        		answer[i]=0;
        		arrSize--;
        	}
        }		
        Arrays.sort(answer, Comparator.reverseOrder());
        int[] realAnswer = Arrays.stream(Arrays.copyOfRange(answer, 0, arrSize)).mapToInt(Integer::intValue).toArray();
        return realAnswer;
    }
	
    
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int[] f = {30,40,10,20,30,60};
		
		System.out.println(Arrays.toString(solution(f,3)));
		
	}

}
