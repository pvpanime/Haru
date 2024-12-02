package dev.nemi.haru.mapper;

import org.apache.ibatis.annotations.Select;

import java.time.Instant;

public interface TimeMapper {

  @Select("SELECT now()")
  Instant getTime();
}
