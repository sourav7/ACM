import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Count The Faces
 * 
 * @author CSE
 *
 */
class Vertex {
	char data;
	int rank;
	Vertex parent;

	public Vertex(char data) {
		this.data = data;
		this.parent = this;
		this.rank = 0;
	}
}

class DisjointSetOfVertex {
	private Map<Character, Vertex> map;
	private int numberOfPlane = 1;

	public int getNumberOfPlane() {
		return numberOfPlane;
	}

	public DisjointSetOfVertex(int n) {
		map = new TreeMap<>();
	}

	public void makeFace(char a, char b) {
		if (a == b) {
			numberOfPlane++;
			return;
		}
		Vertex aa = map.get(a);
		Vertex bb = map.get(b);
		if (aa == null)
			aa = makeSet(a);
		if (bb == null)
			bb = makeSet(b);

		Vertex parent1 = findSet(aa);
		Vertex parent2 = findSet(bb);

		if (parent1 == parent2) {
			numberOfPlane++;
			return;
		}
		if (parent1.rank >= parent2.rank) {
			parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
			parent2.parent = parent1;
		} else
			parent1.parent = parent2;
	}

	private Vertex findSet(Vertex v) {
		if (v.parent == v)
			return v;
		return v.parent = findSet(v.parent);
	}

	private Vertex makeSet(char data) {
		Vertex v = new Vertex(data);
		map.put(data, v);
		return v;
	}
}

public class Problem10178 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		String input = null;
		StringTokenizer st = null;
		while ((input = br.readLine()) != null) {
			st = new StringTokenizer(input, " ");
			int node = Integer.parseInt(st.nextToken());
			int edge = Integer.parseInt(st.nextToken());

			DisjointSetOfVertex dj = new DisjointSetOfVertex(node);
			for (int i = 0; i < edge; i++) {
				input = br.readLine();
				dj.makeFace(input.charAt(0), input.charAt(2));
			}
			sb.append(dj.getNumberOfPlane() + "\n");
		}
		pw.print(sb);
		pw.flush();
	}
}
