package practice;

public class MissingNumber {


    public static int MissingNnumberPigeonSort(int[] input){
        int n= input.length;
        boolean foundN=false;
        int index=0;
        while (index<n) {
            if (input[index]==n){
                foundN = true;
                index++;
                continue;
            } else if (input[index]==index) {
                index ++;
                continue;
            } else {
                int temp = input[index];
                input[index]= input[temp];
                input[temp]=temp;
            }
        }

        if (!foundN){
            return n;
        }
        for (int i=0; i<n; i++){
            if (input[i]!=i){
                return i;
            }
        }
        return -1;
    }

    /**
     * A number XOR itself equals to ZERO.
     */
    public static int MissingNumberXOR(int[] input){
        int start = input.length;
        for (int i=0; i<input.length; i++){
            start^=i;
            start^=input[i];
        }
        return start;
    }

    public static void main(String[] args) {
        int[] inputs;
        inputs= new int[]{1, 2, 3};
        System.out.println(MissingNumberXOR(inputs));
        System.out.println(MissingNnumberPigeonSort(inputs));

        inputs= new int[]{0, 1, 3};
        System.out.println(MissingNumberXOR(inputs));
        System.out.println(MissingNnumberPigeonSort(inputs));

        inputs= new int[]{0, 1, 2};
        System.out.println(MissingNumberXOR(inputs));
        System.out.println(MissingNnumberPigeonSort(inputs));



    }
}
