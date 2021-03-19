package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import regestrationTests.TestCase_1_ValidRegistrationByClick;
import regestrationTests.TestCase_2_ValidRegistrationPressEnterKey;
import regestrationTests.TestCase_3_CheckErrorMessage;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {


                TestCase_3_CheckErrorMessage.class,
                TestCase_2_ValidRegistrationPressEnterKey.class,
                TestCase_1_ValidRegistrationByClick.class
        }
)
public class Smoke {



}
