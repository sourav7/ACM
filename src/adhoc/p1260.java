import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Sales
 * 
 * @author Sourav Debnath
 *
 */
public class Problem1260 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		int n = 0, cnt = 0;
		int length = 0;
		int val = 0;
		Iterator<Integer> it;
		LinkedList<Integer> list;

		Comparator<Integer> cmp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};

		int tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			list = new LinkedList<>();
			list.add(Integer.parseInt(st.nextToken()));
			cnt = 0;

			for (int i = 1; i < n; i++) {
				list.sort(cmp);
				val = Integer.parseInt(st.nextToken());
				length = 0;

				it = list.listIterator();

				while (it.hasNext() && (it.next() <= val)) {
					length++;
				}
				list.add(val);
				cnt += length;
			}
			sb.append(cnt + "\n");
		}
		pw.print(sb);
		pw.flush();
		br.close();
		pw.close();
	}
}
