package nadeem;

import java.io.IOException;

import utils.DataDriven;

public class aademo {

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\nadeemuddinsayed\\Desktop\\somu sir\\asder.xlsx";

		DataDriven.getCellData(path, 0, 3, 1);
		System.out.println("========================");

		String b = "Kushboo";
		DataDriven.writeCellData(path, 0, 4, 1, b);
		System.out.println("========================");

		DataDriven.getBelowCellValue(path, "O", 0, 3);
		System.out.println("========================");

		DataDriven.getAdjacentCellValue(path, "I", 0, 0);
		System.out.println("========================");

		String a = "head";
		String reversedLoop = "";
		for (int i = a.length() - 1; i >= 0; i--) {
			reversedLoop += a.charAt(i);
		}
		System.out.println("Reversed (Loop): " + reversedLoop);

		String f = "race";

		String reversed = new StringBuilder(f).reverse().toString();
		System.out.println(reversed.concat("sOOOOO"));
		System.out.println("Reversed (StringBuilder): " + reversed);

	}
}
