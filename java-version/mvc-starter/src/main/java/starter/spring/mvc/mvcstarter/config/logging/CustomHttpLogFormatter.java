package starter.spring.mvc.mvcstarter.config.logging;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpHeaders;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Precorrelation;
import org.zalando.logbook.json.JsonHttpLogFormatter;

final class CustomHttpLogFormatter implements HttpLogFormatter {

  private static final String CONTENT_DATE = "date";
  private static final String CONTENT_REMOTE = "remote";
  private static final String CONTENT_HEADERS = "headers";


  private final JsonHttpLogFormatter delegate;

  public CustomHttpLogFormatter(JsonHttpLogFormatter delegate) {
    this.delegate = delegate;
  }

  @Override
  public String format(Precorrelation precorrelation, HttpRequest request) throws IOException {
    final Map<String, Object> content = delegate.prepare(precorrelation, request);
    content.put(CONTENT_DATE, getFormattedDate());
    content.remove(CONTENT_REMOTE);

    HttpHeaders headers = (HttpHeaders) content.get(CONTENT_HEADERS);
    headers = headers.apply((key, value) -> hasIgnorableRequestContents(key) ? null : value);
    content.put(CONTENT_HEADERS, headers);

    return delegate.format(content);
  }

  private boolean hasIgnorableRequestContents(String key) {
    return key.equalsIgnoreCase("accept") ||
        key.equalsIgnoreCase("accept-encoding") ||
        key.equalsIgnoreCase("cache-control") ||
        key.equalsIgnoreCase("connection") ||
        key.equalsIgnoreCase("content-length") ||
        key.equalsIgnoreCase("postman-token") ||
        key.equalsIgnoreCase("application/json");
  }

  @Override
  public String format(Correlation correlation, HttpResponse response) throws IOException {
    final Map<String, Object> content = delegate.prepare(correlation, response);
    content.put(CONTENT_DATE, getFormattedDate());
    content.remove(CONTENT_HEADERS);

    return delegate.format(content);
  }

  private String getFormattedDate() {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);
  }
}
