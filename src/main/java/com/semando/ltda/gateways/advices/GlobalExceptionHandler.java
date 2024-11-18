package com.semando.ltda.gateways.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

/**
 * Esta classe cuida de todas as exceções no sistema, centralizando o tratamento de erros e
 * evitando duplicação de código nos controladores.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Lida com qualquer erro genérico e devolve um status 500 (erro interno do servidor).
     *
     * @param ex a exceção capturada
     * @param request detalhes da requisição que causou o erro
     * @return resposta com a mensagem de erro e status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Trata erros de runtime e devolve um status 400 (erro do cliente).
     *
     * @param ex a exceção de runtime
     * @param request detalhes da requisição
     * @return resposta com a mensagem de erro e status 400
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções de elemento não encontrado e devolve um status 404 (não encontrado).
     *
     * @param ex exceção de elemento não encontrado
     * @param request detalhes da requisição
     * @return resposta com a mensagem de erro e status 404
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
