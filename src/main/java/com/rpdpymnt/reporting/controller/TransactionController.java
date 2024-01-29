package com.rpdpymnt.reporting.controller;

import com.rpdpymnt.reporting.domain.TransactionIdModel;
import com.rpdpymnt.reporting.domain.request.ReportRequest;
import com.rpdpymnt.reporting.domain.request.TransactionInsertRequest;
import com.rpdpymnt.reporting.domain.request.TransactionListRequest;
import com.rpdpymnt.reporting.domain.response.ReportResponse;
import com.rpdpymnt.reporting.domain.response.TransactionListResponse;
import com.rpdpymnt.reporting.service.TransactionService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition
@RestController
@RequestMapping(value = "/api/v3/transaction",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transaction Report API", description = "The Reporting API gives you access to most of the report data in PSP.")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Report by Currency Pairs", description = "Returns total number of transactions and sum grouped by currency pairs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ReportResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied"),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReportResponse.class)) }) })
    @PostMapping("/report")
    public ResponseEntity<ReportResponse> reports(@RequestBody ReportRequest request) {
        return new ResponseEntity<ReportResponse>(transactionService.getReport(request), HttpStatus.OK);
    }

    @Operation(summary = "Insert Transaction", description = "Returns 201 when the transation is created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ReportResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied"),
            @ApiResponse(responseCode = "404", description = "Transaction not inserted"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReportResponse.class)) }) })
    @PostMapping("/insertTransactionList")
    public ResponseEntity<TransactionIdModel> insertTransactionList(@RequestBody TransactionInsertRequest request) {
        int transactionId = transactionService.insertTransactionList(request);
        return new ResponseEntity<>(new TransactionIdModel(String.valueOf(transactionId)), HttpStatus.CREATED);
    }
    @Operation(summary = "List of Transaction", description = "Returns list of transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ReportResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied"),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReportResponse.class)) }) })
    @PostMapping("/list")
    public ResponseEntity<TransactionListResponse> transactionList(@RequestBody TransactionListRequest request) throws Exception {
        return new ResponseEntity<TransactionListResponse>(transactionService.getTransactionList(request), HttpStatus.OK);
    }

}
