import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
/**
 * Forests
 * 
 * @author Sourav Debnath
 *
 */
public class Problem10227 {
	private static SortedSet<Integer> adjList[] = new SortedSet[103];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		br.readLine();

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int numberOfPerson = Integer.parseInt(st.nextToken());
			
			for (int i = 1; i <= numberOfPerson; i++) {
				adjList[i] = new TreeSet<>();
			}

			String input = null;
			while ((input = br.readLine()) != null) {
				if (input.length() == 0)
					break;
				st = new StringTokenizer(input, " ");
				adjList[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}
			int result = 0;

			for (int i = 1; i <= numberOfPerson; i++) {
				SortedSet<Integer> currentSet = adjList[i];
				
				if (currentSet != null) {
					result++;
					for (int ij = i + 1; ij <= numberOfPerson; ij++) {
						if (adjList[ij] != null && adjList[ij].equals(currentSet)) {
							adjList[ij] = null;
						}
					}
				}
			}

			sb.append(result + "\n");
			if (tc != testCase)
				sb.append("\n");
		}
		pw.print(sb);
		pw.flush();
	}
}
