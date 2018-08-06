import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
class Solution {
	public static int N, M, K, A, B;
	public static List<Customer> tk = new LinkedList<Customer>();
	public static List<Customer> stay = new LinkedList<Customer>();
	public static Desk[] recept = new Desk[9];
	public static Desk[] repair = new Desk[9];

	public static class Customer {
		private int number;
		private int arrive;
		private int receptNum;
		private int repairNum;

		public Customer(int number, int arrive) {
			this.number = number;
			this.setArrive(arrive);
			receptNum = 0;
			repairNum = 0;
		}

		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public int getArrive() {
			return arrive;
		}
		public void setArrive(int arrive) {
			this.arrive = arrive;
		}
		public int getReceptNum() {
			return receptNum;
		}
		public void setReceptNum(int receptNum) {
			this.receptNum = receptNum;
		}
		public int getRepairNum() {
			return repairNum;
		}
		public void setRepairNum(int repairNum) {
			this.repairNum = repairNum;
		}
	};

	public static class Desk {
		private int taskTime;
		private int currentState;
		private Customer customer;

		public Desk() {
			taskTime = 0;
			currentState = 0;
			customer = null;
		}

		public int getTaskTime() {
			return taskTime;
		}
		public void setTaskTime(int taskTime) {
			this.taskTime = taskTime;
		}
		public int getCurrentState() {
			return currentState;
		}
		public void setCurrentState(int currentState) {
			this.currentState = currentState;
		}
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
	}
	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
// 		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		int input;

		for(int testCase = 1; testCase <= T; testCase++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();

			A = sc.nextInt();
			B = sc.nextInt();

			for(int i = 0; i < N; i++) {
				input = sc.nextInt();
				recept[i] = new Desk();
				recept[i].setTaskTime(input);
			}
			for(int i = 0; i < M; i++) {
				input = sc.nextInt();
				repair[i] = new Desk();
				repair[i].setTaskTime(input);
			}
			for(int i = 0; i < K; i++) {
				input = sc.nextInt();
				tk.add(new Customer(i, input));
			}

			System.out.println("#" + testCase + " " + do_play());
		}
	}

	public static int do_play() {
		int curTime = tk.get(0).getArrive();
		int i, result = -1;

		do {
/////////////////////////////////////////////////////////////
//		recept
			for(i = 0; i < N; i++) {
				recept[i].currentState++;

				if(recept[i].getCustomer() == null) {
					if(!tk.isEmpty() && tk.get(0).getArrive() <= curTime) {
						recept[i].setCustomer(tk.remove(0));
						recept[i].getCustomer().setReceptNum(i);
						recept[i].setCurrentState(0);
					}
				} else if(recept[i].taskTime == recept[i].currentState) {
					stay.add(recept[i].getCustomer());
					if(!tk.isEmpty() && tk.get(0).getArrive() <= curTime) {
						recept[i].setCustomer(tk.remove(0));
						recept[i].getCustomer().setReceptNum(i);
						recept[i].setCurrentState(0);
					} else {
						recept[i].setCustomer(null);
					}
				}
			}
/////////////////////////////////////////////////////////////
//		repair
			for(i = 0; i < M; i++) {
				repair[i].currentState++;

				if(repair[i].getCustomer() == null) {
					if(!stay.isEmpty()) {
						repair[i].setCustomer(stay.remove(0));
						repair[i].getCustomer().setRepairNum(i);
						repair[i].setCurrentState(0);
					}
				} else if(repair[i].taskTime == repair[i].currentState) {
					if(repair[i].getCustomer().getReceptNum() == A - 1 && repair[i].getCustomer().getRepairNum() == B - 1) {
						result += repair[i].getCustomer().getNumber() + 1;
					}
					repair[i].setCustomer(null);

					if(!stay.isEmpty()) {
						repair[i].setCustomer(stay.remove(0));
						repair[i].getCustomer().setRepairNum(i);
						repair[i].setCurrentState(0);
					}
				}
			}

			curTime++;
		} while(!tk.isEmpty() || !isReceptEmpty() || !isRepairEmpty());

		if(result != -1)
			result += 1;

		return result;
	}

	public static boolean isReceptEmpty() {
		int i;
		for(i = 0; i < N; i++) {
			if(recept[i].getCustomer() != null)
				return false;
		}
		return true;
	}

	public static boolean isRepairEmpty() {
		int i;
		for(i = 0; i < M; i++) {
			if(repair[i].getCustomer() != null)
				return false;
		}
		return true;
	}
}