import group.manager.Right;
//import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ExampleTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(ExampleTest.class);

  /*@Before
  public void loadLogger() {
    BasicConfigurator.configure();
  }*/

  @Test
  public void testRight() {
    Right right = new Right(Right::toString, Right::toString);
    try {
      right.loadInternalRights();
    } catch (IOException e) {
      e.printStackTrace();
    }

    right.add("ACCESS_TWITTER");
    right.add("TEST_DATA");
    LOGGER.info("======================================");

    right.add("SHOW_BLOG");
    LOGGER.info("======================================");

    right.delete("ACCESS_TWITTER");
    LOGGER.info("======================================");
  }
}
