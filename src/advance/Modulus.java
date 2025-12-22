package advance;

public class Modulus {
    public static void main(String[] args) {
        System.out.println(findM(50, 22));

        System.out.println(fastPowerTheorem(3, 11, 20));

        int a = 7, b = 3, m = 10;
        System.out.println(modDivide(a, b, m));

        // when m is prime
        int a1 = 7, b1 = 3, p = 11;
        System.out.println(modDivideFermat(a1, b1, p));
    }

    public static long findM(long A, long B) {
        /*
        a % m = b % m
        where m = |a-b|
         */
        long M = Math.abs(A - B);
        return (M > 1) ? M : -1;
    }

    static int modulusOfSum(int a, int b, int m) {
        /*
        (a + b)%m = (a%m + b%m) %m
         */

        return ((a % m) + (b % m)) % m;
    }

    static int modulusOfDifference(int a, int b, int m) {
         /*
        (a - b)%m = (a%m - b%m +m) %m
         */
        return ((a % m) - (b % m) + m) % m;
    }

    static int modulusOfProduct(int a, int b, int m) {
        /*
        (a * b)%m = (a%m * b%m) %m
         */
        return ((a % m) * (b % m)) % m;
    }

    public static int modPower(int a, int b, int m) {
        /*
        (a^b)%m = (a % m)^b %m
         */

        // T(C) = O(b)
        int result = 1;
        int base = a % m;

        for (int i = 0; i < b; i++) {
            result = (result * base) % m; // need to module at each step
        }

        return result;
    }

    public static int fastPowerTheorem(int a, int m, int b) {
        /*
        (a^b)%m = (a^2 % m)^b/2 %m  if b is even
                = a * (a^2 % m)^b/2 %m  if b is odd
         if b is odd in integer division
         b/2 ~= (b-1)/2

         T(c) = log2(b)
         */

        int result = 1;
        int base = a % m;   // THIS is the base

        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * base) % m; // a * (a^2 % m)^b/2 %m
            }
            base = (base * base) % m; // (a^2 % m)^b/2 %m
            b = b / 2;
        }
        return result;

    }

    // Euclidean algorithm for gcd
    // gcd(a,b)=gcd(b,a % b)
    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Brute-force modular inverse with T(c) = O(m)
    static int modInverseBrute(int b, int m) {
        for (int x = 1; x < m; x++) {
            if ((b * x) % m == 1) {
                return x;
            }
        }
        throw new ArithmeticException("Modular inverse does not exist");
    }

    // (a / b) % m
    static int modDivide(int a, int b, int m) {
        if (gcd(b, m) != 1) {
            throw new ArithmeticException("Division not defined (gcd != 1)");
        }

        int bInverse = modInverseBrute(b, m);
        return (a % m * bInverse) % m;
    }

    // Fermat modular inverse with T(c) =  O(logm)
    static int modInverseFermat(int base, int exp, int mod) {
        // Fast power method
        int result = 1;
        base %= mod;

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp = exp / 2;
        }
        return result;
    }

    // (a / b) % m using Fermat's theorem
    static int modDivideFermat(int a, int b, int m) {
        if (gcd(b, m) != 1) {
            throw new ArithmeticException("Division not defined");
        }
        // m must be prime
        int bInverse = modInverseFermat(b, m - 2, m);
        return (a % m * bInverse) % m;
    }
}
