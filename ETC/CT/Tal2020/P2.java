package CT.Tal2020;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class P2 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String word = bufferedReader.readLine();

		String result = Result2.lastLetters(word);

		System.out.println(result);
		bufferedReader.close();
	}
}

class Result2 {

	/*
	 * Complete the 'balancedSum' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static String lastLetters(String word) {
		return word.charAt(word.length() - 1) + " " + word.charAt(word.length() - 2);
	}

}