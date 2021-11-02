package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//트럭
public class b13335 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//트럭 수
		int W = Integer.parseInt(st.nextToken());	//다리의 길이 겸 다리위 최대 트럭 수
		int L = Integer.parseInt(st.nextToken());	//최대하중
		ArrayList<Truck> trucks = new ArrayList<Truck>();
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			trucks.add(new Truck(Integer.parseInt(st.nextToken())));
		}
		int w=0;
		int l=0;
		int time=0;
		ArrayList<Truck> onBridge = new ArrayList<Truck>();
		do{
			int out=-1;
			for(int i=0;i<onBridge.size();i++) {
				onBridge.get(i).loc++;
				if(onBridge.get(i).loc>W) {
					out=i;
				}
			}
			if(out!=-1) {
				Truck t = onBridge.remove(out);
				w--;
				l-=t.w;
			}
			if(!trucks.isEmpty()&&trucks.get(0).w+l<=L&&w+1<=W) {
				Truck t = trucks.remove(0);
				t.loc++;
				onBridge.add(t);
				w++;
				l+=t.w;
			}
			time++;
		}while(!onBridge.isEmpty());
		System.out.println(time);
	}
	public static class Truck{
		int w;
		int loc=0;
		Truck(int w){
			this.w = w;
		}	
	}
}
