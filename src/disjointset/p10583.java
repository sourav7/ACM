import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Ubiquitous Religions
 * 
 * @author Sourav Debnath
 *
 */
class Person {
    int data;
    int rank;
    Person parent;
    
    Person(int data){
        this.data = data;
        this.rank = 0;
        this.parent = this;
    }
}

public class Problem_10583 {

	private static Person[] map = new Person[50005];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		String input = null;

		for (int tc = 1; (input = br.readLine()) != null; tc++) {
			st = new StringTokenizer(input, " ");
			int numberOfPerson = Integer.parseInt(st.nextToken());
			int numberOfFriendShip = Integer.parseInt(st.nextToken());
			if (numberOfPerson == 0 && numberOfFriendShip == 0)
				break;

			for (int i = 0; i <= numberOfPerson; i++) {
				map[i] = new Person(i);
			}

			int result = numberOfPerson;
			for (int i = 0; i < numberOfFriendShip; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				if (union(map[Integer.parseInt(st.nextToken())], map[Integer.parseInt(st.nextToken())]))
					result--;

			}
			sb.append("Case " + tc + ": " + result + "\n");
		}

		pw.print(sb);
		pw.flush();
		pw.close();
		br.close();
	}

	private static boolean union(Person a, Person b) {
		Person parentA = findSet(a);
		Person parentB = findSet(b);

		if (parentA == parentB)
			return false;

		if (parentA.rank >= parentB.rank) {
			parentA.rank = (parentA.rank == parentB.rank) ? parentA.rank + 1 : parentA.rank;
			parentB.parent = parentA;

		} else {
			parentA.parent = parentB;
		}
		return true;
	}

	private static Person findSet(Person p) {
		Person parent = p.parent;
		if (p == parent)
			return p;
		p.parent = findSet(p.parent);
		return p.parent;
	}
}
