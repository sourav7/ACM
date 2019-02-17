import java.util.*;
import java.io.*;

/**
* LC-Display
*
* 
* @author Sourav Debnath
*/

public class Problem_706 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input;
		
		while((input = br.readLine()) != null)
		{
			st = new StringTokenizer(input," ");
			int n = Integer.parseInt(st.nextToken());
			String t = st.nextToken();
			if(n == 0) break;
			
			char num[] = t.toCharArray();
			int len = t.length();
			int p = n*2 + 3;
			/////////
			for(int i = 0; i<len; i++){
				printLineUp(num[i], n, true);
				if(i != len-1)
					System.out.print(" ");
			}System.out.println();
			//////////////
			for(int i = 0; i<n; i++){
				for(int j = 0;j<len; j++){
					printStickUp(num[j], n);
					if(j != len-1)
						System.out.print(" ");
				}System.out.println();
			}
			//////////////
			for(int i = 0; i<len; i++){
				printLineMid(num[i], n, true);
				if(i != len-1)
					System.out.print(" ");
			}System.out.println();
			//////////////
			for(int i = 0; i<n; i++){
				for(int j = 0;j<len; j++){
					printStickDown(num[j], n);
					if(j != len-1)
						System.out.print(" ");
				}System.out.println();
			}
			//////////////
			for(int i = 0; i<len; i++){
				printLineDown(num[i], n, true);
				if(i != len-1)
					System.out.print(" ");
			}System.out.println();
			/////////
			System.out.println();
		}
	}
	static void printStickDown(char digit, int num){
		switch(digit)
		{
		case '1':
		case '3':
		case '4':
		case '5':
		case '7':
		case '9':
			System.out.print(" ");
			for(int i = 0; i<num; i++)
				System.out.print(" ");
			System.out.print("|");
			break;
		case '6':
		case '8':
		case '0':
			System.out.print("|");
			for(int i =0; i<num; i++)
				System.out.print(" ");
			System.out.print("|");
			break;
		case '2':
			System.out.print("|");
			for(int i =0; i<num; i++)
				System.out.print(" ");
			System.out.print(" ");
		}
	}
	static void printStickUp(char digit, int num){
		switch(digit){
		case '1':
		case '2':
		case '3':
		case '7':
			System.out.print(" ");
			for(int i =0; i<num; i++)
				System.out.print(" ");
			System.out.print("|");
			break;
		case '5':
		case '6':
			System.out.print("|");
			for(int i =0; i<num; i++)
				System.out.print(" ");
			System.out.print(" ");
			break;
		case '4':
		case '8':
		case '9':
		case '0':
			System.out.print("|");
			for(int i = 0; i< num; i++)
				System.out.print(" ");
			System.out.print("|");
			break;
		}
	}
	static void printLineUp(char digit, int num, boolean seven)
	{
		switch(digit){
		case '1':
		case '4':
			System.out.print(" ");
			for(int i =0; i<num; i++)
				System.out.print(" ");
			System.out.print(" ");
			break;
		case '2':
		case '3':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case '0':
			System.out.print(" ");
			for(int i =0; i<num; i++)
				System.out.print("-");
			System.out.print(" ");
		}
		
	}

	static void printLineMid(char digit, int num, boolean seven)
	{
		switch(digit){
		case '1':
		case '7':
		case '0':
			System.out.print(" ");
			for(int i =0; i<num; i++)
				System.out.print(" ");
			System.out.print(" ");
			break;
		case '2':
		case '3':
		case '5':
		case '6':
		case '4':
		case '8':
		case '9':
			System.out.print(" ");
			for(int i =0; i<num; i++)
				System.out.print("-");
			System.out.print(" ");
		}
		
	}
	
	static void printLineDown(char digit, int num, boolean seven)
	{
		switch(digit){
		case '1':
		case '4':
		case '7':
			System.out.print(" ");
			for(int i =0; i<num; i++)
				System.out.print(" ");
			System.out.print(" ");
			break;
		case '2':
		case '3':
		case '5':
		case '6':
		case '8':
		case '9':
		case '0':
			System.out.print(" ");
			for(int i =0; i<num; i++)
				System.out.print("-");
			System.out.print(" ");
		}
		
	}
	
}
