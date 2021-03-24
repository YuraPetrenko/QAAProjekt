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
                TestCase_1_ValidRegistrationByClick.class
        }
)
public class Smoke {


}
