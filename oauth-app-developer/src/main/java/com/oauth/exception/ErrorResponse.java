package com.oauth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.util.Objects;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String code;
    private String description;
    private String detail;

    public ErrorResponse makeThrowableErrorResponse(Throwable throwable) {
        code = ErrorCode.EXIST_VALUE.getCode();
        description = ErrorCode.EXIST_VALUE.getDescription();
        detail = throwable.getMessage();

        return new ErrorResponse(code, description, detail);
    }

    public ErrorResponse makeBindingErrorResponse(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            detail = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (Objects.requireNonNull(bindResultCode)){
                case "NotNull":
                    code = ErrorCode.NOT_NULL.getCode();
                    description = ErrorCode.NOT_NULL.getDescription();
                    break;
                case "Min":
                    code = ErrorCode.MIN_VALUE.getCode();
                    description = ErrorCode.MIN_VALUE.getDescription();
                    break;
            }
        }

        return new ErrorResponse(code, description, detail);
    }
}
