package ex4;

public class HyperLink extends DocumentPart {

    private String URL;

    public HyperLink(String text, String URL) {
        super(text);
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    @Override
    public String toHTML(DocumentVisitor visitor) { return visitor.toHTML(this);}

    @Override
    public String toPlainText(DocumentVisitor visitor) { return visitor.toPlainText(this); }

}
