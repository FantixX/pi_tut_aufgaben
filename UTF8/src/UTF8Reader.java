
//2144530 Abeler, Luis Hugo
//2153312 Leyh, Kilian
//2071216 Walch, Philipp
// Tutor: Hug Tobias
// PI1 Excercise I
import java.io.FileInputStream;
import java.io.IOException;

public class UTF8Reader {
    public static void main(String[] args) {
        try (var input = new FileInputStream("german.txt")) {
            int currentUnicode = 0;
            int expectContinuation = 0;
            for (var curr = input.read(); curr != -1; curr = input.read()) {
                int leadingBytes = curr >> 3;
                if (((leadingBytes >> 4) & 1) == 0) {
                    // System.out.println("1 Byte ASCII seq");
                    if (expectContinuation != 0) {
                        System.out.println("Invalid encoding.");
                        break;
                    }
                    expectContinuation = 0;
                    byte dataByte = (byte) curr;
                    currentUnicode = dataByte;
                } else if (((leadingBytes >> 3) & 1) == 0) {
                    // System.out.println("Continuation seq");
                    if (expectContinuation == 0) {
                        System.out.println("Invalid encoding.");
                        break;
                    }
                    byte dataByte = (byte) (curr & 0b00111111);
                    expectContinuation--;
                    currentUnicode = (currentUnicode << 6) | dataByte;
                } else if (((leadingBytes >> 2) & 1) == 0) {
                    // System.out.println("2 Byte UTF seq");
                    if (expectContinuation != 0) {
                        System.out.println("Invalid encoding.");
                        break;
                    }
                    expectContinuation = 1;
                    byte dataByte = (byte) (curr & 0b00011111);
                    currentUnicode = dataByte;
                } else if (((leadingBytes >> 1) & 1) == 0) {

                    // System.out.println("3 Byte seq");

                    if (expectContinuation != 0) {
                        System.out.println("Invalid encoding.");
                        break;
                    }
                    expectContinuation = 2;
                    byte dataByte = (byte) (curr & 0b00001111);
                    currentUnicode = dataByte;
                } else if (((leadingBytes) & 1) == 0) {
                    // System.out.println("4 Byte seq");
                    if (expectContinuation != 0) {
                        System.out.println("Invalid encoding.");
                        break;
                    }
                    expectContinuation = 3;
                    byte dataByte = (byte) (curr & 0b00000111);
                    currentUnicode = dataByte;
                } else {
                    System.out.println("Invalid enconding");
                    expectContinuation = 0;
                    break;
                }

                if (expectContinuation == 0) {
                    System.out.printf("U+%04X%n", currentUnicode);

                }

            }
            if (expectContinuation != 0) {
                System.out.println("Invalid encoding.");
            }

        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei");
        }

    }

}
