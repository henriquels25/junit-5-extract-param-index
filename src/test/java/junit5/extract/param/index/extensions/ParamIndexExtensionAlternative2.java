package junit5.extract.param.index.extensions;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.engine.descriptor.TestTemplateInvocationTestDescriptor;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ParamIndexExtensionAlternative2 implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        int index = extractIndex(context);
        System.out.println("index: " + index);
    }

    private int extractIndex(ExtensionContext context) {
        Method method = ReflectionUtils.findMethod(context.getClass(),
                "getTestDescriptor").orElse(null);
        TestTemplateInvocationTestDescriptor descriptor =
                (TestTemplateInvocationTestDescriptor)
                        ReflectionUtils.invokeMethod(method, context);

        try {
            Field indexField = descriptor.getClass().getDeclaredField("index");
            indexField.setAccessible(true);
            return Integer.parseInt(indexField.get(descriptor).toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
