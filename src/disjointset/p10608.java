import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Friends
 * 
 * @author Sourav Debnath
 *
 */
public class Problem_10608 {

	private static int max = 0;
	private static Person[] map = new Person[30002];
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
			max = 1;
			for (int i = 1; i <= numberOfPerson; i++) {
                //default group length is 1
				makeSet(i, 1);
			}
			for (int i = 0; i < numberOfFriendShip; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				union(map[Integer.parseInt(st.nextToken())], map[Integer.parseInt(st.nextToken())]);
			}
			sb.append(max + "\n");

		}

		pw.print(sb);
		pw.flush();
		pw.close();
		br.close();
	}

	private static void makeSet(int i, int groupLen) {
		Person person = new Person(i, groupLen);
		map[i] = person;
	}

	private static void union(Person a, Person b) {
		Person parentA = findSet(a);
		Person parentB = findSet(b);
		if (parentA == parentB)
			return;
		if (parentA.rank >= parentB.rank) {
			parentA.rank = (parentA.rank == parentB.rank) ? parentA.rank + 1 : parentA.rank;
			parentB.parent = parentA;
			parentA.groupLen += parentB.groupLen;
			if (parentA.groupLen > max)
				max = parentA.groupLen;
		} else {
			parentA.parent = parentB;
			parentB.groupLen += parentA.groupLen;
			if (parentB.groupLen > max)
				max = parentB.groupLen;
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
	int groupLen;
	Person parent;

	public Person(int data, int groupLen) {
		this.data = data;
		this.groupLen = groupLen;
		this.parent = this;
		this.rank = 0;
	}
}
