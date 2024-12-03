package dev.nemi.haru.controller.formatter;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.Formatter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InstantFormatter implements Formatter<Instant> {

  @Override
  public @NotNull Instant parse(@NotNull String text, @NotNull Locale locale) {
    // Parse the text into an Instant
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss", locale)
      .withZone(ZoneId.systemDefault());
    return Instant.from(formatter.parse(text));
  }

  @Override
  public @NotNull String print(@NotNull Instant object, @NotNull Locale locale) {
    // Format the Instant into a string
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss", locale)
      .withZone(ZoneId.systemDefault());
    return formatter.format(object);
  }

}