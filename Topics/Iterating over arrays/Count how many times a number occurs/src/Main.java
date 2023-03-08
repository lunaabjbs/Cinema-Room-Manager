import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int inputSize = scanner.nextInt();
        int[] nums = new int[inputSize];
        for (int i = 0; i < inputSize; i++) {
            nums[i] = scanner.nextInt();
        }
        int numToFind = scanner.nextInt();

        int occurances = 0;
        for (int num : nums) {
            if (num == numToFind) {
                occurances++;
            }
        }

        System.out.println(occurances);
    }
}