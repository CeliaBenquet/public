package ex4;

public class DocumentVisitor {
    String toHTML(PlainText part) { return part.getText(); }
    String toHTML(HyperLink part) { return String.format("<a href=\"%s\">%s</a>", part.getURL(), part.getText()); }
    String toHTML(BoldText part) { return String.format("<b>%s</b>", part.getText()); }

    String toPlainText(PlainText part) { return part.getText(); }
    String toPlainText(HyperLink part) {return String.format("[%s](%s)", part.getText(), part.getURL());}
    String toPlainText(BoldText part) {  return String.format("**%s**", part.getText()); }
}
