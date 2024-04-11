package starter.spring.mvc.mvcstarter.config.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.json.JsonHttpLogFormatter;

@Configuration
class LogbookConfiguration {

  @Bean
  public Logbook logbook() {
    Logbook logbook = Logbook.builder()
        .sink(new DefaultSink(
            new CustomHttpLogFormatter(new JsonHttpLogFormatter()),
            new DefaultHttpLogWriter()))
        .build();
    return logbook;
  }
}
