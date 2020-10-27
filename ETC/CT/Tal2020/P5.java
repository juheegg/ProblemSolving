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

public class P5 {

	public static void main(String[] args) throws IOException {

	}
	
    public static int minX(List<Integer> arr) {
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
            min = Math.min(min, sum);
        }
        
        if (min < 0)
            min = Math.abs(min) + 1;
        else
            min = - min + 1;
        
        return min;
    }
}

