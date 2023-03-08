import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        boolean symmetric = true;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
               symmetric = symmetric && (matrix[i][j] == matrix[j][i]);
            }
        }

        if (symmetric) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}