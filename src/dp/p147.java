import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/**
 * Dollers
 * 
 * @author Sourav Debnath
 *
 */
public class Problem147 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		String input = null;

		Dollers dollers = new Dollers();

		while ((input = br.readLine()) != null) {
			double amount = Double.parseDouble(input);
			int n = (int) ((amount * 10000) / 100);
			if (n == 0)
				break;
			sb.append(String.format("%6.2f%17d\n", amount, dollers.getNumberOfWays(n)));
		}
		pw.print(sb);
		pw.flush();

	}
}

class Dollers {
	private int[] coins = new int[] { 0, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };
	private final int MAX_LIMIT = 30015;
	private final int coinIndexLimit = coins.length;
	private long resultArray[];

	public Dollers() {

		this.resultArray = new long[MAX_LIMIT];
		this.generateResult();
	}

	public long getNumberOfWays(int amount) {
		return this.resultArray[amount];
	}

	private void generateResult() {

		resultArray[0] = 1;
		for (int i = 1; i < coinIndexLimit; i++) {
			int currentCoin = coins[i];
			int start = currentCoin;

			while (start < MAX_LIMIT) {
				resultArray[start] = resultArray[start] + resultArray[start - currentCoin];
				start += 5;
			}
		}
	}
}
