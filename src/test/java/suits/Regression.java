package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import posts.CheckEditTitleOfPost;
import posts.CreateNewPostTest;
import posts.DeletePost;
import regestrationTests.*;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                DeletePost.class,
                CreateNewPostTest.class,
                CheckEditTitleOfPost.class,
                TestCase_7_CheckErrorMessageIfEmptyFieldByClick.class,
                TestCase_6_CheckPlaceHoldersAndTextOfRegisterForm.class,
                TestCase_5_ValidRegistrationByTabAndCheckBorderColor.class,
                TestCase_4_CheckErrorMessageIfEmptyFieldByTab.class,
                TestCase_3_CheckErrorMessage.class,
                TestCase_2_ValidRegistrationPressEnterKey.class,
                TestCase_1_ValidRegistrationByClick.class
        }
)
public class Regression {


}
