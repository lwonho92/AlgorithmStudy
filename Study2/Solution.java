import java.io.FileInputStream;
import java.util.Scanner;
class Solution {
	public static final int NUM_PRICE = 4;
	public static final int NUM_MONTH = 12;

	public static int[] price = new int[4];
	public static int[] use = new int[NUM_MONTH + 1];
	public static int[] cost = new int[NUM_MONTH + 1];

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
 		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();

		for(int testCase = 1; testCase <= T; testCase++) {
			for(int i = 0; i < NUM_PRICE; i++) {
				price[i] = sc.nextInt();
			}
			for(int i = 1; i <= NUM_MONTH; i++) {
				use[i] = sc.nextInt();
				cost[i] = 0;
			}
			calcMonthCost();

			System.out.println("#" + testCase + " " + cost[NUM_MONTH]);
		}
	}

	public static void calcMonthCost() {
		for(int month = 1; month <= NUM_MONTH; month++) {
			cost[month] = cost[month - 1] + use[month] * price[0];

			if(month - 1 >= 0 && cost[month] > cost[month - 1] + price[1])
				cost[month] = cost[month - 1] + price[1];
			if(month - 3 >= 0 && cost[month] > cost[month - 3] + price[2])
				cost[month] = cost[month - 3] + price[2];
			if(month - 12 >= 0 && cost[month] > cost[month - 12] + price[3])
				cost[month] = cost[month - 12] + price[3];
		}
	}
}