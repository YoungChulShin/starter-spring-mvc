package starter.spring.mvc.mvcstarter.common.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  COMMON_SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "시스템 오류가 발생했습니다."),

  COMMON_ILLEGAL_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다"),
  ;

  private final HttpStatus status;
  private final String message;
}
