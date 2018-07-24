import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
*
* SuperSale
*
* @author Sourav Debnath
*/

public class p10130 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input;
		StringBuilder sb = new StringBuilder("");
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			int n = Integer.parseInt(br.readLine());
			int[] val = new int[n];
			int wt[] = new int[n];
			int totalWt = 0;
			
			for(int i = 0; i<n; i++)
			{
				st = new StringTokenizer(br.readLine()," ");
				val[i] = Integer.parseInt(st.nextToken());
				wt[i] = Integer.parseInt(st.nextToken());
				totalWt += wt[i];
				
			}
			int result_array[][] = knapsack(val, wt, totalWt);
			int nn = Integer.parseInt(br.readLine());
			int sum = 0;
			while(nn-- > 0)
			{
				int x = Integer.parseInt(br.readLine());
				sum += result_array[val.length][x];
			}
			sb.append(sum + "\n");
		}
		System.out.print(sb);
		
	}
	public static int[][] knapsack(int[] val, int[] wt, int W){
		int row = val.length+1;
		int col = W+1;
		int[][] K = new int[row][col];
		for(int i = 0; i<row; i++)
			for(int j = 0; j<col; j++){
				if(i == 0 || j == 0)
				{
					K[i][j] = 0;
					continue;
				}
				if(j - wt[i-1] >= 0)
					K[i][j] = Math.max(K[i-1][j], val[i-1] + K[i-1][j-wt[i-1]]);
				else
					K[i][j] = K[i-1][j];
			}
		return K;
	}
}
