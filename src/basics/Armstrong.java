package basics;

public class Armstrong {
    public boolean isArmstrong(Integer num){
        int total = 0;
        int temp = num;
        int digits = String.valueOf(num).length();
        if (num<=0){
            return false;
        }
        while (num>0){
            Integer reminder = num%10;
            total += Math.pow(reminder,digits);
            num = num/10;
        }
        if (total==temp){
            return true;
        }
        return false;
    }
}
