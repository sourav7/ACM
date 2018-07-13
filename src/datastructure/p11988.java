import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class Main {
	public static PrintWriter pw = new PrintWriter(System.out);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		while ((input = br.readLine()) != null) {
			getBijuText(input);
		}
	}

	private static void getBijuText(String input) {
		char[] inputArray = input.toCharArray();
		LinkedList<Character> list = new LinkedList<>();
		StringBuilder sb = new StringBuilder("");
		int len = input.length();
		boolean isFirst = true;
		char letter;
		ListIterator<Character> first = list.listIterator();

		for (int i = 0; i < len; i++) {
			letter = inputArray[i];
			if (letter == '[') {
				first = list.listIterator();
				isFirst = true;
				continue;
			} else if (letter == ']') {
				isFirst = false;
				continue;
			}

			if (isFirst)
				first.add(letter);
			else
				list.addLast(letter);
		}
		for (Iterator<Character> iterator = list.iterator(); iterator.hasNext();) {
			sb.append(iterator.next());
		}
		pw.println(sb);
		pw.flush();
	}
}