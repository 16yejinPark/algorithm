import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        return answer;
    }
    
    
    
	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int[][] i = null;
		
		//System.out.println(Arrays.toString(solution(i)));
		Date in = new SimpleDateFormat("hh:mm").parse("18:59");
		Date out = new SimpleDateFormat("hh:mm").parse("23:59");
		System.out.println((out.getTime()-in.getTime())/1000/60);
		
	}

}
