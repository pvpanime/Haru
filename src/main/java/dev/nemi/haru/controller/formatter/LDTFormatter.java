package dev.nemi.haru.controller.formatter;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LDTFormatter implements Formatter<LocalDateTime> {

  @Override
  public @NotNull LocalDateTime parse(@NotNull String text, @NotNull Locale locale) {
    return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm[:ss]", locale));
  }

  @Override
  public @NotNull String print(@NotNull LocalDateTime object, @NotNull Locale locale) {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm", locale).format(object);
  }

}
