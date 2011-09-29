package sphinx.demo.helloworld;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 9/29/11
 * Time: 2:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class JSGFGrammarBuilder {
    private Set<String> keywords = Sets.newHashSet();

    public JSGFGrammarBuilder() {

    }

    public JSGFGrammarBuilder add(String key) {
        if (key != null && !key.isEmpty()) {
            keywords.add(key.toLowerCase());
        }
        return this;
    }

    public JSGFGrammarBuilder remove(String key) {
        if (key != null) {
            keywords.remove(key.toLowerCase());
        }
        return this;
    }

    public String getGrammar() {
        StringBuilder grammar = new StringBuilder( "grammar newjsgfg;\n public <command> = ");

        for(String key: keywords){
        if (key != null && !key.isEmpty())
            grammar.append("(").append(key.toLowerCase()).append( ") | ");
        }
        return grammar.deleteCharAt(grammar.length()-1).append(" ;").toString();
    }

}
