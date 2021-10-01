package CinemaRoomManager;

import java.util.Scanner;

class Main {
    static private final Scanner SCANNER = new Scanner(System.in);
    static private char[][] seatingArrangement;
    static private int numberPurchasedTickets;
    static int currentIncome;

    public static void main(String[] args) {
        setCinemaRoomSize();
        setSeatingArrangement();
        mainMenu();
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            switch (SCANNER.nextInt()) {
                case 1:
                    printSeatingArrangement();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    printStatistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    static void setCinemaRoomSize() {
        System.out.println("\nEnter the number of rows:");
        seatingArrangement = new char[SCANNER.nextInt()][];
        System.out.println("Enter the number of seats in each row:");
        seatingArrangement = new char[seatingArrangement.length][SCANNER.nextInt()];
    }

    static void setSeatingArrangement() {
        for (int i = 0; i < seatingArrangement.length; i++) {
            for (int j = 0; j <seatingArrangement[0].length; j++) {
                seatingArrangement[i][j] = 'S';
            }
        }
    }

    static void printSeatingArrangement() {
        System.out.print("\nCinema:\n  ");
        for (int i = 1; i <= seatingArrangement[0].length; i++) {
            System.out.print(i == seatingArrangement[0].length + 1 ? i + "\n" : i + " ");
        }
        for (int i = 0; i < seatingArrangement.length; i++) {
            System.out.print(i == 0 ? "\n" + (i + 1) + " " : i + 1 + " ");
            for (int j = 0; j < seatingArrangement[0].length; j++) {
                System.out.print(j == seatingArrangement[0].length - 1 ? seatingArrangement[i][j] + "\n"
                                                                       : seatingArrangement[i][j] + " ");
            }
        }
    }

    static int getTicketPrice(int rowNumber) {
        if (seatingArrangement.length * seatingArrangement[0].length > 60) {
            return rowNumber > seatingArrangement.length / 2 ? 8 : 10;
        } else {
            return 10;
        }
    }

    static void buyTicket() {
        while (true) {
            System.out.println("\nEnter a row number:");
            int rowNumber = SCANNER.nextInt() - 1;
            System.out.println("Enter a seat number in that row:");
            int seatNumber = SCANNER.nextInt() - 1;

            if (rowNumber < seatingArrangement.length && seatNumber < seatingArrangement[0].length) {
                if (seatingArrangement[rowNumber][seatNumber] != 'B') {
                    System.out.printf("\nTicket price: $%d\n", getTicketPrice(rowNumber + 1));
                    seatingArrangement[rowNumber][seatNumber] = 'B';
                    numberPurchasedTickets++;
                    currentIncome += getTicketPrice(rowNumber + 1);
                    return;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } else System.out.println("Wrong input!");
        }
    }

    static int getProfitFromSoldTickets() {
        if (seatingArrangement.length * seatingArrangement[0].length > 60) {
            return seatingArrangement.length / 2 * seatingArrangement[0].length * 10
                    + (seatingArrangement.length / 2 + seatingArrangement.length % 2) * seatingArrangement[0].length * 8;
        } else {
            return seatingArrangement.length * seatingArrangement[0].length * 10;
        }
    }

    static void printStatistics() {
        double percentage = (double) numberPurchasedTickets / (seatingArrangement.length *  seatingArrangement[0].length) * 100;
        System.out.printf("\nNumber of purchased tickets: %d\nPercentage: %.2f%%\nCurrent income: $%d\nTotal income: $%d\n",
                numberPurchasedTickets, percentage, currentIncome, getProfitFromSoldTickets());
    }
}


