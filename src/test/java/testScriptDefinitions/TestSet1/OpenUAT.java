package testScriptDefinitions.TestSet1;

import FrameworkLibrary.ExcelLibrary;

public class OpenUAT  extends ExcelLibrary{

	public static void main(String args[]) {
		// sum of n natural numbers
		int n = 5;
		int sum = 0;
		String s = "a";
		String StrValue="c";
		String e = "bca";
		if (e.contains(StrValue)) {
			putActualResult(6, "PASS", 1);
		} else {
			putActualResult(6, "FAIL", 1);
		}

	}
}
