package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import regestrationTests.TestCase_1_ValidRegistrationByClick;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                TestCase_1_ValidRegistrationByClick.class

        }
)
public class Smoke {



}
