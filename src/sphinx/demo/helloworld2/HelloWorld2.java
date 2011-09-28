package sphinx.demo.helloworld2;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class HelloWorld2 {

    public static void main(String[] args) {
        ConfigurationManager cm = new ConfigurationManager(HelloWorld2.class.getResource("helloworld2.config.xml"));

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            return;
        }

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
