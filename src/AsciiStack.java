/**
 *
 * A stack able to represent the history of operations on an AsciiImage
 */
public class AsciiStack {

    private AsciiStackNode head;

    public AsciiStack() {
        head = null;
    }

    public boolean empty() {
        return head == null;
    }

    public AsciiImage peek() {
        if (empty())
            return null;
        return head.getImage();
    }

    public int size() {
        if (empty())
            return 0;
        return head.size();
    }

    public void push(AsciiImage image) {
        head = new AsciiStackNode(new AsciiImage(image), head);
    }

    public AsciiImage pop() {
        if (empty())
            return null;
        AsciiStackNode popped = head;
        head = head.getNext();
        return popped.getImage();
    }

    private class AsciiStackNode {
        private AsciiImage image;
        private AsciiStackNode next;

        public AsciiStackNode(AsciiImage image, AsciiStackNode next) {
            this.image = image;
            this.next = next;
        }

        public AsciiImage getImage() {
            return image;
        }

        public AsciiStackNode getNext() {
            return next;
        }

        public int size() {
            if (next == null)
                return 1;

            return 1 + next.size();
        }
    }
}
