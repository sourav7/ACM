import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/**
 * Network Connections
 * 
 * @author Sourav Debnath
 *
 */
class Computer {
	int data;
	int rank;
	Computer parent;

	public Computer(int data) {
		this.data = data;
		this.parent = this;
		this.rank = 0;
	}
}

class DisJoint {
	private Computer[] map;

	public DisJoint(int n) {
		map = new Computer[n + 1];
		for (int i = 0; i <= n; i++) {
			makeSet(i);
		}
	}

	public boolean query(int a, int b) {
		if (findSet(map[a]) == findSet(map[b]))
			return true;
		return false;
	}

	public void createConnection(int a, int b) {
		Computer aa = map[a];
		Computer bb = map[b];

		Computer parent1 = findSet(aa);
		Computer parent2 = findSet(bb);
		if (parent1 == parent2)
			return;
		if (parent1.rank >= parent2.rank) {
			parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
			parent2.parent = parent1;
		} else
			parent1.parent = parent2;
	}

	private Computer findSet(Computer pc) {
		if (pc.parent == pc)
			return pc;
		pc.parent = findSet(pc.parent);
		return pc.parent;
	}

	private void makeSet(int i) {
		map[i] = new Computer(i);
	}
}

public class Problem793 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		String input = null;
		StringTokenizer st = null;
		br.readLine();
		while (tc-- > 0) {

			int computers = Integer.parseInt(br.readLine());
			DisJoint dj = new DisJoint(computers);
			int sucess = 0, unsuccess = 0;

			while ((input = br.readLine()) != null) {
				if (input.compareTo("") == 0)
					break;
				st = new StringTokenizer(input, " ");

				switch (st.nextToken()) {
				case "c":
					dj.createConnection(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
					break;
				case "q": {
					if (dj.query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
						sucess++;
					} else
						unsuccess++;
				}
					break;
				default:
					break;
				}
			}
			sb.append(sucess + "," + unsuccess + "\n");
			if (tc > 0)
				sb.append("\n");
		}
		pw.print(sb);
		pw.flush();
		br.close();

	}
}
