package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.input.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response.MailHistoryResponseDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler.IMailHistoryHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @GetMapping(path = "/most-sent/day", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, Date> getDayMostMailsSent() {
        return iMailHistoryHandler.getDayMostMailsSent();
    }
    
    
    @Operation(summary = "get which user was sent the most emails.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned"),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping(path = "/most-sent/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, String> getUserMostMailsSent() {
        return iMailHistoryHandler.getUserMostMailsSent();
    }
    
    
    @Operation(summary = "filter email sending history.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emails returned"),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<MailHistoryResponseDto> getAllVehicles(
    		@Parameter(description = "Fecha desde (formato: yyyy-MM-dd)", example = "2024-12-31", required = false)
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, 
            
            @Parameter(description = "Fecha hasta (formato: yyyy-MM-dd)", example = "2024-12-31", required = false)
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")  Date dateUntil, 
            
    		@RequestParam(required = false) String email) {
        return iMailHistoryHandler.filter(dateFrom, dateUntil, email);
    }
    
}
