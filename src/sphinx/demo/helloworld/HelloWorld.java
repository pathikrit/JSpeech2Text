package sphinx.demo.helloworld;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class HelloWorld {

    public static void main(String[] args) {
        ConfigurationManager cm = new ConfigurationManager(HelloWorld.class.getResource("helloworld.config.xml"));

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            return;
        }

        System.out.println("Say: (Good morning | Hello) ( Bhiksha | Evandro | Paul | Philip | Rita | Will )");

        while (true) {
            System.out.println("Start speaking...");
            Result result = recognizer.recognize();
            if (result != null) {
                System.out.println("You said: " + result.getBestFinalResultNoFiller());
            } else {
                System.out.println("I can't hear what you said.");
            }
        }
    }
}
