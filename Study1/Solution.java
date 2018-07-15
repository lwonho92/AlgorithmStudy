import java.util.Scanner;

class Solution {
	public static int CHAINS_COUNT = 4;
	public static int CHAIN_PART = 8;

	public static int CHAIN_LEFT = (CHAIN_PART / 4) * 3;
	public static int CHAIN_RIGHT = CHAIN_PART / 4;

	public static int CHAIN_INVISITED = 0;
	public static int CHAIN_VISITED = 1;

	public static int[][] chains = new int[CHAINS_COUNT][CHAIN_PART];
	public static int[] tops = new int[CHAINS_COUNT];
	public static int[] dest = new int[CHAINS_COUNT];

	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int K, chainNum, turnWay;

		for(int testCase = 1; testCase <= T; testCase++) {
			K = sc.nextInt();
			for(int i = 0; i < CHAINS_COUNT; i++) {
				for(int j = 0; j < CHAIN_PART; j++) {
					chains[i][j] = sc.nextInt();
				}
				tops[i] = 0;
			}

			for(int i = 0; i < K; i++) {
				chainNum = sc.nextInt();
				turnWay = sc.nextInt();

				play(chainNum - 1, turnWay, dest);
			}

			System.out.println("#" + testCase + " " + calcResult());
		}
	}

	public static void nChainTurn(int chainNum, int turnWay) {
		int op = CHAIN_PART - turnWay;
		tops[chainNum] = (tops[chainNum] + op) % CHAIN_PART;
	}

	public static void play(int chainNum, int turnWay, int[] dest) {
		if(dest[chainNum] == CHAIN_VISITED)
			return;
		dest[chainNum] = CHAIN_VISITED;

//		CHECK LEFT CHAIN
		if(chainNum - 1 >= 0 && chains[chainNum - 1][(tops[chainNum - 1] + CHAIN_RIGHT) % CHAIN_PART] !=
				chains[chainNum][(tops[chainNum] + CHAIN_LEFT) % CHAIN_PART]) {
			play(chainNum - 1, -1 * turnWay, dest);
		}
//		CHECK RIGHT CHAIN
		if(chainNum + 1 < CHAINS_COUNT && chains[chainNum][(tops[chainNum] + CHAIN_RIGHT) % CHAIN_PART] !=
				chains[chainNum + 1][(tops[chainNum + 1] + CHAIN_LEFT) % CHAIN_PART]) {
			play(chainNum + 1, -1 * turnWay, dest);
		}

		nChainTurn(chainNum, turnWay);
		dest[chainNum] = CHAIN_INVISITED;
	}

	public static int calcResult() {
		int result = 0;
		for(int i = 0; i < CHAINS_COUNT; i++) {
			result += chains[i][tops[i]] * Math.pow(2, i);
		}
		return result;
	}
}