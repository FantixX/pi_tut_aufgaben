//2144530 Abeler, Luis Hugo
//2153312 Leyh, Kilian
//2071216 Walch, Philipp 
// Tutor: Hug Tobias
// PI1 Excercise F

import java.util.Scanner;

public class Maze {

    public static void main(String[] args) {
        boolean track;
        if (args.length > 0) {
            track = true;
        } else {
            track = false;
        }

        byte coord = makeCoord((byte) 0, (byte) 0);
        byte goal = makeCoord((byte) 3, (byte) 3);
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are in a maze.");
        while (coord != goal) {
            if (track) {
                System.out.println("Current coordinates: " + formatCoord(coord));
            }
            System.out.println("Possible exits:");
            if (hasNExit(coord)) {
                System.out.println("north");
            }
            if (hasSExit(coord)) {
                System.out.println("south");
            }
            if (hasEExit(coord)) {
                System.out.println("east");
            }
            if (hasWExit(coord)) {
                System.out.println("west");
            }

            String input = scanner.nextLine().toLowerCase();

            if (input.equals("n")) {
                coord = goN(coord);
            } else if (input.equals("s")) {
                coord = goS(coord);
            } else if (input.equals("e")) {
                coord = goE(coord);
            } else if (input.equals("w")) {
                coord = goW(coord);
            } else if (input.equals("x") || input.equals("exit")) {
                System.out.println("You loose");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid input");
            }

        }
        System.out.println("You win!");
        scanner.close();

    }

    static byte getX(byte coord) {
        byte x = (byte) (0b0011 & coord);
        return x;
    }

    static byte getY(byte coord) {
        byte x = (byte) (coord >> 2);
        return x;
    }

    static byte makeCoord(byte x, byte y) {
        byte coord = y;
        coord = (byte) (coord << 2);
        coord = (byte) (coord | x);

        return coord;

    }

    static void printByte(byte b) {
        for (int i = 7; i >= 0; i--) {
            System.out.print((b >> i) & 1);
            if (i % 4 == 0) {
                System.out.print(" ");
            }

        }
        System.out.println();
    }

    static String formatCoord(byte coord) {
        return "(" + getX(coord) + ", " + getY(coord) + ")";

    }

    static boolean hasNExit(byte coord) {
        byte y = getY(coord);
        byte x = getX(coord);
        if (y > 2) {

            return false;
        }

        if (y == 1) {
            return true;
        }

        if ((y & 1) == (x & 1)) {

            return true;
        }
        return false;
    }

    static boolean hasSExit(byte coord) {

        byte y = getY(coord);
        byte x = getX(coord);

        if (y == 2) {

            return true;

        } else if ((y & 1) != (x & 1)) {
            return true;
        } else {
            return false;
        }
    }

    static boolean hasEExit(byte coord) {
        byte x = getX(coord);
        byte y = getY(coord);

        if (x >= 3) {
            return false;
        }

        if ((y & 1) != (x & 1)) {
            return true;
        } else {
            return false;
        }
    }

    static boolean hasWExit(byte coord) {
        byte x = getX(coord);
        byte y = getY(coord);

        if (x <= 0) {
            return false;
        }

        if ((y & 1) == (x & 1)) {
            return true;
        } else {
            return false;
        }
    }

    static byte goN(byte coord) {

        byte y = getY(coord);
        byte x = getX(coord);

        if (hasNExit(coord)) {
            y++;
        }

        return makeCoord(x, y);
    }

    static byte goS(byte coord) {
        byte y = getY(coord);
        byte x = getX(coord);

        if (hasSExit(coord)) {
            y--;
        }

        return makeCoord(x, y);
    }

    static byte goE(byte coord) {
        byte y = getY(coord);
        byte x = getX(coord);

        if (hasEExit(coord)) {
            x++;
        }

        return makeCoord(x, y);
    }

    static byte goW(byte coord) {
        byte y = getY(coord);
        byte x = getX(coord);

        if (hasWExit(coord)) {
            x--;
        }

        return makeCoord(x, y);
    }

}