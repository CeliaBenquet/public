package ex4;

import java.util.LinkedList;
import java.util.List;

public class Document {

    private List<DocumentPart> parts;

    public Document() {
        parts = new LinkedList<>();
    }

    public void add(DocumentPart part) {
        parts.add(part);
    }

    public String toHTML() {
        StringBuffer result = new StringBuffer();
        DocumentVisitor visitor = new DocumentVisitor();
        for (DocumentPart part: parts) {
            result.append(part.toHTML(visitor));
        }
        return result.toString();
    }

    public String toPlainText() {
        StringBuffer result = new StringBuffer();
        DocumentVisitor visitor = new DocumentVisitor();
        for (DocumentPart part: parts) {
            result.append(part.toPlainText(visitor));
        }
        return result.toString();
    }

}
