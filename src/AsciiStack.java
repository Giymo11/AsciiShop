/**
 * Created by Giymo11 on 14.11.2014.
 */
public class AsciiStack {
    private AsciiImage[] images;
    private int increment;
    private int current = 0;

    public AsciiStack(int increment) {
        this.increment = increment;
        images = new AsciiImage[increment];
    }

    public int capacity() {
        return images.length;
    }

    public boolean empty() {
        return images[0] == null;
    }

    public AsciiImage peek() {
        return images[current];
    }

    public int size() {
        if (empty())
            return 0;
        else
            return current + 1;
    }

    public void push(AsciiImage image) {
        if (size() == capacity()) {
            AsciiImage[] newImages = new AsciiImage[capacity() + increment];
            for (int i = 0; i < images.length; ++i)
                newImages[i] = images[i];
            images = newImages;
        }
        if (!empty())
            ++current;
        images[current] = new AsciiImage(image);
    }

    public AsciiImage pop() {
        if (empty())
            return null;

        AsciiImage image = peek();
        images[current--] = null;
        if ((capacity() - size()) > increment) {
            AsciiImage[] newImages = new AsciiImage[capacity() - increment];
            for (int i = 0; i < newImages.length; ++i)
                newImages[i] = images[i];
            images = newImages;
        }
        return image;
    }
}
