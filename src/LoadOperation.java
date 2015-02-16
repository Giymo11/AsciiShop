import java.util.Scanner;

/**
 * Created by Giymo11 on 16.02.2015.
 */
public class LoadOperation implements Operation {

    private String data;

    public LoadOperation(String newImage) {
        data = newImage;
    }

    @Override
    public AsciiImage execute(AsciiImage img) throws OperationException {
        Scanner scanner = new Scanner(data);
        int lineCount = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.length() == 0)
                break;

            if (line.length() != img.getWidth()) {
                System.out.println("should be " + img.getWidth() + " but was " + line.length());
                throw new OperationException();
            }
            try {
                for (int i = 0; i < line.length(); ++i) {
                    img.setPixel(i, lineCount, line.charAt(i));
                }
                ++lineCount;
            } catch (Exception ex) {
                throw new OperationException();
            }
        }

        if (lineCount != img.getHeight()) {
            //System.out.println("should be " + img.getHeight() + " but was " + lineCount);
            throw new OperationException();
        }

        return img;
    }
}
