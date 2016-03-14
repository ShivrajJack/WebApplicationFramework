package com.Unilog.drivers;

import java.util.ArrayList;

import com.Unilog.libraries.ExcellLibraries;

public class Scripts {

	ExcellLibraries lib = new ExcellLibraries();

	@SuppressWarnings("unchecked")
	public ArrayList<String> scr() {
		@SuppressWarnings("rawtypes")
		ArrayList ar = new ArrayList();
		int step1 = lib.getRowCount("Scenarios");
		System.out.println("row count "+step1);

		for (int i = 1; i <= step1; i++) {
			String s1 = lib.getExcelData("Scenarios", i, 2);
			if (s1.equalsIgnoreCase("yes")) {
				String s2 = lib.getExcelData("Scenarios", i, 1);
				System.out.println("Scriptname "+s2);
				ar.add(s2);
				 
			}
			System.out.println("scenarios " + s1);

		}

		return ar;
	}

}