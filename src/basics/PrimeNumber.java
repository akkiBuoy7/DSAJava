package basics;

public class PrimeNumber {
    public Boolean isPrime(Integer num) {
        if (num < 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(Double.valueOf(num)); i++) {
            if (num % i == 0) {
                return false;
            }

        }
        return true;
    }
}
