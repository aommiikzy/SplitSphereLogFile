

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class SplitSphereLogFile {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//This is file from Sirichoke Yooyen 
		//If has any question please contact to Duece.
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the file name of SphereLog file : ");
		String input = scan.nextLine();
	
		System.out.println("Your file name is " + input);
	        int count=0;
	        int frame=0;
	      int countstart = 0;
	            StringBuilder start = new StringBuilder();

		        try (BufferedReader br = Files.newBufferedReader(Paths.get(input))) {

		            String line;
		            int numberpre = -1;
		            int numbercur = 1;
		            int check = 0;
		            while ((line = br.readLine()) != null) {
		            	String [] sentences = line.split(" ");
		            
		            		if(line.trim().isEmpty())
		            		{
		            			continue;
		            		}
		            		if (sentences[1].contains("frame")==true)
		            		{
		     
		            			check=1;
	         		
		            			}	
		            		else if(check==1)
		            		{
		            			numbercur = Integer.parseInt(sentences[0]);
//		            			System.out.println("numbercur = "+numbercur);
		            			
		            			if(numbercur < numberpre)
		            			{
		            				break;
		            			}
		            			else if(numbercur > numberpre)
		            			{
		            				countstart ++;
		            				numberpre = numbercur;
		            			}
		            			
		            		}
	            		
		            		
		            }
//		            System.out.println("count = "+countstart);
		            }

		        catch (IOException e) {
		            System.err.format("Your file name is invalid or your file is not in the same folder of SplitSphereLogFile.java\n");
		        }
////NewVersionn
		        LinkedList<Integer> Skip = new LinkedList<Integer>(); 

				try (BufferedReader br = Files.newBufferedReader(Paths.get(input))) {

				    // read line by line
				    String line;
				    
				    int round = 0;
				    int checkDiff = 0;int checkNextLine = 0;int check3 =0;
				    try {
						while ((line = br.readLine()) != null) {
							round++;
							if(line.trim().isEmpty()&&checkDiff==1)
								{
									checkNextLine=1;
									check3++;
									if(checkDiff==1&&checkNextLine==1&&(round-check3)<=2)
									{
										Skip.add(round-1);
										Skip.add(round);
//										System.out.println("Frame = "+(round-1));
//										System.out.println("Line = "+round);
										
										checkNextLine=0;
										checkDiff=1;
									}	
									continue;
								}

							String [] sentence = line.split(" ");
							
							 if(sentence[1].equals("frame") == true)
							{
								checkDiff=1;
								check3=round;
								
							}
				
	}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}

	} catch (IOException e1) {
		// TODO Auto-generated catch block
		//e1.printStackTrace();
	}
				
//				System.out.println("Size =============== "+Skip.size());
				for(int i=0;i<Skip.size();i++)
				{
					//System.out.println("Skip = "+Skip.get(i));
				}
				//System.out.println("Size =============== "+Skip.size());
		        ////NewVersionn	        

System.out.println("Total leaf node = "+countstart); 

	        StringBuilder sb = new StringBuilder();

			try (BufferedReader br = Files.newBufferedReader(Paths.get(input))) {

			    // read line by line
			    String line;
			    int first = 0;
			    int i = 1;
			    int check = 0;
			    int roundFinal = 0;
			    int position=0;
			    while ((line = br.readLine()) != null) {
			    	roundFinal++;
			    	if(roundFinal==Skip.get(position))
			    	{	position++;
			    		continue;
			    	}
			    		if(line.trim().isEmpty())
			    		{
			    			continue;
			    			
			    		}
			    		String [] sentence = line.split(" ");
//		            		System.out.println("i = "+i); 
//		            		System.out.println("size = "+size);
//		            		System.out.println(line);    
			    		i++;
//		            		if (i == 33000)
//		            		{
//		            			break;
//		            		}
			    		if( sentence[1].equals("frame") == true &&  first==0)
						{
							
//							System.out.println("Hey It must create a new file");    
							try {
								//System.out.println("Creating file number "+frame);  
								FileWriter aom = new FileWriter("0"+".log");
								System.out.println("Create file at frame "+frame);   
								aom.write("# SphereID Center[X Y Z] Radius Density Region Score Weight\n");
								aom.write("LeafCount "+countstart);
								aom.write("\n");
								aom.write("NodeCount "+countstart);
								aom.write("\n");
								aom.close();    
								
								first =1;
							}
				        	catch (IOException e) {
								//e.printStackTrace();
							}
						}
			    		else if( sentence[1].equals("frame") == true  &&  first==1)
			    			{
			    				 frame = Integer.parseInt(sentence[3]);
			    				//System.out.println("Hey It must create a new file");    
			    				try {
			    					//count++;
			    					  
			    					FileWriter aom = new FileWriter(frame+".log");
			    					System.out.println("Create file at frame "+frame);    
			    					aom.write("# SphereID Center[X Y Z] Radius Density Region Score Weight\n");
			    					aom.write("LeafCount "+countstart);
									aom.write("\n");
									aom.write("NodeCount "+countstart);
									aom.write("\n");
			    					aom.close();    
			    					check=1;
			    					
			    				}
			    	        	catch (IOException e) {
			    					//e.printStackTrace();
			    				}
			    			}
			    		else if(first==1 && check ==0)
			    			{
			    			File file = new File("0"+".log");
			    			if (!file.exists()) {
			    				try {
			    					file.createNewFile();
			    				} catch (IOException e) {
			    					//e.printStackTrace();
			    				}
			    			} 
			    		  
			    			try {
			    				BufferedWriter buf = new BufferedWriter(new FileWriter(file, true)); 
			    				buf.append("1 ");
			    				buf.append(line);
			    				buf.newLine();
			    				buf.close();
			    			} catch (IOException e) {
			    				//e.printStackTrace();
			    			}
			    			}
			    			else if(check==1)
			    			{
			    			File file = new File(frame+".log");
			    			if (!file.exists()) {
			    				try {
			    					file.createNewFile();
			    				} catch (IOException e) {
			    					//e.printStackTrace();
			    				}
			    			} 
			    		  
			    			try {
			    				BufferedWriter buf = new BufferedWriter(new FileWriter(file, true)); 
			    				buf.append("1 ");
			    				buf.append(line);
			    				buf.newLine();
			    				buf.close();
			    			} catch (IOException e) {
			    				//e.printStackTrace();
			    			}
			    			}
			    				
 
			    }

			} catch (IOException e) {
			    //System.err.format("IOException: %s%n", e);
			}

	    }}
	
	


