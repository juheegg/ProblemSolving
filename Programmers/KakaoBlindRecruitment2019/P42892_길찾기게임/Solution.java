package KakaoBlindRecruitment2019.P42892_길찾기게임;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

	static public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];

		Queue<Node> que = new PriorityQueue<>();
		for (int i = 0; i < nodeinfo.length; i++) {
			que.add(new Node(nodeinfo[i][1], nodeinfo[i][0], i + 1));
		}

		Node root = que.poll();
		while (!que.isEmpty()) {
			root.add(que.poll());
		}

		List<Integer> pre = new ArrayList<>();
		preorder(root, pre);
		for (int i = 0; i < pre.size(); i++) {
			answer[0][i] = pre.get(i);
		}
		List<Integer> post = new ArrayList<>();
		postorder(root, post);
		for (int i = 0; i < post.size(); i++) {
			answer[1][i] = post.get(i);
		}
		return answer;
	}

	private static void postorder(Node node, List<Integer> post) {
		if (node.left != null)
			postorder(node.left, post);
		if (node.right != null)
			postorder(node.right, post);
		post.add(node.no);
	}

	private static void preorder(Node node, List<Integer> pre) {
		pre.add(node.no);
		if (node.left != null)
			preorder(node.left, pre);
		if (node.right != null)
			preorder(node.right, pre);
	}

	static class Node implements Comparable<Node> {
		Node left, right;
		int r, c, no;

		public Node(int r, int c, int no) {
			super();
			this.r = r;
			this.c = c;
			this.no = no;
		}

		public void add(Node input) {
			if (this.c > input.c) {
				if (this.left == null)
					this.left = input;
				else
					this.left.add(input);
			} else {
				if (this.right == null)
					this.right = input;
				else
					this.right.add(input);
			}
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", no=" + no + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (this.r == o.r)
				return this.c - o.c;
			return o.r - this.r;
		}
	}

	public static void main(String[] args) {
		int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
				{ 2, 2 } };
		int[][] result = solution(nodeinfo);
		for (int[] is : result) {
			for (int i : is) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
}
