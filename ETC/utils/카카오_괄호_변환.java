package utils;

public class 카카오_괄호_변환 {

	public static void main(String[] args) {
		String a = "[[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]";
		a = a.replace('[', '{');
		a = a.replace(']', '}');
		System.out.println(a);
	}

}
