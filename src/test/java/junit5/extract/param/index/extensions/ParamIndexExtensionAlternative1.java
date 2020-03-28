package junit5.extract.param.index.extensions;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamIndexExtensionAlternative1 implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        int index = extractIndex(context);
        System.out.println("index: " + index);
    }

    private int extractIndex(ExtensionContext context) {
        String patternString = "\\[(\\d+)\\]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(context.getDisplayName());
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        } else {
            return 0;
        }
    }
}
