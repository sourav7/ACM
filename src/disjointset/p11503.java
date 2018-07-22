import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
/**
* Virtual Friends
*
* @author Sourav Debnath
*
*/
class Friend {
	String name;
	int rank;
	int networkSize;
	Friend parent;

	public Friend(String name) {
		this.name = name;
		this.rank = 0;
		this.networkSize = 1;
		this.parent = this;
	}

}

class DisjointSet {
	private Map<String, Friend> map = new TreeMap<>();
	private Friend friend = null;

	public Friend makeSet(String name) {
		if (map.containsKey(name))
			return map.get(name);
		this.friend = new Friend(name);
		map.put(name, this.friend);
		return this.friend;
	}

	public int union(Friend f1, Friend f2) {

		Friend parent1 = findSet(f1);
		Friend parent2 = findSet(f2);

		if (parent1 == parent2) {
			return parent1.networkSize;
		}

		int result = parent1.networkSize + parent2.networkSize;
		if (parent1.rank >= parent2.rank) {
			parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
			parent1.networkSize += parent2.networkSize;
			parent2.parent = parent1;
		} else {
			parent2.networkSize += parent1.networkSize;
			parent1.parent = parent2;
		}
		return result;
	}

	private Friend findSet(Friend f1) {
		Friend parent = f1.parent;
		if (f1 == parent) {
			return parent;
		}
		f1.parent = findSet(f1.parent);
		return f1.parent;
	}
}

public class Problem11503 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		DisjointSet2 dj = null;
		Friend f1 = null, f2 = null;

		int numberOfTestCase = Integer.parseInt(br.readLine());

		for (int testCase = 0; testCase < numberOfTestCase; testCase++) {
			int numberOfFriendship = Integer.parseInt(br.readLine());
			dj = new DisjointSet();

			while (numberOfFriendship-- > 0) {
				st = new StringTokenizer(br.readLine(), " ");

				f1 = dj.makeSet(st.nextToken());
				f2 = dj.makeSet(st.nextToken());

				System.out.println(dj.union(f1, f2));
			}

		}
	}
}
