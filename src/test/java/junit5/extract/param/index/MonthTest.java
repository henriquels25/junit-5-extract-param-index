package junit5.extract.param.index;

import junit5.extract.param.index.extensions.ParamIndexExtensionAlternative2;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ParamIndexExtensionAlternative2.class)
public class MonthTest {

    @ParameterizedTest(name = "{index} {0} is 30 days long")
    @EnumSource(Month.class)
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }
}
