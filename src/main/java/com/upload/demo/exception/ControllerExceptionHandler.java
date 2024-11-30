package com.upload.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.upload.demo.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundException(NotFoundException ex){
    	return buildResponseEntity(ex.getErrorResponse());
    }
    
    @ExceptionHandler({UnprocessableException.class})
    public ResponseEntity<ErrorResponseDTO> unprocessableException(UnprocessableException ex){
        return buildResponseEntity(ex.getErrorResponse());
    }
    
    private ResponseEntity<ErrorResponseDTO>  buildResponseEntity(ErrorResponseDTO errorResponseDTO) {
    	 return ResponseEntity
                 .status(errorResponseDTO.getStatus())
                 .body(errorResponseDTO);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleMissingRequestHeaderException(MissingRequestHeaderException ex){
        return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(List.of(ex.getMessage())).build());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ErrorResponseDTO> accessDeniedException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AccessDeniedException accessDeniedException) throws IOException {

    	 return ResponseEntity.badRequest().body(ErrorResponseDTO.builder()
                 .status(HttpStatus.UNAUTHORIZED.value())
                 .errors(List.of("You don't have permission to access this function")).build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO genericError(Exception ex){
        log.error("Unexpected application error, ex: {}", ex.getMessage());
        return ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errors(List.of("Undefined Internal Error, try again later"))
                .build();
    }

}
