package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시계
public class b8989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			PriorityQueue<Time> q = new PriorityQueue<>((o1,o2)->{
				if(o1.angle==o2.angle) {
					return Integer.compare(o1.hour*60+o1.min, o2.hour*60+o2.min);
				}
				return Double.compare(o1.angle, o2.angle);
			});
			for(int i=0;i<5;i++) {
				String time = st.nextToken();
				q.add(new Time(time));
			}
			q.remove();
			q.remove();
			System.out.println(q.remove().time);
		}
	}

	static class Time{
		String time;
		int hour;
		int min;
		double angle;
		public Time(String time) {
			super();
			this.time = time;
			String[] e = time.split(":");
			this.hour = Integer.parseInt(e[0]);
			this.min = Integer.parseInt(e[1]);
			this.angle = getAngle();
		}
		public double getAngle() {
			int hh=hour;
			if(hour>=12)
				hh = hour-12;
			double hourAngle = hh * 30 + min * 0.5;
			double minAngle = min * 6;
			double ang = Math.abs(hourAngle-minAngle);
			return Math.min(ang, Math.abs(360-ang));
		}
	}
}
