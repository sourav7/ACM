import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Waking Up Brain
 * 
 * @author Sourav Debnath
 *
 */

public class Problem_11690 {
	private static Map<Character, BrainArea> map = new TreeMap<>();
	private static Map<Character, ArrayList<Character>> adjList = new TreeMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		while ((input = br.readLine()) != null) {
			map.clear();
			adjList.clear();
			
			int numberOfSlept = Integer.parseInt(input);
			int m = Integer.parseInt(br.readLine());

			input = br.readLine();
			map.put(input.charAt(0), new BrainArea(input.charAt(0), 3));
			map.put(input.charAt(1), new BrainArea(input.charAt(1), 3));
			map.put(input.charAt(2), new BrainArea(input.charAt(2), 3));

			for (int i = 0; i < m; i++) {
				input = br.readLine();
				if (!adjList.containsKey(input.charAt(0)))
					adjList.put(input.charAt(0), new ArrayList<>());
				if (!adjList.containsKey(input.charAt(1)))
					adjList.put(input.charAt(1), new ArrayList<>());
				adjList.get(input.charAt(0)).add(input.charAt(1));
				adjList.get(input.charAt(1)).add(input.charAt(0));
			}

			bfs(numberOfSlept);
			br.readLine();
		}
	}

	private static void bfs(int numOfChar) {
		if (numOfChar == 3) {
			System.out.println("WAKE UP IN, 0, YEARS");
			return;
		}

		Queue<BrainArea> q = new LinkedList<>();
		for (Map.Entry<Character, BrainArea> e : map.entrySet()) {
			q.add(e.getValue());
		}

		int result = 0;

		while (!q.isEmpty()) {
			BrainArea current = q.remove();

			ArrayList<Character> currentList = adjList.get(current.areaNo);
			if (currentList == null) {
				System.out.println("THIS BRAIN NEVER WAKES UP");
				return;
			}

			for (Character ch : currentList) {
				if (!map.containsKey(ch))
					map.put(ch, new BrainArea(ch, 0));
				BrainArea child = map.get(ch);

				if (child.activeConnection < 3) {
					child.year = Math.max(child.year, current.year + 1);

					child.activeConnection += 1;
					if (child.activeConnection == 3) {
						q.add(child);
						result = Math.max(result, child.year);
					}
				}
			}
		}
		
		for(Map.Entry<Character, BrainArea> eee: map.entrySet()) {
			if(eee.getValue().activeConnection <3)
			{
				System.out.println("THIS BRAIN NEVER WAKES UP");
				return;
			}
		}
		System.out.println("WAKE UP IN, " + result + ", YEARS");
	}
}

class BrainArea {
	int activeConnection;
	char areaNo;
	int year;

	public BrainArea(char areaNo, int activeConnection) {
		this.areaNo = areaNo;
		this.activeConnection = activeConnection;
		this.year = 0;
	}

	@Override
	public String toString() {
		return "BrainArea [activeConnection=" + activeConnection + ", areaNo=" + areaNo + "]";
	}

}
