package advance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeNumbers {
    public static void main(String[] args) {
        int N = 6;
        doorProblem(N);
        System.out.println("is 5 a prime number " + isPrimeNumber(5));
        countOfFactors(50);
        noOfPrimes(10);

        int n = 13;
        int[] factors = countFactors(n);

        for (int i = 1; i <= n; i++) {
            System.out.print(factors[i] + " ");
        }
        System.out.println();
        int N1 = 8;
        List<List<Integer>> result = primeFactors(N1);

        for (int i = 1; i <= N1; i++) {
            System.out.println(i + " -> " + result.get(i));
        }
        System.out.println("===========================");
        List<List<Integer>> result1 = spf(N1);

        for (int i = 1; i <= N1; i++) {
            System.out.println(i + " -> " + result1.get(i));
        }
        System.out.println("===========================");
        int[] result2 = spf2(N1);

        for (int i = 1; i <= N1; i++) {
            System.out.println(i + " -> " + result2[i]);
        }
    }


    private static void doorProblem(int N) {
        /*

    door problem
    Let there be N doors
    A person change the state of the door in the following manner:
    1,2,3...N
    2,4,6...N
    3,3,9...N
    4,8,12..N
    .
    .
    N
    Find the no of open doors at the end
    no of times state of the door i is changed = no of factors i
    for door to be opened no of times state changed = odd times
    No of open doors out of N doors = count of numbers that have odd factors
                                    = perfect squares bw 1 to N
     */
        int ans = (int) Math.floor(Math.sqrt(N));
        System.out.println("No of doors that are open at the end " + ans);
        // T(C) = O(N)
    }

    private static boolean isPrimeNumber(int num) {
        // T(c) = O(sqrt N)
        if (num == 1) return
                false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static void countOfFactors(int num) {
        int count = 0;

        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                if (i * i == num) {
                    count += 1;   // perfect square
                } else {
                    count += 2;   // pair of factors
                }
            }
        }

        System.out.println("No of factors are " + count);
    }

    private static void noOfPrimes(int n) {
        //T(c) = O(log(log(N))) = O(N)
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // important to mark initially all prime
        isPrime[0] = false;
        isPrime[1] = false;
        /*
          Iterate through the multiples of every prime no only ignore the non primes
         */
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) continue; // if no is already marked non-prime then skip it
            for (int j = i * i; j <= n; j = j + i) {
                isPrime[j] = false; //mark multiples of nos as non primes
            }
        }
        System.out.print("Primes are ");
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                System.out.print(" " + i + " ");
            }
        }
        System.out.println();
    }

    public static int[] countFactors(int N) {

        //S(c) = 1
        // T(c) = O(Nlog(N))

        int[] d = new int[N + 1]; // d[i] = number of factors of i

        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                d[j]++; // for each value(4) increment by the value(8) and add one factor
            }
        }

        return d;
    }

    private static void findPrimeFactors() {
        /*
        Given integer N , find all prime factors of the nos ranging from 1 to N
         */
    }


    private static void findSmallestPrimeFactors() {
        /*
        Given integer N , find the smallest prime factors of the nos ranging from 1 to N
         */

    }

    private static List<List<Integer>> primeFactors(int N) {
        //T(c) = O(log(log(N))) = O(N)

        List<List<Integer>> factors = new ArrayList<>();

        // initialize lists
        for (int i = 0; i <= N; i++) {
            factors.add(new ArrayList<>());
        }
        /*
        just like sieve since at every i the sub array is empty , that means initially consider
        all prime
        Next we check if the subarray is empty , if not then prime factors are already added so
        ignore them

        here we iterate thru j=i instead of i*i because we are not finding prime factors
        of only prime nos, we are finding prime factors of all nos so iterate thru all nos
         */
        // sieve-style factor filling
        for (int i = 2; i <= N; i++) {
            if (factors.get(i).isEmpty()) { // i is prime
                for (int j = i; j <= N; j += i) {
                    factors.get(j).add(i);
                }
            }
        }
        return factors;
    }

    private static List<List<Integer>> spf(int N) {
        //T(c) = O(log(log(N))) = O(N)

        List<List<Integer>> factors = new ArrayList<>();

        // initialize lists
        for (int i = 0; i <= N; i++) {
            factors.add(new ArrayList<>());
        }
        /*
        Since we need the smallest prime factor for every i ,
        so we made one additional check if for every sub array index j a prime factor is already
        added or not , if yes then skip else add the smallest prime factor
         */
        // sieve-style factor filling
        for (int i = 2; i <= N; i++) {
            if (factors.get(i).isEmpty()) {
                for (int j = i; j <= N; j += i) {
                    if (factors.get(j).isEmpty()) {
                        factors.get(j).add(i);
                    }
                }
            }
        }
        return factors;
    }

    private static int[] spf2(int n) {
        //T(c) = O(log(log(N))) = O(N)

        int[] spf = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            spf[i] = i;
        }

        for (int i = 2; i <= n; i++) {
            if (spf[i] == i) { //i is prime
                for (int j = i * i; j <= n; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
        return spf;
    }

}
