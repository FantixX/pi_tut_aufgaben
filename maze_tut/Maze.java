public class Maze {

    public static void main(String[] args) {
        System.out.println("You are in a maze.");

        printByte(makeCoord((byte) 0b10, (byte) 0b11));
        System.out.println(hasNExit((byte) 0b0001));

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

    static boolean hasNExit(byte coord) {
        byte y = getY(coord);
        byte x = getX(coord);
        if (y > 2) {
            System.out.println("false because y > 2");

            return false;
        }

        if (y == 1) {
            System.out.println("true because y == 1");
            return true;
        }

        if ((y & 1) == (x & 1)) {
            System.out.println("true because y & 1 == y >> 2 & 1");
            return true;
        }
        return false;
    }

}