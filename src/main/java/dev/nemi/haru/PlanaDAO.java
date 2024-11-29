package dev.nemi.haru;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
@Qualifier("plana")
public class PlanaDAO implements HaruDAO {

  private final DataSource dataSource;

  @Override
  public String getName() throws SQLException {
    @Cleanup Connection connection = dataSource.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("SELECT 'Plana' as `name`");
    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    return resultSet.getString("name");
  }

}
