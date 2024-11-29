import dev.nemi.haru.HaruService;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring.xml")
public class HaruFirstTest {

  @Autowired
  private HaruService haruService;

  @Autowired
  private DataSource dataSource;

  @Test
  public void beanTest() {
    log.info(haruService);
    Assertions.assertNotNull(haruService);
  }

  @Test
  public void hikariTest() throws SQLException {
    @Cleanup Connection connection = dataSource.getConnection();
    log.info(connection);
    Assertions.assertNotNull(connection);

    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("SHOW tables");
    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      log.info(resultSet.getObject(1));
    }

  }

  @Test
  public void serviceWorkingTest() throws SQLException {
    String name = haruService.getName();
    log.info(name);
  }
}
