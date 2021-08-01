package br.com.raloliver.jforum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandleValidation {

    /**
     * @MessageSource: Para retornar a mensagem de erro de acordo ao idioma do
     *                 client.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * @ResponseStatus: informar que o status será mesmo o 400. Por default, o SB
     *                  considera que fizemos o tratamento e, por padrão, ele vai
     *                  devolver 200 para o cliente. Por isso, é necessário utilizar
     *                  essa annotation.
     * @LocaleContextHolder: coletar o idioma do client para retornar a mensagem de
     *                       erro no idioma correto
     * @param exception
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorFormDto> handle(MethodArgumentNotValidException exception) {
        List<ErrorFormDto> errorFormDto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            ErrorFormDto errorDto = new ErrorFormDto(error.getField(), message);

            errorFormDto.add(errorDto);
        });

        return errorFormDto;
    }
}
