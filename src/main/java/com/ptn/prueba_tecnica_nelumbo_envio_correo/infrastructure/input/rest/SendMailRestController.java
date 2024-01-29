package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.request.SendMailRequestDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler.ISendMailHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SendMailRestController {

    private final ISendMailHandler iSendMailHandler;

    @Operation(summary = "Sending emails")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent",
            		content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found",
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
    })
    @PostMapping("/send-mail")
    public ResponseEntity<MessageResponseDto> registerIncome(@Valid @RequestBody SendMailRequestDto sendMailRequestDto) {
        return new ResponseEntity<MessageResponseDto>(iSendMailHandler.sendMail(sendMailRequestDto), HttpStatus.OK);
    }
    
    
}
