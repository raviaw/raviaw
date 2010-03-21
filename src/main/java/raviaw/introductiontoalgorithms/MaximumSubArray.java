package raviaw.introductiontoalgorithms;
public class MaximumSubArray {
    public static void main(String[] args) {
        int A[] = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15 - 5, 7 };
        Result res = findMaxSubArray("", A, 0, A.length - 1);
        ArrayUtils.printArrayElements("all:", A);
        ArrayUtils.printArrayElements("sub (" + res + "): ", A, res.left, res.right + 1);
    }

    public static Result findMaxSubArray(String chain, int[] A, int low, int high) {
        System.out.println("findMaxSubArray([" + chain + "]" + ", low: " + low + ", high: " + high + ")");
        final String newChain = chain + "<low = " + low + ", high = " + high + ">";
        if (low == high)
            return new Result(low, high, A[low]);
        else {
            int mid = (low + high) / 2;
            Result left = findMaxSubArray(newChain, A, low, mid);
            Result right = findMaxSubArray(newChain, A, mid >= high ? high : mid + 1, high);
            Result cross = findMaxCrossingSubArray(A, low, mid, high);
            if (left.sum >= right.sum && left.sum >= cross.sum) {
                return left;
            } else if (right.sum >= left.sum && right.sum >= cross.sum) {
                return right;
            } else {
                return cross;
            }
        }
    }

    public static Result findMaxCrossingSubArray(int[] A, int low, int mid, int high) {
        int sum;

        sum = 0;
        int leftSum = Integer.MIN_VALUE;
        int maxLeft = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        int maxRight = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum = sum + A[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        Result res = new Result(maxLeft, maxRight, leftSum + rightSum);
        System.out.println("findMaxCrossingSubArray(low: " + low + ", mid: " + mid + ", high: " + high + ") = " + res);
        return res;
    }

    public static class Result {
        private final int left, right, sum;

        public Result(int left, int right, int sum) {
            super();
            this.left = left;
            this.right = right;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Result [left=" + left + ", right=" + right + ", sum=" + sum + "]";
        }

    }
}
