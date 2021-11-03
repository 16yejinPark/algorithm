package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b23290_sunggun {

   static class Fish {
      int r, c, d;

      public Fish(int r, int c, int d) {
         super();
         this.r = r;
         this.c = c;
         this.d = d;
      }

      public void move() {
         fishCnt[r][c]--;
         int nextD = d;

         for (int i = 0; i < 8; i++) {
            int nextR = r;
            int nextC = c;
            nextR += dr[nextD];
            nextC += dc[nextD];

            if (nextR >= 0 && nextR < 4 && nextC >= 0 && nextC < 4 && map[nextR][nextC] == 0
                  && smell[nextR][nextC] == 0) {

               this.r = nextR;
               this.c = nextC;
               this.d = nextD;
               break;
            }
            nextD--;
            if (nextD == -1) {
               nextD = 7;
            }
         }
         fishCnt[r][c]++;
      }

   }

   static class Shark {
      int r, c;

      public Shark(int r, int c) {
         super();
         this.r = r;
         this.c = c;
      }

      public void move(int[] dir) {

         map[r][c] = 0;
         for (int i = 0; i < 4; i++)
            Arrays.fill(passed[i], false);

         int nextR = r;
         int nextC = c;

         for (int i = 0; i < 3; i++) {
            nextR += dr[dir[i]];
            nextC += dc[dir[i]];

            passed[nextR][nextC] = true;
            if (fishCnt[nextR][nextC] > 0)
               smell[nextR][nextC] = 3;
            fishCnt[nextR][nextC] = 0;
         }
         this.r = nextR;
         this.c = nextC;
         map[r][c] = 9;

      }

   }

   static int[][] map;
   static int[][] smell;
   static boolean[][] passed;
   static int[][] fishCnt;
   static boolean[][] visited;
   static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
   static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
   static int[] selectedDir = new int[3];
   static ArrayDeque<Fish> fish;
   static ArrayDeque<Fish> copiedFish;
   static PriorityQueue<int[]> sharkDir;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      int M = Integer.parseInt(st.nextToken());
      int S = Integer.parseInt(st.nextToken());

      map = new int[4][4];
      fishCnt = new int[4][4];
      passed = new boolean[4][4];
      visited = new boolean[4][4];
      smell = new int[4][4];
      fish = new ArrayDeque<>();
      copiedFish = new ArrayDeque<>();

      HashMap<Integer, Integer> hashMap = new HashMap<>();
      // 상좌하우 2 0 6 4
      hashMap.put(2, 1);
      hashMap.put(0, 2);
      hashMap.put(6, 3);
      hashMap.put(4, 4);

      for (int i = 0; i < M; i++) {
         st = new StringTokenizer(br.readLine(), " ");
         int r = Integer.parseInt(st.nextToken()) - 1;
         int c = Integer.parseInt(st.nextToken()) - 1;
         int d = Integer.parseInt(st.nextToken()) - 1;
         fish.add(new Fish(r, c, d));
         fishCnt[r][c]++;
      }

      sharkDir = new PriorityQueue<>(new Comparator<int[]>() {
         @Override
         public int compare(int[] o1, int[] o2) {
            if (o1[3] != o2[3])
               return o2[3] - o1[3];
            else if (o1[0] != o2[0])
               return hashMap.get(o1[0]) - hashMap.get(o2[0]);
            else if (o1[1] != o2[1])
               return hashMap.get(o1[1]) - hashMap.get(o2[1]);
            else
               return hashMap.get(o1[2]) - hashMap.get(o2[2]);
         }
      });

      st = new StringTokenizer(br.readLine());
      int sharkR = Integer.parseInt(st.nextToken()) - 1;
      int sharkC = Integer.parseInt(st.nextToken()) - 1;
      map[sharkR][sharkC] = 9;

      Shark shark = new Shark(sharkR, sharkC);
      int s=1;
      while (S-- > 0) {
         // 1. 물고기 복사,2. 물고기 이동

         for (Fish f : fish) {
            copiedFish.add(new Fish(f.r, f.c, f.d));
            f.move();
         }
         // 3. 상어이동
         sharkDir.clear();
         dfs(0, 0, shark.r, shark.c);
         if(s==6||s==7||s==8) {
            printMap(fishCnt);
            System.out.println(Arrays.toString(sharkDir.peek()));
         }
         shark.move(sharkDir.poll());
         if(s==6||s==7||s==8) {
            System.out.println((shark.r)+" "+(shark.c));
         }
         int size = fish.size();

         while (size-- > 0) {
            Fish f = fish.poll();
            if (passed[f.r][f.c] == true)
               continue;
            else
               fish.add(f);
         }
//         System.out.println(shark.r+" "+shark.c);

         // 4. 냄새 사라짐

         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
               if (smell[i][j] > 0)
                  smell[i][j]--;
            }
         }

         // 5. 복사된 물고기 추가
         while (!copiedFish.isEmpty()) {
            Fish f = copiedFish.poll();
            fishCnt[f.r][f.c]++;
            fish.add(f);
         }
         s++;
      }

      System.out.println(fish.size());

   }

   private static void printMap(int[][] fishCnt2) {
      for(int i=0;i<4;i++) {
         for(int j=0;j<4;j++) {
            System.out.print(fishCnt2[i][j]+" ");
         }
         System.out.println();
      }
      System.out.println("@@@@@@@@@@@@@@@@@@@");
   }

   private static void dfs(int d, int cnt, int r, int c) {
      if (d == 3) {

         int[] tmp = new int[4];
         for (int i = 0; i < 3; i++) {
            tmp[i] = selectedDir[i];

         }
         tmp[3] = cnt;
         sharkDir.add(tmp);

         return;
      }

      for (int i = 0; i < 8; i += 2) {
         int sr = r + dr[i];
         int sc = c + dc[i];
         if (sr >= 0 && sr < 4 && sc >= 0 && sc < 4) {
            selectedDir[d] = i;

            if (visited[sr][sc]) {
               dfs(d + 1, cnt, sr, sc);
            } else {
               visited[sr][sc] = true;
               dfs(d + 1, cnt + fishCnt[sr][sc], sr, sc);
               visited[sr][sc] = false;
            }

         }

      }
   }

}