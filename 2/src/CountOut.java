import java.util.*;

public class CountOut {

	public static List<Integer> play(int n, int k) {
		Queue<Integer> remainingPlayers = new LinkedList<>();
		LinkedList<Integer> output = new LinkedList<>();

		for(int i=0; i<n; i++){
			remainingPlayers.add(i);
		}

		while(remainingPlayers.size()>0){
			for(int i=0; i<k-1; i++){
				remainingPlayers.add(remainingPlayers.remove());
			}
			output.add(remainingPlayers.remove());
		}
		return output;

	}

	// The runtime is O(nk)
	public static Integer findWinner(int n, int k) {
		return play(n,k).get(n-1);
	}

	// The runtime is O(n) because it makes n recursive calls
	public static Integer findWinnerRec(int n, int k) {
		if(n==1)
			return 0;
		return (findWinnerRec(n-1, k)+k)%n;
	}

	public static void main(String[] args) {
		System.out.println(play(9,4));
		System.out.println(findWinner(9,4));
		System.out.println(findWinnerRec(9,4));
	}
}
