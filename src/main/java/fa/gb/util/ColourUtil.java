package fa.gb.util;

public class ColourUtil {
    /**
     * Reference https://stackoverflow.com/a/2464821
     */
    public static String generateRandomSeededColour(String seed) {
        int hashCode = seed.hashCode();
        return Integer.toHexString((hashCode >> 16) & 0xFF) +
            Integer.toHexString((hashCode >> 8) & 0xFF) +
            Integer.toHexString(hashCode & 0xFF);
    }
}
