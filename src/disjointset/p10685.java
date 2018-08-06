import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Nature
 * 
 * @author Sourav Debnath
 *
 */
class Person {
    int data,money,rank;
    Person parent;
    Person(int data, int money){
        this.data = data;
        this.money = money;
        this.parent = this;
        this.rank =0;
    }
}
public class Problem_10685 {
	static int max = 0;
	private static Map<String, Person> map = new TreeMap<>();

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
			max = 1;
			for (int i = 0; i < numberOfPerson; i++) {
				map.put(br.readLine().trim(), new Person(i, 1));
			}

			for (int i = 0; i < numberOfFriendShip; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				union(map.get(st.nextToken()), map.get(st.nextToken()));
			}
			sb.append(max + "\n");
			br.readLine();
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
			parentA.money += parentB.money;
			if (parentA.money > max)
				max = parentA.money;
		} else {
			parentA.parent = parentB;
			parentB.money += parentA.money;
			if (parentB.money > max)
				max = parentB.money;
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
