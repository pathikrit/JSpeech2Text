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

        System.out.println("Speak ...");
        while (true) {
            Result result = recognizer.recognize();
            String text = result.getBestFinalResultNoFiller();
            if (!text.isEmpty()) {
                System.out.println("Sending " + text);
                Twilio.sendSms("703 731 8860", text);
            }
        }
    }
}
