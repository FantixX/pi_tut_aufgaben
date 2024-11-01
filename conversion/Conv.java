public class Conv {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Falsche Anzahl Argumente");
            return;
        }
        String input = args[0];
        long inputBase = Long.parseLong(args[1]);
        long outputBase = Long.parseLong(args[2]);

        long stringLong = stringToLong(input, inputBase);
        String output = longToString(stringLong, outputBase);
        System.out.println(output);
        System.out.printf("Base %d Output: %s", outputBase, output);
    }

    static long charToLong(char c) {
        long code = (long) c;

        if (code >= 65 && code <= 90) {
            return code - 55;
        }

        if (code >= 97 && code <= 122) {
            return code - 87;
        }

        if (code >= 48 && code <= 58) {
            return code - 48;
        }
        return -1;
    }

    static char longToChar(long l) {
        if (l >= 0 && l <= 9) {
            return ((char) (l + 48));
        }

        if (l >= 10 && l <= 35) {
            return ((char) (l + 87));
        }

        return '0';
    }

    static long stringToLong(String s, long base) {
        long result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long code = charToLong(c);
            long exponent = Math.abs(i - s.length() + 1);
            result += code * Math.pow(16, exponent);
        }

        return result;
    }

    static String longToString(long num, long base) {
        StringBuilder string = new StringBuilder();
        long prev = num;
        while (prev > 0) {
            long remainder = prev % base;
            prev = prev / base;
            string.append(longToChar(remainder));
        }
        return string.reverse().toString();
    }

}
