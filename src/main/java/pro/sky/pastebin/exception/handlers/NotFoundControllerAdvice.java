package pro.sky.pastebin.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pro.sky.pastebin.exception.BadRequestException;
import pro.sky.pastebin.exception.NotFoundException;

@ControllerAdvice
public class NotFoundControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(){
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badLot(){
        return ResponseEntity.status(400).build();
    }
}
