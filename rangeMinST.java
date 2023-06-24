import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class rangeMinST {
    static int[] tree;
    static int n;
    static int[] arr;

    public static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int left = 2 * node;
            int right = 2 * node + 1;
            int mid = (start + end) / 2;
            build(left, start, mid);
            build(right, mid + 1, end);
            tree[node] = Math.min(tree[left], tree[right]);
        }
    }

    public static void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            arr[start] = val;
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }
    }

    public static int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        } else if (left <= start && right >= end) {
            return tree[node];
        } else {
            int mid = (start + end) / 2;
            int leftMin = query(2 * node, start, mid, left, right);
            int rightMin = query(2 * node + 1, mid + 1, end, left, right);
            return Math.min(leftMin, rightMin);
        }
    }

    public static void main(String[] args) throws Exception {
         File file = new File(
            "C:\\Users\\Ritik\\Downloads\\test_input (2).txt");
 
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
        BufferedReader inp
            = new BufferedReader(new FileReader(file));
        String[] nqArr = inp.readLine().split(" ");//"C:\\Users\\Ritik\\Downloads\\test_input (2).txt"
        n = Integer.parseInt(nqArr[0]);
        int q = Integer.parseInt(nqArr[1]);

        String[] narr = inp.readLine().split(" ");
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(narr[i]);
        }

        tree = new int[4 * n];
        build(1, 0, n - 1);

        for (int i = 0; i < q; i++) {
            String[] queryArr = inp.readLine().split(" ");
            int qt = Integer.parseInt(queryArr[0]);

            if (qt == 1) {
                int k = Integer.parseInt(queryArr[1]) - 1;
                int u = Integer.parseInt(queryArr[2]);
                update(1, 0, n - 1, k, u);
            } else if (qt == 2) {
                int l = Integer.parseInt(queryArr[1]) - 1;
                int r = Integer.parseInt(queryArr[2]) - 1;
                int minVal = query(1, 0, n - 1, l, r);
                System.out.println(minVal);
            }
        }
    }
}
