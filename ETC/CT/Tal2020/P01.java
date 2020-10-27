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

public class P01 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		int result = Result.balancedSum(arr);
		System.out.println(result);

		bufferedReader.close();

	}

}

class Result {

	/*
	 * Complete the 'balancedSum' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static int balancedSum(List<Integer> arr) {

		int right = 0;
		int left = 0;
		for (int i = 1; i < arr.size(); i++) {
			right += arr.get(i);
		}

		Queue<Integer> pivots = new PriorityQueue<>();

		for (int i = 0; i < arr.size()-1; i++) {
			if (left == right)
				pivots.add(i);
			left += arr.get(i);
			right -= arr.get(i+1);

		}

		return pivots.poll();
	}

}