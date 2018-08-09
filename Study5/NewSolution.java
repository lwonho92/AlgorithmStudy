import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Solution {
	public static final int NO_VISITED = 0;
	public static final int VISITED = 1;
	public static final int[] dr = {-1, 0, 1, 0};
	public static final int[] dc = {0, 1, 0, -1};

	public static class Sector {
		public int R;
		public int C;
		public int type;
//		type 1 -> 15
//		type 2 -> 5
//		type 3 -> 10
//		type 4 -> 3
//		type 5 -> 6
//		type 6 -> 12
//		type 7 -> 9

		public int L;

		public Sector(int R, int C, int type, int L) {
			this.R = R;
			this.C = C;
			this.type = type;
			this.L = L;
		}
	}

	public static int map[][] = new int[50][50];
	public static int dest[][] = new int[50][50];

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
 		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));
 		Queue<Sector> que = new LinkedList<Sector>();
 		int N, M, R, C, L, result;
 		Sector item;

		int T = sc.nextInt();

		for(int testCase = 1; testCase <= T; testCase++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			result = 0;

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
					dest[i][j] = NO_VISITED;
				}
			}

			que.add(new Sector(R, C, typeToBinary(map[R][C]), L - 1));
			dest[R][C] = VISITED;
			do {
				item = que.remove();
				result++;

				if(item.L == 0)
					continue;

				for(int i = 0; i < 4; i++) {
					if((item.type & (int)(Math.pow(2, i))) != 0) {
						if(0 <= item.R + dr[i] && item.R + dr[i] < N && 0 <= item.C + dc[i] && item.C + dc[i] < M) {
							if((typeToBinary(map[item.R + dr[i]][item.C + dc[i]]) & (int)(Math.pow(2, (i + 2) % 4))) != 0) {
								if(dest[item.R + dr[i]][item.C + dc[i]] == NO_VISITED) {
									que.add(new Sector(item.R + dr[i], item.C + dc[i], typeToBinary(map[item.R + dr[i]][item.C + dc[i]]), item.L - 1));
									dest[item.R + dr[i]][item.C + dc[i]] = VISITED;
								}
							}
						}
					}
				}
			} while(!que.isEmpty());

			System.out.println("#" + testCase + " " + result);
		}
	}

	public static int typeToBinary(int type) {
		switch(type) {
		case 1:
			return 15;
		case 2:
			return 5;
		case 3:
			return 10;
		case 4:
			return 3;
		case 5:
			return 6;
		case 6:
			return 12;
		case 7:
			return 9;
		default:
			return 0;
		}
	}
}