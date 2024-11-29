package dev.nemi.haru;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
@Qualifier("arona")
public class AronaDAO implements HaruDAO {

  private final DataSource dataSource;

  @Override
  public String getName() throws SQLException {
    @Cleanup Connection connection = dataSource.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("SELECT 'Arona' as `name`");
    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    return resultSet.getString("name");
  }

}
