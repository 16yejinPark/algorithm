package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

//22:40~12:20
public class b19236_sungun {

   static int maxSum = 0;
   static int sharkIdx = -1;
   static int[] dr = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
   static int[] dc = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

   static class Fish {
      int num;
      int r;
      int c;
      int d;
      int shark;

      public Fish(int num, int r, int c, int d, int shark) {
         this.num = num;
         this.r = r;
         this.c = c;
         this.d = d;
         this.shark = shark;
      }

      public void move(int[][] map, ArrayList<Fish> list) {
         int nr = r + dr[d];
         int nc = c + dc[d];

         if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || map[nr][nc] == -1) {
            chageDir();
            move(map, list);
         } else {
            if (map[nr][nc] == 0) {
               map[r][c] = 0;
               this.r = nr;
               this.c = nc;
               map[nr][nc] = this.num;
            } else {
               Fish tmpFish = list.get(map[nr][nc]);
               tmpFish.r = this.r;
               tmpFish.c = this.c;
               map[r][c] = tmpFish.num;
               this.r = nr;
               this.c = nc;
               map[nr][nc] = this.num;
            }
         }
      }

      private void chageDir() {
         if (d == 8)
            d = 1;
         else
            d++;
      }

   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = null;

      ArrayList<Fish> list = new ArrayList<>();
      list.add(new Fish(0, -1, -1, -1, -1));
      int[][] map = new int[4][4];

      for (int i = 0; i < 4; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 0; j < 4; j++) {
            int num = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Fish(num, i, j, d, 0));
            map[i][j] = num;
         }
      }

      map[0][0] = -1;
      maxSum = list.get(1).num;
      list.get(1).shark = 1;
      sharkIdx = maxSum;

      sort(list);
      getMaxSum(list, map, maxSum);

      System.out.println(maxSum);
   }

   private static void getMaxSum(ArrayList<Fish> list, int[][] map, int sum) {

      maxSum = Math.max(maxSum, sum);
      for (Fish fish : list) {
         if (fish.shark == 0 && fish.d > 0) {
            fish.move(map, list);
         }
      }

      Fish shark = list.get(sharkIdx);
      int nr = shark.r + dr[shark.d];
      int nc = shark.c + dc[shark.d];

      while (true) {
         if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
            return;
         } else {
            if (map[nr][nc] > 0) {
               ArrayList<Fish> newList = copyList(list);
               int[][] newMap = copyMap(map);
               int fishNum = map[nr][nc];
               newMap[nr][nc] = -1;
               newMap[shark.r][shark.c] = 0;

               Fish dead = newList.get(fishNum);
               Fish newShark = newList.get(sharkIdx);

               newShark.d = dead.d;
               newShark.r = nr;
               newShark.c = nc;
               dead.d = 0;
               getMaxSum(newList, newMap, sum + dead.num);

            }
            nr += dr[shark.d];
            nc += dc[shark.d];
         }

      }

   }

   private static void printMap(int[][] map) {
      System.out.println("@@@@@@@@@");
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
            System.out.print(map[i][j] + " ");
         }
         System.out.println();
      }

   }

   private static ArrayList<Fish> copyList(ArrayList<Fish> list) {

      ArrayList<Fish> newList = new ArrayList<>();

      for (Fish f : list) {
         Fish newFish = new Fish(f.num, f.r, f.c, f.d, f.shark);
         newList.add(newFish);
      }

      return newList;
   }

   private static int[][] copyMap(int[][] map) {

      int[][] tmpMap = new int[4][4];

      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++)
            tmpMap[i][j] = map[i][j];
      }

      return tmpMap;
   }

   private static void sort(ArrayList<Fish> list) {
      Collections.sort(list, new Comparator<Fish>() {

         @Override
         public int compare(Fish o1, Fish o2) {
            return o1.num - o2.num;
         }
      });

   }

}