package me.oragejuice.commandDispatcher;

public class DamerauLevenshtein {

    /**
     * https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance
     * @param a The source String.
     * @param b The target String.
     * @return The distance between source and target strings.
     */
    public static int calculate(CharSequence a, CharSequence b) {
        int sourceLength = a.length();
        int targetLength = b.length();
        if (sourceLength == 0) return targetLength;
        if (targetLength == 0) return sourceLength;
        int[][] dist = new int[sourceLength + 1][targetLength + 1];
        for (int i = 0; i < sourceLength + 1; i++) {
            dist[i][0] = i;
        }
        for (int j = 0; j < targetLength + 1; j++) {
            dist[0][j] = j;
        }
        for (int i = 1; i < sourceLength + 1; i++) {
            for (int j = 1; j < targetLength + 1; j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                dist[i][j] = Math.min(Math.min(dist[i - 1][j] + 1, dist[i][j - 1] + 1), dist[i - 1][j - 1] + cost);
                if (i > 1 &&
                        j > 1 &&
                        a.charAt(i - 1) == b.charAt(j - 2) &&
                        a.charAt(i - 2) == b.charAt(j - 1)) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 2][j - 2] + cost);
                }
            }
        }
        return dist[sourceLength][targetLength];
    }

}
