package overridenutility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

	public static void main(String[] args) {

		try {

			// git log --pretty=format:%s -- BaiLoadPostProc.java
            // git log --pretty=format:%s -- filename = anupam.java
		
			
			//String gitpath = "--git-dir=/Users/anupam.jain/Desktop/UitilityOverrid/sourcecode/.git";
            //String options = "--pretty=format:%s -- filename = anupam.java";
           // String fileName = "-- anupam.java";
            

			//String[] args1 = new String[] { "ls" };

			 String[] args1 = new String[] {"git", "log" , "--pretty=format:%s" , "-- filename = anupam.java"};
		
			 
			 

             //ProcessBuilder proc = new ProcessBuilder("git", "log", options;
             
             

			// Process proc = new ProcessBuilder(args1).start();

			ProcessBuilder proc = new ProcessBuilder(args1);

			proc.directory(new File("/Users/anupam.jain/Desktop/UitilityOverrid/sourcecode/test1"));

			// Process proc = Runtime.getRuntime().exec(command);

			// Read the output
			System.out.println("Running command : " + proc.command());
			
			Process p = proc.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.print(line + "\n");
			}

			p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
