import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Enemy At The Gates
 * 
 * @author Sourav Debnath
 *
 */
public class Problem12428 {

	private static PrintWriter pw = new PrintWriter(System.out);

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder("");
		long numberOfNode = 0, numberOfEdge = 0;
		int tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			numberOfNode = Long.parseLong(st.nextToken());
			numberOfEdge = Long.parseLong(st.nextToken());

			if (numberOfNode > numberOfEdge)
				sb.append(numberOfEdge + "\n");
			else {

				long xtraEdge = numberOfEdge - (numberOfNode - 1);
				double root = (-1 + Math.sqrt(1 + 4 * 2 * xtraEdge)) / 2;
				long numberOfNodesToBeOmitted = (numberOfNode - 1) - (1 + (long) Math.ceil(root));
				sb.append(numberOfNodesToBeOmitted + "\n");
			}
		}
		pw.print(sb);
		pw.flush();
		pw.close();
	}
}
