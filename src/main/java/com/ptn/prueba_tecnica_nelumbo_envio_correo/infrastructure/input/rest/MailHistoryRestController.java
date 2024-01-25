package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.input.rest;

import java.util.Date;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response.MailHistoryResponseDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler.IMailHistoryHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailHistoryRestController {

    private final IMailHistoryHandler iMailHistoryHandler;

    @Operation(summary = "get which day the most emails were sent.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Day returned"),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/most-sent/day")
    public ResponseEntity<Map<String, Date>> getDayMostMailsSent() {
        return ResponseEntity.ok(iMailHistoryHandler.getDayMostMailsSent());
    }
    
    
    @Operation(summary = "get which user was sent the most emails.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned"),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/most-sent/user")
    public ResponseEntity<MailHistoryResponseDto> getUserMostMailsSent() {
        return ResponseEntity.ok(iMailHistoryHandler.getUserMostMailsSent());
    }
    
    
    @Operation(summary = "filter email sending history.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emails returned"),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/filter")
    public ResponseEntity<MailHistoryResponseDto> getAllVehicles(@RequestParam Date dateFrom, @RequestParam Date dateUntil, @RequestParam String email) {
        return ResponseEntity.ok(iMailHistoryHandler.filter(dateFrom, dateUntil, email));
    }
    
}
