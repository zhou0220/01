public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int n = input.length();
        int m = pattern.length();
        if (m > n) return -1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(input.charAt(i));
        }

        RollingString inputRoll = new RollingString(sb.toString(), m);
        RollingString patternRoll = new RollingString(pattern, m);

        int inputHash = inputRoll.hashCode();
        int patternHash = patternRoll.hashCode();
        for (int i = 0; i <= n - m; i++) {
            if (inputHash == patternHash) {
                if (inputRoll.equals(patternRoll)) return i;
            }
            if (i < n - m) {
                inputRoll.addChar(input.charAt(m + i));
                inputHash = inputRoll.hashCode();
            }
        }

        return -1;
    }

}
