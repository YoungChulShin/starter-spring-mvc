package starter.spring.mvc.mvcstarter.adapter.in.web.model.response;

import lombok.Getter;
import starter.spring.mvc.mvcstarter.common.exceptions.ErrorCode;

@Getter
public final class ApiResponse<T> {

  private final Result result;
  private final T data;
  private final String message;
  private final String errorCode;

  private ApiResponse(Result result, T data, String message, String errorCode) {
    this.result = result;
    this.data = data;
    this.message = message;
    this.errorCode = errorCode;
  }

  public static <T> ApiResponse<T> success(T data, String message) {
    return new ApiResponse<>(
        Result.SUCCESS,
        data,
        message,
        null);
  }

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(
        Result.SUCCESS,
        data,
        null,
        null);
  }

  public static ApiResponse<Void> success() {
    return new ApiResponse<>(
        Result.SUCCESS,
        null,
        null,
        null);
  }

  public static ApiResponse<Void> fail(ErrorCode errorCode) {
    return new ApiResponse<>(
        Result.FAIL,
        null,
        errorCode.getMessage(),
        errorCode.name());
  }

  public enum Result {
    SUCCESS,
    FAIL
  }
}
