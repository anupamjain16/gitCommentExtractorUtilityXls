package overridenutility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class Main {

	// static int rowCount = 2;

	public static class getNext {
		static int rowCount = 2;

		public static int value() {
			//rowCount = rowCount + 1;
			return ++rowCount;
		}


	}

	public static void main(String[] args) {

		String fileName = "";
		String SourceCodePath = System.getProperty("user.home") + "/Desktop/" + "UitilityOverrid/sourcecode/";
		String gitPath = "/Users/anupam.jain/Desktop/UitilityOverrid/sourcecode/.git";
		
		
		
		//String SourceCodePath = System.getProperty("user.home") + "/Desktop/" + "UitilityOverrid/webseries/";
	    //String gitPath = "/Users/anupam.jain/Desktop/UitilityOverrid/webseries/.git";
				
		
		String foundPath = "";

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		jfc.setDialogTitle("Choose a file to search");

		jfc.setMultiSelectionEnabled(true);

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		File[] files = jfc.getSelectedFiles();

		for (int i = 0; i < files.length; i++)

		{

			System.out.println(files[i].getName());

			Boolean status = isOverride(files[i].getName(), SourceCodePath);

			if (status) {

				foundPath = isOverridePath(files[i].getName(), SourceCodePath);

				System.out.println("File is Override");

				gitCommentWriter(gitPath, files[i].getName(), foundPath);

			}

			else {
				System.out.println("File is Not Override");
			}

		}

		/*
		 * if (returnValue == JFileChooser.APPROVE_OPTION)
		 * 
		 * { File selectedFile = jfc.getSelectedFile(); fileName =
		 * selectedFile.getName();
		 * 
		 * System.out.println(fileName);
		 * 
		 * }
		 */

	}

	private static void gitCommentWriter(String gitPath, String fileName, String foundPath) {

		FileRepositoryBuilder builder = new FileRepositoryBuilder();

		// Repository repo = builder.setGitDir(new
		// File("localrepositary"+"\\.git")).setMustExist(true).build();

		try {
			Repository repo = builder.setGitDir(new File(gitPath)).setMustExist(true).build();

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

			OverridingData.put("1", new Object[] { "JIRA Comment", "FILE NAME", "PATH" });

			// git comments iterator

			 //int rowCount = 2;

			int rowCount = getNext.value();

			
			
			Iterable<RevCommit> log = git.log().call();
			
			
			
			for (Iterator<RevCommit> iterator = log.iterator(); iterator.hasNext();)

			{
				RevCommit rev = iterator.next();
				
				rev.getName();
				
				System.out.println(getNext.value());
				OverridingData.put(Integer.toString(getNext.value()),
						new Object[] { rev.getFullMessage(), fileName, foundPath });
				//rowCount++;

				System.out.println(rev.getFullMessage());

			}

			// getNext.setValue(rowCount);

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

	}

	private static boolean isOverride(String fileName, String SourceCodePath) {
		File root = new File(SourceCodePath);

		boolean recursive = true;

		Collection files = FileUtils.listFiles(root, null, recursive);

		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			if (file.getName().equals(fileName)) {

				String foundPath = file.getAbsolutePath();

				System.out.println(foundPath);

				return true;
			}
		}

		return false;
	}

	private static String isOverridePath(String fileName, String SourceCodePath) {
		File root = new File(SourceCodePath);

		boolean recursive = true;

		Collection files = FileUtils.listFiles(root, null, recursive);

		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			if (file.getName().equals(fileName)) {

				String foundPath = file.getAbsolutePath();

				System.out.println(foundPath);

				return foundPath;
			}
		}

		return "";
	}

}
