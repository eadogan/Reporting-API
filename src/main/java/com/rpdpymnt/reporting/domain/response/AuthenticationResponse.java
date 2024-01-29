package com.rpdpymnt.reporting.domain.response;

import com.rpdpymnt.reporting.util.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private StatusEnum status;
}
