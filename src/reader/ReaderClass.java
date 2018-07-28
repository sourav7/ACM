import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReaderClass {
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

	public double nextDouble() throws IOException {
		if (st.hasMoreTokens())
			return Double.parseDouble(st.nextToken());

		inputLine = br.readLine();
		if (inputLine == null)
			System.exit(0);
		st = new StringTokenizer(inputLine, " ");

		return Double.parseDouble(st.nextToken());
	}
}
