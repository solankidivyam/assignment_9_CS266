//Divyam Solanki
//201951198
//2D
//We need to implement the Banker's Algorithm

package com.devmanuals;
import java.util.*;
public class Divyam_Solanki_Assignment_9 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of processes : ");
		int process = sc.nextInt();

		System.out.print("Enter number of resources : ");
		int resour = sc.nextInt();
		
		//Taking the inputs
		int temp[] =new int[resour];
		int inst[] = new int[resour];
		int value[] = new int[resour];

		
		for(int i=1;i<=resour;i++)
		{
			System.out.println("Please provide the instance for resource " + i);
			int z = sc.nextInt();
			inst[i-1] = z;
		}

		for(int i=0;i<resour;i++)
		{
			temp[i] = inst[i]-value[i];
		}


		int maxdem[][] = new int[process][resour];
		int resour_alloc[][] = new int[process][resour];
		int neccesary_resour[][] = new int[process][resour];
		
		for(int i=1;i<=process;i++)
		{
			for(int j=1;j<=resour;j++)
			{
				System.out.println("Please provide the largest value for resource " + j +" of process "+i);
				int x = sc.nextInt();
				maxdem[i-1][j-1] = x;
			}
		}
		for(int i=1;i<=process;i++)
		{
			for(int j=1;j<=resour;j++)
			{
				System.out.println("Please provide the allocated value for resource "+j+" of process "+i);
				int x = sc.nextInt();
				resour_alloc[i-1][j-1] = x;
				value[j-1]=value[j-1]+x;
			}
		}
		
		for(int i=1;i<=process;i++)
		{
			for(int j=1;j<=resour;j++)
			{
				
				neccesary_resour[i-1][j-1]=maxdem[i-1][j-1]-resour_alloc[i-1][j-1];
			}
		}
		System.out.println("Matrix that we need : ");
		for(int i=1;i<=process;i++)
		{
			for(int j=1;j<=resour;j++)
			{
				System.out.print(neccesary_resour[i-1][j-1]+" ");
			}
				System.out.println();
		}

		int z = output(process,resour,resour_alloc,temp,neccesary_resour);
		int a;
		if(z==1)
		{
			System.out.println("Name the process for which you want to change the request value");
			a = sc.nextInt();
			for(int i=0;i<resour;i++)
			{
			System.out.println("Please provide the largest resource " + i + " for process "+ a);
			int x = sc.nextInt();
			maxdem[a-1][i] +=x;
			}
			for(int j=1;j<=resour;j++)
			{
				
				neccesary_resour[a-1][j-1]=maxdem[a-1][j-1]-resour_alloc[a-1][j-1];
			}
			System.out.println("Matrix that we need : ");
			for(int i=1;i<=process;i++)
			{
				for(int j=1;j<=resour;j++)
				{
					System.out.print(neccesary_resour[i-1][j-1] + " ");
				}
					System.out.println();
				}
			int y = output(process,resour,resour_alloc,temp,neccesary_resour);
		}
		sc.close();
	}

	public static int output(int process,int resour,int resour_alloc[][],int temp[],int neccesary_resour[][])
	{
		int finish[] = new int[process];
		int sequence[] = new int[process];
		int count =0;
		while(count < process)
		{
			int result = 0;
			for(int i = 0;i<process;i++)
			{
				if(finish[i]==0)
				{
					int j;
					for( j =0;j<resour;j++)
					{
						if(neccesary_resour[i][j] > temp[j])
							break;
					}
						if(j == resour)
						{
							for(int k =0; k<resour;k++)
							{
								temp[k] = temp[k]+resour_alloc[i][k];
							}
							sequence[count++] = i;
							finish[i] = result = 1;
						}
					}
				}
				if(result == 0 )
				{
					System.out.println("Not in the SAFE STATE");
					return 0;
				}
			}
			System.out.println("SAFE STATE ACHIEVED");
			System.out.println("THE SEQUENCE IS : ");

			for(int i=0;i<process;i++)
			{
				System.out.print(sequence[i]+ " ");
			}

			System.out.println();
			return 1;
	}

}
