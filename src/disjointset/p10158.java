import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/**
 * WAR
 * 
 * @author Sourav Debnath
 *
 */
class Person {
	int data;
	int rank;
	Person parent;

	public Person(int data) {
		this.data = data;
		this.parent = this;
		this.rank = 0;
	}
}

public class Problem10158 {

	private static Person[] enemies = new Person[10001];
	private static Person[] friends = new Person[10001];
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			makeSet(i);
		}

		String input;
		StringTokenizer st = null;
		while ((input = br.readLine()) != null) {
			if (input.compareTo("0 0 0") == 0)
				break;
			st = new StringTokenizer(input, " ");
			int c = Integer.parseInt(st.nextToken());
			Person a = friends[Integer.parseInt(st.nextToken())];
			Person b = friends[Integer.parseInt(st.nextToken())];

			switch (c) {
			case 1:
				makeFriend(a, b);
				break;
			case 2:
				makeEnemy(a, b);
				break;
			case 3:
				if (areFriends(a, b))
					sb.append("1\n");
				else
					sb.append("0\n");
				break;
			case 4:
				if (areEnemy(a, b))
					sb.append("1\n");
				else
					sb.append("0\n");
				break;
			default:
				break;
			}
		}
		pw.print(sb);
		pw.flush();
		br.close();
	}

	private static boolean areEnemy(Person a, Person b) {

		Person friendOfA = findSet(a);
		Person enemyOfB = findSet(enemies[b.data]);
		if (friendOfA == enemyOfB)
			return true;
		// similarly
		if (findSet(b) == findSet(enemies[a.data]))
			return true;
		return false;
	}

	private static void makeEnemy(Person a, Person b) {
		if (areFriends(a, b)) {
			sb.append("-1\n");
			return;
		}
		union(a, enemies[b.data]);
		union(b, enemies[a.data]);

	}

	private static void union(Person a, Person b) {
		Person parentA = findSet(a);
		Person parentB = findSet(b);
		if (parentA == parentB)
			return;
		if (parentA.rank >= parentB.rank) {
			parentA.rank = (parentA.rank == parentB.rank) ? parentA.rank + 1 : parentA.rank;
			parentB.parent = parentA;
		} else {
			parentA.parent = parentB;
		}

	}

	private static boolean areFriends(Person a, Person b) {
		if (findSet(a) == findSet(b))
			return true;
		return false;
	}

	private static Person findSet(Person p) {
		Person parent = p.parent;
		if (p == parent)
			return p;
		p.parent = findSet(p.parent);
		return p.parent;
	}

	private static void makeFriend(Person a, Person b) {
		if (areEnemy(a, b)) {
			sb.append("-1\n");
			return;
		}
		union(a, b);
		union(enemies[a.data], enemies[b.data]);
	}

	private static void makeSet(int data) {
		friends[data] = new Person(data);
		enemies[data] = new Person(data);
	}

}
