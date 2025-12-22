package advance;

public class Modulus {
    public static void main(String[] args) {
        System.out.println(findM(50,22));
    }

    public static long findM(long A, long B) {
        long M = Math.abs(A - B);
        return (M > 1) ? M : -1;
    }

    static int modulusOfSum(int a, int b, int m) {
        return ((a % m) + (b % m)) % m;
    }

    static int modulusOfDifference(int a, int b, int m) {
        return ((a % m) - (b % m) + m) % m;
    }
    static int modulusOfProduct(int a, int b, int m) {
        return ((a % m) * (b % m)) % m;
    }

    public static int modPower(int a, int b, int m) {
        int result = 1;
        int base = a % m;

        for (int i = 0; i < b; i++) {
            result = (result * base) % m;
        }

        return result;
    }
}
