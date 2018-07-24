import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Ingenuous Cubrency
 * 
 * @author Sourav Debnath
 *
 */
public class Problem11137 {

	public static void main(String[] args) throws IOException {

		Reader reader = new Reader();
		IngenuousCubrency cubrency = new IngenuousCubrency(21);

		while (true) {
			int n = reader.nextInteger();
			System.out.println(cubrency.getNumberOfWays(n));
		}
	}
}

class IngenuousCubrency {
	private int coins[];

	private final int MAX_LIMIT = 10002;
	private int coinIndexLimit = 0;
	private long resultArray[][];

	public IngenuousCubrency(int numberOfCoins) {
		this.coinIndexLimit = numberOfCoins + 1;
		resultArray = new long[coinIndexLimit][MAX_LIMIT];
		this.makeCoins();
		this.generateResult();
	}

	public long getNumberOfWays(int amount) {
		return this.resultArray[findIndex(amount)][amount];
	}

	private int findIndex(int n) {
		int coinIndex = 0;
		for (int i = 1; i < this.coinIndexLimit; i++) {
			if (coins[i] <= n)
				coinIndex++;
		}
		return coinIndex;
	}

	private void generateResult() {
		for (int i = 1; i < coinIndexLimit; i++) {
			resultArray[i][0] = 1;
			int currentCoin = coins[i];
			int j = 1;

			while (j < currentCoin) {
				resultArray[i][j] = resultArray[i - 1][j];
				j++;
			}
			while (j < MAX_LIMIT) {
				resultArray[i][j] = resultArray[i - 1][j] + resultArray[i][j - currentCoin];
				j++;
			}

		}

	}

	private void makeCoins() {
		coins = new int[this.coinIndexLimit];
		coins[0] = 0;
		for (int i = 1; i < this.coinIndexLimit; i++) {
			coins[i] = i * i * i;
		}
	}
}

final class Reader {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = new StringTokenizer("", " ");
	private String inputLine = "";

	public int nextInteger() throws IOException {
		if (st.hasMoreTokens())
			return Integer.parseInt(st.nextToken());

		inputLine = br.readLine();
		if (inputLine == null)
			System.exit(0);
		st = new StringTokenizer(inputLine, " ");

		return Integer.parseInt(st.nextToken());
	}
}
