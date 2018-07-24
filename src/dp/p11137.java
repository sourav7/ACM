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

	private final int MAX_LIMIT = 10002;
	private int coinIndexLimit = 0;
	private long resultArray[];

	public IngenuousCubrency(int numberOfCoins) {
		this.coinIndexLimit = numberOfCoins + 1;
		this.resultArray = new long[MAX_LIMIT];
		this.generateResult();
	}

	public long getNumberOfWays(int amount) {
		return this.resultArray[amount];
	}

	private void generateResult() {
		resultArray[0] = 1;
		for (int i = 1; i < coinIndexLimit; i++) {
			int currentCoin = i*i*i;
			int start = currentCoin;

			while (start < MAX_LIMIT) {
				resultArray[start] = resultArray[start] + resultArray[start - currentCoin];
				start++;
			}
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
