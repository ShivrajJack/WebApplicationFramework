package com.Unilog.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipResults {

	/**
	 * <p>
	 * <b>Zipresult function name :</b> zipRes
	 * </p>
	 * <p>
	 * <b>Description :</b> Creating a Zip of Results files
	 * </p>
	 * 
	 * @throws Exception
	 */
	public void zipRes() throws Exception {
		 
Browser br = new Browser();
		String path = br.testConfigFile.getProperty("zipResultPath");
		String res = br.testConfigFile.getProperty("resultPath");
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
	
		String dt = date.toString().replace(":", "_");
		System.out.println(date.toString());
		zipFolder(res, path + "/" + dt + ".zip");
	}

	/**
	 * <p>
	 * <b>Zipresult function name :</b> zipFolder
	 * </p>
	 * <p>
	 * <b>Description :</b> Creating a Zip Folders for Results files
	 * </p>
	 * 
	 * @param srcFolder
	 * @param destZipFile
	 * @throws Exception
	 */
	static public void zipFolder(String srcFolder, String destZipFile)
			throws Exception {
		ZipOutputStream zip = null;
		FileOutputStream fileWriter = null;

		fileWriter = new FileOutputStream(destZipFile);
		zip = new ZipOutputStream(fileWriter);

		addFolderToZip("", srcFolder, zip);
		zip.flush();
		zip.close();
	}

	/**
	 * <p>
	 * <b>Zipresult function name :</b> addFileToZip
	 * </p>
	 * <p>
	 * <b>Description :</b> Adding the files to Zip Folders
	 * </p>
	 * 
	 * @param path
	 * @param srcFile
	 * @param zip
	 * @throws Exception
	 */
	static private void addFileToZip(String path, String srcFile,
			ZipOutputStream zip) throws Exception {
		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			@SuppressWarnings("resource")
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
		}
	}

	/**
	 * <p>
	 * <b>Zipresult function name :</b> addFolderToZip
	 * </p>
	 * <p>
	 * <b>Description :</b> Adding the folders to Zip
	 * </p>
	 * 
	 * @param path
	 * @param srcFolder
	 * @param zip
	 * @throws Exception
	 */
	static private void addFolderToZip(String path, String srcFolder,
			ZipOutputStream zip) throws Exception {
		File folder = new File(srcFolder);

		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
			} else {
				addFileToZip(path + "/" + folder.getName(), srcFolder + "/"
						+ fileName, zip);
			}
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

