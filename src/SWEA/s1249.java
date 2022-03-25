package SWEA;

import java.util.*;
import java.io.*;
 
public class s1249 {
     
    private static int N;
    private static int[][] map;
    private static int[][] minTime;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T;
 
        T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            // input
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            minTime = new int[N][N];
             
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                 
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                    minTime[i][j] = Integer.MAX_VALUE;
                }
            }
             
            // solve
            bw.write("#" + tc + " " + getAns() + "\n");
        }
 
        bw.flush();
        bw.close();
    }
     
    private static int getAns() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { 0, 0 }); // 시작점
        minTime[0][0] = 0;
         
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
             
            for (int dir = 0; dir < 4; dir++) {
                int nextX = cur[0] + dx[dir];
                int nextY = cur[1] + dy[dir];
                 
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
                 
                if (minTime[cur[0]][cur[1]] + map[nextX][nextY] < minTime[nextX][nextY]) {
                    minTime[nextX][nextY] = minTime[cur[0]][cur[1]] + map[nextX][nextY];
                    queue.offer(new int[] { nextX, nextY });
                }
            }
        }
         
        return minTime[N - 1][N - 1];
    }
 
}