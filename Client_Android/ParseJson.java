import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.View;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseJson extends View {

    private static final String FILENAME = "src/text.json";

    /**
     * Creates a new <code>View</code> object.
     *
     * @param elem the <code>Element</code> to represent
     */
    public ParseJson(Element elem) {
        super(elem);
    }

    public static void parse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> map = mapper.readValue(new File(FILENAME),
                    new TypeReference<HashMap<String, Object>>(){});
            List<Integer> messages = (ArrayList<Integer>) map.get("Faces");
                for(int i = 0; i < messages.toArray().length; i+=4)
                {
                    System.out.println("X: " + messages.get(i));
                    System.out.println("Y: " + messages.get(i+1));
                    System.out.println("Width: " + messages.get(i+2));
                    System.out.println("Height: " + messages.get(i+3));
                }

        } catch (IOException ex) {
            Logger.getLogger(ParseJson.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public float getPreferredSpan(int axis) {
        return 0;
    }

    @Override
    public void paint(Graphics g, Shape allocation) {

    }

    @Override
    public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException {
        return null;
    }

    @Override
    public int viewToModel(float x, float y, Shape a, Position.Bias[] biasReturn) {
        return 0;
    }
}