package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import regestrationTests.TestCase_1_ValidRegistrationByClick;
import regestrationTests.TestCase_2_ValidRegistrationPressEnterKey;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                TestCase_1_ValidRegistrationByClick.class,
                TestCase_2_ValidRegistrationPressEnterKey.class

        }
)
public class Smoke {



}
