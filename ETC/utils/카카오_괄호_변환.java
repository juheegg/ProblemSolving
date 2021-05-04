package utils;

import java.util.Scanner;

public class 카카오_괄호_변환 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		a = a.replace('[', '{');
		a = a.replace(']', '}');
		System.out.println(a);
	}

}
