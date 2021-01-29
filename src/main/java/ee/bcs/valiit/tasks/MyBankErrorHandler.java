package ee.bcs.valiit.tasks;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyBankErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyBankErrorResponse> handleError(Exception ex) {
        ex.printStackTrace();
        MyBankErrorResponse error = new MyBankErrorResponse();
        error.setMessage("FATAL ERROR");
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MyBankException.class)
    public ResponseEntity<MyBankErrorResponse> handleError(MyBankException exception) {
        MyBankErrorResponse error = new MyBankErrorResponse();
        error.setMessage(exception.getMessage());
        return new ResponseEntity<MyBankErrorResponse>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
