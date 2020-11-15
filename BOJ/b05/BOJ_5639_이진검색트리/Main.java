package b05.BOJ_5639_이진검색트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Node root = new Node(Integer.parseInt(br.readLine()));

		String str = br.readLine();
		while (str != null && str.length() != 0) {
			int x = Integer.parseInt(str);
			Node cur = root;
			while (cur.val != 0) {
				if (cur.val > x) {
					if (cur.left == null) {
						cur.left = new Node(x);
						break;
					} else {
						cur = cur.left;
					}
				} else {
					if (cur.right == null) {
						cur.right = new Node(x);
						break;
					} else
						cur = cur.right;
				}
			}
			str = br.readLine();
		}

		search(root);
	}

	private static void search(Node node) {
		if (node.left != null)
			search(node.left);

		if (node.right != null)
			search(node.right);

		System.out.println(node.val);
	}

	static class Node {
		int val;
		Node left, right;

		public Node(int val) {
			this.val = val;
		}
	}

}
