package dev.nemi.haru.controller.formatter;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CBFormatter implements Formatter<Boolean> {
  @Override
  public @NotNull Boolean parse(String text, Locale locale) throws ParseException {
    return "on".equals(text);
  }

  @Override
  public @NotNull String print(Boolean object, Locale locale) {
    return object? "on" : "off";
  }
}
