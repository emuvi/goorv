package br.com.pointel.goorv.service.document;

import java.util.ArrayList;
import java.util.List;

public class Replacer {
    
    private final List<ReplacerItem> items = new ArrayList<>();

    public Replacer() {}
    
    public Replacer add(boolean regex, String sourceOf, String sourceTo) {
        items.add(new ReplacerItem(regex, sourceOf, sourceTo));
        return this;
    }
    
    public String replace(String source) {
        String result = source;
        for (var item : items) {
            if (item.regex) {
                result = result.replaceAll(item.sourceOf, item.sourceTo);
            } else {
                result = result.replace(item.sourceOf, item.sourceTo);
            }
        }
        return result;
    }
    
    private class ReplacerItem {
        
        public boolean regex;
        public String sourceOf;
        public String sourceTo;

        public ReplacerItem(boolean regex, String sourceOf, String sourceTo) {
            this.regex = regex;
            this.sourceOf = sourceOf;
            this.sourceTo = sourceTo;
        }
        
    }
    
}
