package cinema;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cinema {

    private static final int SMALL_THEATRE_TICKET_PRICE = 10;
    private static final int LARGE_THEATRE_FRONT_PRICE = 10;
    private static final int LARGE_THEATRE_BACK_PRICE = 8;
    private static final int MAXIMUM_SEATS_IN_SMALL_THEATRE = 60;

    private static int numberOfTicketsPurchased = 0;
    private static int currentIncome = 0;

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int numColumns = scanner.nextInt();

        char[][] cinema = createCinema(numRows, numColumns);

        int userInput = -1;
        while(userInput != 0) {
            printMainMenu();
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    buyTicket(scanner, cinema);
                    break;
                case 3:
                    showStatistics(numRows, numColumns);
                case 0:
                    break;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private static void buyTicket(Scanner scanner, char[][] cinema) {
        boolean validInput = false;
        int seatRow, seatRowIndex, seatCol, seatColIndex;
        do {
            System.out.println("Enter a row number:");
            seatRow = scanner.nextInt();
            seatRowIndex = seatRow - 1;

            System.out.println("Enter a seat number in that row:");
            seatCol = scanner.nextInt();
            seatColIndex = seatCol - 1;

            if (seatRowIndex < 0 || seatRowIndex >= cinema.length
                    || seatColIndex < 0 || seatColIndex >= cinema[0].length) {
                System.out.println("Wrong input!");
            } else if (cinema[seatRowIndex][seatColIndex] == 'B'){
                System.out.println("That ticket has already been purchased!");
            } else {
                validInput = true;
            }
        } while (!validInput);


        int ticketPrice = calculateSeatPrice(cinema.length, cinema[0].length, seatRow);
        numberOfTicketsPurchased++;
        currentIncome += ticketPrice;
        System.out.println("Ticket price: $" + ticketPrice);

        cinema[seatRowIndex][seatColIndex] = 'B';
        printCinema(cinema);
    }

    private static void showStatistics(int numRows, int numCols) {
        System.out.println("Number of purchased tickets: " + numberOfTicketsPurchased);
        System.out.printf("Percentage %.2f%%\n", 100 * numberOfTicketsPurchased / (double) (numRows * numCols));
        System.out.println("Current income: $" + currentIncome);

        int totalIncome = calculateIncome(numRows, numCols);
        System.out.println("Total income: $" + totalIncome);
    }

    private static int calculateSeatPrice(int numRows, int numColumns, int seatRow) {
        int ticketPrice;
        int totalNumberOfSeats = numRows * numColumns;
        if (totalNumberOfSeats <= MAXIMUM_SEATS_IN_SMALL_THEATRE) {
            ticketPrice = SMALL_THEATRE_TICKET_PRICE;
        } else {
            if (seatRow <= numRows / 2) {
                ticketPrice = LARGE_THEATRE_FRONT_PRICE;
            } else {
                ticketPrice = LARGE_THEATRE_BACK_PRICE;
            }
        }

        return ticketPrice;
    }

    private static int calculateIncome(int numRows, int numColumns) {
        int income;
        int totalNumberOfSeats = numRows * numColumns;
        if (totalNumberOfSeats <= MAXIMUM_SEATS_IN_SMALL_THEATRE) {
            income = calculateIncomeForSeats(SMALL_THEATRE_TICKET_PRICE, totalNumberOfSeats);
        } else {
            income = calculateIncomeForLargeTheatre(numRows, numColumns);
        }
        return income;
    }

    private static int calculateIncomeForLargeTheatre(int numRows, int numColumns) {
        int half = numRows / 2;
        int frontNumberOfSeats = half * numColumns;
        int backNumberOfSeats = (numRows - half) * numColumns;
        return calculateIncomeForSeats(LARGE_THEATRE_FRONT_PRICE, frontNumberOfSeats)
                + calculateIncomeForSeats(LARGE_THEATRE_BACK_PRICE, backNumberOfSeats);
    }

    private static int calculateIncomeForSeats(int ticketPrice, int numSeats) {
        return ticketPrice * numSeats;
    }

    private static char[][] createCinema(int numRows, int numColumns) {
        char[][] cinema = new char[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                cinema[i][j] = 'S';
            }
        }

        return cinema;
    }

    private static void printCinema(char[][] cinema) {
        System.out.println("Cinema:");
        printColumnIndices(cinema[0].length);
        printRowsOfSeats(cinema);
        System.out.println();
    }

    private static void printColumnIndices(int numColumns) {
        String indices = IntStream.range(1, numColumns + 1)
                .mapToObj(String::valueOf).collect(Collectors.joining(" "));
        String formattedColumnIndices = "  " + indices;
        System.out.println(formattedColumnIndices);
    }

    private static void printRowsOfSeats(char[][] cinema) {
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }

    }
}