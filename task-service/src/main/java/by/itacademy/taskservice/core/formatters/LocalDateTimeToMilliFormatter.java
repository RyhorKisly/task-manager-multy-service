package by.itacademy.taskservice.core.formatters;

import lombok.NonNull;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

public class LocalDateTimeToMilliFormatter implements Formatter<LocalDateTime> {
    @Override
    @NonNull
    public LocalDateTime parse(@NonNull String text,@NonNull Locale locale) {
            long milliseconds = Long.parseLong(text);
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    @Override
    @NonNull
    public String print(LocalDateTime object,@NonNull Locale locale) {
        return object.toString();
    }
}
