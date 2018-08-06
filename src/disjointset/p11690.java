import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Money Matters... Not submitted yet
 * 
 * @author CSE
 *
 */
public class Problem_11690 {

	private static Person[] map = new Person[10002];
	private static HashMap<Integer, Person> setMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int numberOfPerson = Integer.parseInt(st.nextToken());
			int numberOfFriendShip = Integer.parseInt(st.nextToken());

			for (int i = 0; i < numberOfPerson; i++) {
				makeSet(i, Integer.parseInt(br.readLine()));
			}
			for (int i = 0; i < numberOfFriendShip; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				union(map[Integer.parseInt(st.nextToken())], map[Integer.parseInt(st.nextToken())]);
			}
			boolean isPossible = true;
			for (Map.Entry<Integer, Person> entry : setMap.entrySet()) {
				if (entry.getValue().money != 0) {
					isPossible = false;
					break;
				}
			}
			if (isPossible) {
				sb.append("POSSIBLE\n");
			} else
				sb.append("IMPOSSIBLE\n");
		}
		
		pw.print(sb);
		pw.flush();
		pw.close();
		br.close();
	}

	private static void makeSet(int i, int money) {
		Person person = new Person(i, money);
		map[i] = person;
		setMap.put(i, person);
	}

	private static void union(Person a, Person b) {
		Person parentA = findSet(a);
		Person parentB = findSet(b);
		if (parentA == parentB)
			return;
		if (parentA.rank >= parentB.rank) {
			parentA.rank = (parentA.rank == parentB.rank) ? parentA.rank + 1 : parentA.rank;
			parentB.parent = parentA;
			parentA.money += parentB.money;
			setMap.remove(parentB.data);
		} else {
			parentA.parent = parentB;
			parentB.money += parentA.money;
			setMap.remove(parentA.data);
		}
	}

	private static Person findSet(Person p) {
		Person parent = p.parent;
		if (p == parent)
			return p;
		p.parent = findSet(p.parent);
		return p.parent;
	}
}

class Person {
	int data;
	int rank;
	int money;
	Person parent;

	public Person(int data) {
		this.data = data;
		this.parent = this;
		this.rank = 0;
	}

	public Person(int i, int money) {
		this.data = i;
		this.money = money;
		this.parent = this;
		this.rank = 0;
	}
}
