package overridenutility;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFileChooser;
import java.io.File;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.*;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.util.RefList.Builder;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
//file writing jars

import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Run {

	private static String foundPath;

	public static void main(String[] args) {

		
		
		
		
		
		String desktop = System.getProperty("user.home") + "/Desktop/" + "/clonned/";

		FileRepositoryBuilder builder = new FileRepositoryBuilder();

		// Repository repo = builder.setGitDir(new
		// File("localrepositary"+"\\.git")).setMustExist(true).build();

		try {
			Repository repo = builder.setGitDir(new File("/Users/anupam.jain/Desktop/UitilityOverrid/sourcecode/.git"))
					.setMustExist(true).build();

			Git git = new Git(repo);

			/// Fore file wrinting

			// workbook object
			XSSFWorkbook workbook = new XSSFWorkbook();

			// spreadsheet object
			XSSFSheet spreadsheet = workbook.createSheet(" Overriding File Data ");

			// creating a row object
			XSSFRow row;

			// This data needs to be written (Object[])
			Map<String, Object[]> OverridingData = new TreeMap<String, Object[]>();

			OverridingData.put("1", new Object[] { "JIRA Comment", "FILE NAME" });

			// git comments iterator

			int rowCount = 2;

			Iterable<RevCommit> log = git.log().call();
			for (Iterator<RevCommit> iterator = log.iterator(); iterator.hasNext();)

			{
				RevCommit rev = iterator.next();

				OverridingData.put(Integer.toString(rowCount), new Object[] { rev.getFullMessage() , "anc.java" });
				rowCount++;

				System.out.println(rev.getFullMessage());

				

			}

			Set<String> keyid = OverridingData.keySet();

			int rowid = 0;

			// writing the data into the sheets...

			for (String key : keyid) {

				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = OverridingData.get(key);
				int cellid = 0;

				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
					cell.setCellValue((String) obj);
				}
			}

			// .xlsx is the format for Excel Sheets...
			// writing the workbook into the file...
			FileOutputStream out = new FileOutputStream(
					new File("/Users/anupam.jain/Desktop/UitilityOverrid/OverrideFileData.xlsx"));

			workbook.write(out);
			out.close();

		} catch (IOException | GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * CredentialsProvider cp = new
		 * UsernamePasswordCredentialsProvider("jainanupam16@gmail.com" ,
		 * "#13apr1996*"); CredentialsProvider cp2 = new
		 * UsernamePasswordCredentialsProvider("anupam.jain@bottomline.com" ,
		 * "B0tt0mline47@*");
		 * 
		 * 
		 * 
		 * try { Git.cloneRepository()
		 * .setURI("ssh://git@bitbucket.bottomline.tech:7999/~anupam.jain/cnb.git")
		 * .setDirectory(new File(desktop)) .setCredentialsProvider(cp2) .call(); }
		 * catch (GitAPIException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 */

		// IMportan info -- git log -- [filename] for get file history

		// git log --pretty=format:%s -- anupam.java > logs.txt to save history in file

		/*
		 * System.out.println("anupam");
		 * 
		 * //File file = new File("C:" + File.separator + "jdk11.0.2" + File.separator,
		 * "demo1.java");
		 * 
		 * 
		 * 
		 * String desktop = System.getProperty ("user.home") + "/Desktop/";
		 * 
		 * System.out.println(desktop);
		 * 
		 * 
		 * File myFile = new File (desktop + "MyFile.txt");
		 * 
		 * System.out.println("File name: " + myFile.getName());
		 * System.out.println("Path name: " + myFile.getPath());
		 * 
		 * 
		 * /////
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Panel parent = new Panel();
		 * 
		 * 
		 * JFileChooser fileChooser = new JFileChooser();
		 * fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		 * int result = fileChooser.showOpenDialog(parent); if (result ==
		 * JFileChooser.APPROVE_OPTION) { File selectedFile =
		 * fileChooser.getSelectedFile(); System.out.println("Selected file: " +
		 * selectedFile.getAbsolutePath()); }
		 * 
		 */

		String fileName = "";
		String SourceCodePath = "";
		String foundPath = "";

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Choose a file to search");

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION)

		{
			File selectedFile = jfc.getSelectedFile();
			fileName = selectedFile.getName();

			System.out.println(fileName);

		}

		/*
		 * For making source code path pickup from dialoge JFileChooser jfc2 = new
		 * JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		 * jfc2.setDialogTitle("Choose a directory of sourcce code");
		 * jfc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 * 
		 * int returnValue2 = jfc.showOpenDialog(null); if (returnValue2 ==
		 * JFileChooser.APPROVE_OPTION)
		 * 
		 * { if(jfc2.getSelectedFile().isDirectory()) {
		 * 
		 * File selectedFile = jfc.getSelectedFile(); String path =
		 * selectedFile.getAbsolutePath();
		 * 
		 * System.out.println(path);
		 * 
		 * 
		 * System.out.println("You selected the directory: " + jfc2.getSelectedFile());
		 * } }
		 * 
		 */

		SourceCodePath = System.getProperty("user.home") + "/Desktop/" + "UitilityOverrid/sourcecode/";

		Boolean status = isOverride(fileName, SourceCodePath);

		if (status) {
			System.out.println("File is Override");
		}

		else {
			System.out.println("File is Not Override");
		}
	}

	

	private static boolean isOverride(String fileName, String SourceCodePath) {
		File root = new File(SourceCodePath);

		boolean recursive = true;

		Collection files = FileUtils.listFiles(root, null, recursive);

		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			if (file.getName().equals(fileName)) {

				foundPath = file.getAbsolutePath();

				System.out.println(foundPath);

				return true;
			}
		}

		return false;
	}

}
