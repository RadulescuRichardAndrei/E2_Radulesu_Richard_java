public class MyRandom {

    public static int generateSize() {
        return (int) ((Math.random() + 0.5) * 20);
    }

    public static int generateCost() {
        return (int) ((Math.random() + 0.1) * 50);
    }

    public static int generateRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String generateString() {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int pos = (int) (Math.random() * 26) - 1;
            if (pos < 0) break;
            sb.append((char) (97 + pos));
        }
        return sb.toString();
    }

    public static String generateIp() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append((char) ('0' + (int) (Math.random() * 10)));
            }
            sb.append('.');
        }
        sb.append((char) ('0' + (int) (Math.random() * 10)));
        sb.append('.');
        sb.append((char) ('0' + (int) (Math.random() * 10)));
        return sb.toString();
    }

}
