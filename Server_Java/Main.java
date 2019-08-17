import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//
// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
//
class DetectFaceDemo {
    public void run() throws JSONException, IOException {
        System.out.println("\nRunning DetectFaceDemo");

        // Create a face detector from the cascade file in the resources45
        // directory.
        CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("lbpcascade_frontalface.xml").getPath());


        // Detect faces in the image.
        // MatOfRect is a special container class for Rect.
        MatOfRect faceDetections = new MatOfRect();
        Mat image = Highgui.imread("/home/hunk/resources/123.jpg");

        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
        List<Integer> list = new ArrayList<Integer>();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Integer>> map = new HashMap<>();
        // Draw a bounding box around each face.;
        if(faceDetections.toArray().length == 0)
        {
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            map.put("Faces", list);
            try {
                mapper.writeValue(new File("user.json"), map);
            } catch (IOException ex) {
                Logger.getLogger(DetectFaceDemo.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        else {
            for (Rect rect : faceDetections.toArray()) {
                Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
                list.add(rect.x);
                list.add(rect.y);
                list.add(rect.width);
                list.add(rect.height);
                map.put("Faces", list);
                try {
                    mapper.writeValue(new File("user.json"), map);
                } catch (IOException ex) {
                    Logger.getLogger(DetectFaceDemo.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }

        // Save the visualized detection.
        String filename = "faceDetection.png";
        System.out.println(String.format("Writing %s", filename));
        Highgui.imwrite(filename, image);
    }

}

class HelloOpenCV {
    public void main() throws JSONException, IOException {
        System.out.println("Hello, OpenCV");

        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new DetectFaceDemo().run();
    }
}

