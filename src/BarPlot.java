import java.util.Scanner;

/**
 * Created by Benjamin Potzmann on 2014-10-13.
 *
 * Generates horizontal BarPlots.
 */
public class BarPlot {

    private static final int BAR_LENGTH = 30;
    private static final int LABEL_LENGTH = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String label = scanner.next();
            if (scanner.hasNextInt()) {                              // Check if we get the value as an integer
                int length = scanner.nextInt();
                if (length > BAR_LENGTH || length < 0) {            // Check if input is from 0 - 30
                    System.out.println("INPUT ERROR");
                    return;
                } else
                    System.out.println(drawBar(label, length));
            } else if (scanner.hasNextDouble()) {                    // Check if we get the value as an double
                double length = scanner.nextDouble();
                if (length > 1.0 || length < 0) {                   // Check if input is from 0 - 1
                    System.out.println("INPUT ERROR");
                    return;
                } else
                    System.out.println(drawBar(label, length));
            } else {                                                // invalid input
                System.out.println("INPUT ERROR");
                return;
            }
        }
    }

    /**
     * Repeats a character n times
     *
     * @param c The character to repeat
     * @param n The number of times to repeat
     * @return A String with n times the character c
     */
    public static String repeat(char c, int n) {
        StringBuilder builder = new StringBuilder(BAR_LENGTH);

        for(int count = 0; count < n; ++count)
            builder.append(c);

        return builder.toString();
    }

    /**
     * Expands or trims a label to a fixed size
     *
     * @param label The label
     * @param n The length to expand/trim the label to
     * @return The label as a string
     */
    public static String drawLabel(String label, int n) {
        StringBuilder builder = new StringBuilder(n);

        for(int count = 0; count < n; ++count) {
            if(count < label.length())
                builder.append(label.charAt(count));
            else
                builder.append(' ');
        }

        return builder.toString();
    }

    /**
     * Draws a Bar
     *
     * @param label The label of the bar
     * @param value The absolute value of the bar
     * @return The finished bar as a string
     */
    public static String drawBar(String label, int value) {
        return drawLabel(label, LABEL_LENGTH) + '|' + repeat('#', value) + repeat(' ', BAR_LENGTH - value) + '|';
    }
    /**
     * Draws a Bar
     *
     * @param label The label of the bar
     * @param value The percentage value of the bar
     * @return The finished bar as a string
     */
    public static String drawBar(String label, double value) {
        return drawBar(label, (int)Math.round(value * BAR_LENGTH));
    }
}
