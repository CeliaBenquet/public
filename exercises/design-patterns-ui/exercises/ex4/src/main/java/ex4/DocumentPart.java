package ex4;

public abstract class DocumentPart {

    private String text;

    public DocumentPart(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public abstract String toHTML(DocumentVisitor visitor);
    public abstract String toPlainText(DocumentVisitor visitor);
}
