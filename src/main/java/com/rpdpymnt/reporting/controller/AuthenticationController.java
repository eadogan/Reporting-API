package com.rpdpymnt.reporting.controller;

import com.rpdpymnt.reporting.domain.UserIdModel;
import com.rpdpymnt.reporting.domain.request.SignInRequest;
import com.rpdpymnt.reporting.domain.request.SignUpRequest;
import com.rpdpymnt.reporting.domain.response.AuthenticationResponse;
import com.rpdpymnt.reporting.domain.response.ReportResponse;
import com.rpdpymnt.reporting.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/merchant/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Insert User", description = "Returns userId when the user is created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ReportResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid user details"),
            @ApiResponse(responseCode = "404", description = "User not inserted"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReportResponse.class)) }) })
    @PostMapping("/signup")
    public UserIdModel signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @Operation(summary = "Login User", description = "Returns signed JWT token valid for 10 mins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ReportResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid JWT token"),
            @ApiResponse(responseCode = "404", description = "JWT token not generated"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReportResponse.class)) }) })
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody SignInRequest request) {
        return authenticationService.login(request);
    }
}