import java.io.FileInputStream;
import java.util.Scanner;
class Solution {
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
 		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		int N;

		int[] num = new int[12];
		int[] op = new int[11];
		int input, index, max, min, temp;

		for(int testCase = 1; testCase <= T; testCase++) {
			N = sc.nextInt();
			index = 0;

			for(int i = 0; i < 4; i++) {
				input = sc.nextInt();
				for(int j = 0; j < input; j++) {
					op[index++] = i;
				}
			}

			for(int i = 0; i < N; i++) {
				num[i] = sc.nextInt();
			}

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			do {
				temp = num[0];
				for(int i = 1; i < N; i++) {
					switch(op[i - 1]) {
					case 0:	// '+'
						temp += num[i];
						break;
					case 1:	// '-'
						temp -= num[i];
						break;
					case 2:	// '*'
						temp *= num[i];
						break;
					case 3:	// '/'
						temp /= num[i];
						break;
					}
				}

				if(max < temp)
					max = temp;
				if(min > temp)
					min = temp;
			} while(next_permutation(op, N - 1));
			System.out.println("#" + testCase + " " + (max - min));
		}
	}

	public static void printMatrix(int[] arr, int size) {
		for(int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static boolean next_permutation(int[] arr, int size) {
		int i = size - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;

		if(i <= 0)
			return false;

		int j = size - 1;
		while(i <= j && arr[i - 1] >= arr[j]) j--;

		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;

		j = size - 1;
		while(i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}

		return true;
	}

	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}