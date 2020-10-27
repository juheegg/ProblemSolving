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

public class P4 {

	public static void main(String[] args) throws IOException {

	}
	
    public static int isPrime(long n) {
        for (long l = 2L; l < n; l++) {
            if ( n % l == 0)
                return (int) l;
        }
        return 1;
    }
}
