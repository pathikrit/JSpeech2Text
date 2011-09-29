package sphinx.demo.helloworld;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.jsgf.JSGFGrammar;
import edu.cmu.sphinx.jsgf.JSGFGrammarException;
import edu.cmu.sphinx.jsgf.JSGFGrammarParseException;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class HelloWorld {


    public static void main(String[] args) throws AWTException {
        ConfigurationManager cm = new ConfigurationManager(HelloWorld.class.getResource("helloworld.config.xml"));

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        Microphone microphone = (Microphone) cm.lookup("microphone");

        JSGFGrammar jsgfGrammarManager = (JSGFGrammar) cm.lookup("jsgfGrammar");

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
                ///Twilio.sendSms("817 715 5168", text);

                if (text.equals("contacts")) {
                    System.out.println("changing grammar!");

                    try {
                        JSGFGrammarBuilder grammar = new JSGFGrammarBuilder();
                        grammar = grammar.add("up").add("left right").add("down");

                        // Create tempfile temp.gram out of string grammar.getGrammar()
                        // create URL to the directory where tempfile is and set it below
                        // accordingly
                        URL url = new URL("folder of the temp file");
                        jsgfGrammarManager.setBaseURL(url);
                        jsgfGrammarManager.loadJSGF("temp");

                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (JSGFGrammarParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (JSGFGrammarException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    //dumpSampleSentences();
                    //recognizeAndReport();
                }
            }
        }
    }


    /*
    URL url = JSGFDemo.class.getResource("jsgf.config.xml");
    ConfigurationManager cm = new ConfigurationManager(url);
    jsgfGrammarManager = (JSGFGrammar) cm.lookup("jsgfGrammar");


Use the JSGFGrammar component to load new JSGF Grammars - The new JSGF gramamrs can be loaded via the loadJSGF method. An example of how this is done can be found in the 'loadAndRecognize' method.

 private void loadAndRecognize(String grammarName) throws IOException, GrammarException  {
    jsgfGrammarManager.loadJSGF(grammarName);
    dumpSampleSentences(grammarName);
    recognizeAndReport();
 }


*/
}
