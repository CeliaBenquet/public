package ex4;

public class PlainText extends DocumentPart {

    public PlainText(String text) {
        super(text);
    }

    @Override
    public String toHTML(DocumentVisitor visitor) {
        return visitor.toHTML(this);
    }

    @Override
    public String toPlainText(DocumentVisitor visitor) {
        return visitor.toPlainText(this);
    }

}
