package com.numble.bankingserver.global.exceptionHandler;
import com.numble.bankingserver.global.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionHandlers {

    @ExceptionHandler(BasicException.class)
    public ResponseEntity<ErrFormat> basicException(BasicException e){
        return new ResponseEntity<>(ErrFormat.create(e.getExceptionType()), e.getExceptionType().getStatus());
    }
}
