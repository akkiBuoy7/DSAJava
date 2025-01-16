package basics;

public class Fibonacci {
   public void getFibonacci(Integer counter){
        System.out.print("Fibonacci series up to "+counter+": ");
        if(counter<1){
            System.out.print(counter);
        }
        Integer fibo1 = 0;
        Integer fibo2 = 1;
        while (counter>0){
            Integer temp = fibo1+fibo2;
            System.out.print(fibo1+" ");
            fibo2 = fibo1;
            fibo1 = temp;
            counter--;
        }
    }
}
