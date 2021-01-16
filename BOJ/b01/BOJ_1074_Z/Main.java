package b01.BOJ_1074_Z;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		long N, r, c;
		N = Long.parseLong(s.next());
		r = Long.parseLong(s.next());
		c = Long.parseLong(s.next());

		System.out.println(zz(N - 1, r, c));
	}

	static long zz(long n, long r, long c) {
		if (n == 0) {
			if (r == 0 && c == 0)
				return 0;
			else if (r == 0 && c == 1)
				return 1;
			else if (r == 1 && c == 0)
				return 2;
			else
				return 3;
		}
		long div = (long) Math.pow(2, n);
		long result = 0L;
		if (r / div == 0 && c / div == 0) {
			result = (long) Math.pow(4, n) * 0 + zz(n - 1, r, c);
		} else if (r / div == 0 && c / div == 1) {
			result = (long) Math.pow(4, n) * 1 + zz(n - 1, r, c % div);
		} else if (r / div == 1 && c / div == 0) {
			result = (long) Math.pow(4, n) * 2 + zz(n - 1, r % div, c);
		} else {
			result = (long) Math.pow(4, n) * 3 + zz(n - 1, r % div, c % div);
		}
		return result;
	}

}