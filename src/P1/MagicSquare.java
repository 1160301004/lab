package P1;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
public class MagicSquare {
		public static boolean isLegalMagicSquare(String fileName)
		{
			String sq[][]=new String[200][200];
			int n=0;//n为行数
			BufferedReader br=null;
			try
			{
				br=new BufferedReader(new FileReader(new File(fileName)));
				String str=null;
				while((str=br.readLine())!=null)
				{
					sq[n]=str.split("\t");
					n++;
				}
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			int m=sq[0].length;//m为列数
			int square[][]=new int[n][m];
			int i,j;
			for(i=0;i<n;i++)
			{
				if(sq[i].length!=m)
				{
					System.out.println("Not a matrix");
					return false;
				}
				for(j=0;j<m;j++)
				{
					Pattern pattern = Pattern.compile("^-?[0-9]+");
					Matcher isNum = pattern.matcher(sq[i][j]);
					if(!isNum.matches())
					{
						//System.out.println(sq[i][j]);
						System.out.println("not all using '\t'");
						return false;
					}
					else
					{
						square[i][j]=Integer.parseInt(sq[i][j]);
						if(square[i][j]<=0)
						{
							System.out.println("not all positive integer");
							return false;
						}
					}
				}
			}
			//System.out.println(+m);
			//System.out.println(+n);
			if(m!=n)
			{
				System.out.println("row!=column");
				return false;
			}
			else
			{
				int sum=0;
				int sum_temp=0;
				for(int index=0;index<n;index++)
					sum = sum+square[index][index];
				for(int index=0;index<n;index++)
					sum_temp+=square[index][n-1-index];
				if(sum_temp!=sum)
					return false;
				for(int row=0;row<n;row++)
				{
					sum_temp=0;
					for(int column=0;column<n;column++)
						sum_temp+=square[row][column];
					if(sum_temp!=sum)
						return false;
				}
				for(int column=0;column<n;column++)
				{
					sum_temp=0;
					for(int row=0;row<n;row++)
						sum_temp+=square[row][column];
					if(sum_temp!=sum)
						return false;
				}
				return true;
			}
		}
		public static boolean genereateMagicSquare(int n) {
			if(n <= 0)
			{
				System.out.println("这是一个负数：");
				return false;
			}
			else if(n % 2==0)
			{
				System.out.println("这是一个偶数：");
				return false;
			}
			int magic[][] = new int[n][n];
			int row = 0, col = n / 2, i,j,square=n*n;
			for(i=1;i<=square;i++)
			{
				magic[row][col]=i;
				if(i%n==0)
					row++;
				else
				{
					if(row==0)
						row=n-1;
					else
						row--;
					if(col==(n-1))
						col = 0;
					else
						col++;
				}
			}
			try
			{
				BufferedWriter out=new BufferedWriter(new FileWriter("src\\P1\\txt\\6.txt"));
				//FileWriter out=new FileWriter(file);
				for(i=0;i<n;i++)
				{
					for(j=0;j<n;j++)
					{
						System.out.print(magic[i][j]+"\t");
						out.write(magic[i][j]+"\t");
					}
					System.out.println();
					out.write("\n");
				}
				out.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			return true;
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			System.out.println("1.txt:");
			System.out.println(isLegalMagicSquare("src\\P1\\txt\\1.txt"));
			System.out.println("2.txt:");
			System.out.println(isLegalMagicSquare("src\\P1\\txt\\2.txt"));
			System.out.println("3.txt:");
			System.out.println(isLegalMagicSquare("src\\P1\\txt\\3.txt"));
			System.out.println("4.txt:");
			System.out.println(isLegalMagicSquare("src\\P1\\txt\\4.txt"));
			System.out.println("5.txt:");
			System.out.println(isLegalMagicSquare("src\\P1\\txt\\5.txt"));
			System.out.println("请输入一个参数:");
			Scanner sc = new Scanner(System.in);
			int n=sc.nextInt();
			boolean a=genereateMagicSquare(n);
			if(a==false)
			{
				System.out.println("false");
			}
			else
				System.out.println(isLegalMagicSquare("src\\P1\\txt\\6.txt"));
		}
}
