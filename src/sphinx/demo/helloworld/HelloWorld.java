package sphinx.demo.helloworld;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

import java.awt.*;

public class HelloWorld {

    public static void main(String[] args) throws AWTException {
        ConfigurationManager cm = new ConfigurationManager(HelloWorld.class.getResource("helloworld.config.xml"));

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            return;
        }

        Robot r = new Robot();
        final int DELTA = 50;

        while (true) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            Result result = recognizer.recognize();
            if (result != null) {
                // System.out.println("You said: " + result.getBestFinalResultNoFiller() + "[" + result.getBestPronunciationResult() + "]");
                String text = result.getBestFinalResultNoFiller();
                if (text.equals("up")) {
                    r.mouseMove(p.x, p.y - DELTA);
                } else if (text.equals("down")) {
                    r.mouseMove(p.x, p.y + DELTA);
                } else if (text.equals("left")) {
                    r.mouseMove(p.x - DELTA, p.y);
                } else if (text.equals("right")) {
                    r.mouseMove(p.x + DELTA, p.y);
                }
            }
        }
    }
}
