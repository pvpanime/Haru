package dev.nemi.haru.board;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Qualifier("BoardDAO")
public class BoardDAO implements UseBoardDAO {

  public final DataSource dataSource;

  @Override
  public BoardVO getById(long id) throws SQLException {
    @Cleanup Connection conn = dataSource.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "SELECT * FROM Board WHERE id = ?"
    );
    @Cleanup ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
      return BoardVO.builder()
        .boardId(rs.getLong("id"))
        .title(rs.getString("title"))
        .content(rs.getString("content"))
        .added(rs.getTimestamp("added").toInstant())
        .updated(rs.getTimestamp("updated").toInstant())
        .userId(rs.getString("userid"))
        .build();
    }
    return null;
  }

  @Override
  public List<BoardVO> getListAt(long start, long offset) throws SQLException {
    ArrayList<BoardVO> list = new ArrayList<>();
    @Cleanup Connection conn = dataSource.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "SELECT * FROM Board ORDER BY added DESC LIMIT ? OFFSET ?"
    );
    @Cleanup ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      list.add(BoardVO.builder()
        .boardId(rs.getLong("id"))
        .title(rs.getString("title"))
        .content(rs.getString("content"))
        .added(rs.getTimestamp("added").toInstant())
        .updated(rs.getTimestamp("updated").toInstant())
        .userId(rs.getString("userid"))
        .build());
    }
    return list;
  }

  @Override
  public void update(BoardVO board) throws SQLException {
    @Cleanup Connection conn = dataSource.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      ""
    );
  }

  @Override
  public void delete(long id) throws SQLException {
    @Cleanup Connection conn = dataSource.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      ""
    );
  }
}
