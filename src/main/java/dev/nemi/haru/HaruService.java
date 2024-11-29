package dev.nemi.haru;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@ToString
@RequiredArgsConstructor
public class HaruService {

  @Qualifier("arona")
  private final HaruDAO haruDAO;

  public String getName() throws SQLException {
    return haruDAO.getName();
  }
}
