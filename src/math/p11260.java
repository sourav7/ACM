import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * Root Odd Sum
 * 
 * @author Sourav Debnath
 *
 */
public class Problem11260 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		String input = null;
		StringBuilder sb = new StringBuilder("");

		while ((input = br.readLine()) != null) {
			if (input.compareTo("0") == 0)
				break;
			Long num = Long.parseUnsignedLong(input);

			Long limit = (long) Math.sqrt(num);

			long squareSumLimit = limit - 1;

			BigInteger sqrSmLmt = BigInteger.valueOf(squareSumLimit);

			BigInteger sqrSum = (sqrSmLmt.multiply((sqrSmLmt.add(BigInteger.ONE))));
			sqrSum = (sqrSum.multiply(sqrSmLmt.multiply(BigInteger.valueOf(2)).add(BigInteger.ONE)))
					.divide(BigInteger.valueOf(6));

			long nthOfOdd = (limit - 1) / 2 + 1;

			BigInteger oddSum = BigInteger.valueOf(nthOfOdd).multiply(BigInteger.valueOf(nthOfOdd));

			// extras
			if (num % 2 == 0)
				num--;
        
			Long limLim = Long
					.parseUnsignedLong(BigInteger.valueOf(limit).multiply(BigInteger.valueOf(limit)).toString());
			if (limLim % 2 == 1)
				limLim++;
			long extras = 0;
			if (num - limLim > 0)
				extras = (num - limLim) / 2 + 1;
			else
				extras = 0;

			BigInteger xtraSum = BigInteger.valueOf(limit).multiply(BigInteger.valueOf(extras));

			sb.append(sqrSum.add(oddSum).add(xtraSum).mod(BigInteger.valueOf(100000000)) + "\n");

		}
		pw.print(sb);
		pw.flush();
		br.close();
		pw.close();
	}

}
