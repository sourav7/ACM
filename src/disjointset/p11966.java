
import java.io.IOException;
import reader.ReaderClass;

/**
 * Galactic Bonding
 * 
 * @author Sourav Debnath
 *
 */
class Star {
	double x;
	double y;
	Star parent;
	int rank;

	public Star(double x, double y) {
		this.x = x;
		this.y = y;
		this.parent = this;
		this.rank = 0;
	}
}

public class Problem11966 {
	public static Star[] stars = new Star[1002];

	public static void main(String[] args) throws IOException {
		ReaderClass reader = new ReaderClass();
		int testCase = reader.nextInteger();

		for (int tc = 1; tc <= testCase; tc++) {
			int node = reader.nextInteger();
			double dist = reader.nextDouble();
			dist = dist * dist;

			int ans = node;

			for (int i = 0; i < node; i++) {
				stars[i] = new Star(reader.nextDouble(), reader.nextDouble());

				for (int j = 0; j < i; j++) {
					if (findDistSquare(stars[i], stars[j]) <= dist) {
						if (union(stars[i], stars[j]))
							ans--;
					}
				}
			}
			System.out.println("Case " + tc + ": " + ans);
		}
	}

	private static double findDistSquare(Star star, Star star2) {
		double xDif = star.x - star2.x;
		double yDif = star.y - star2.y;
		return (xDif * xDif) + (yDif * yDif);
	}

	private static Star findSet(Star p) {
		Star parent = p.parent;
		if (p == parent)
			return p;
		p.parent = findSet(p.parent);
		return p.parent;
	}

	private static boolean union(Star a, Star b) {
		Star parentA = findSet(a);
		Star parentB = findSet(b);
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
}
