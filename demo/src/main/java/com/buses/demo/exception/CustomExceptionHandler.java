package com.buses.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex/*, WebRequest request*/) {
        List<String> details = Stream.of(ex.getLocalizedMessage()).collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecordNotFoundExceptions(RecordNotFoundException ex) {
        List<String> details = Stream.of(ex.getLocalizedMessage()).collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

//    manejador producido cuando el argumento del método no es el tipo esperado:
//    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
//    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
//            MethodArgumentTypeMismatchException ex, WebRequest request) {
//        String error =
//                ex.getName() + " should be of type " + ex.getRequiredType().getName();
//
//        ApiError apiError =
//                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), apiError.getStatus());
//    }

//    manejador cuando ocurre una solicitud con un método HTTP no admitido:
//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
//            HttpRequestMethodNotSupportedException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        StringBuilder builder = new StringBuilder();
//        builder.append(ex.getMethod());
//        builder.append(
//                " method is not supported for this request. Supported methods are ");
//        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
//
//        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED,
//                ex.getLocalizedMessage(), builder.toString());
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), apiError.getStatus());
//    }

//    manejador cuando el cliente envía una solicitud con un tipo de medio no compatible
//    @Override
//    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
//            HttpMediaTypeNotSupportedException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        StringBuilder builder = new StringBuilder();
//        builder.append(ex.getContentType());
//        builder.append(" media type is not supported. Supported media types are ");
//        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
//
//        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
//                ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), apiError.getStatus());
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        List<String> details = ex.getBindingResult().getAllErrors().stream().map((error) ->
//            error.getDefaultMessage()).collect(Collectors.toList());
//        return new ResponseEntity(new ErrorResponse("Validation Failed", details), HttpStatus.BAD_REQUEST);
//    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = ex.getBindingResult().getAllErrors().stream().map((error) ->
            error.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity(new ErrorResponse("Validation Failed", details), HttpStatus.BAD_REQUEST);
    }
}
