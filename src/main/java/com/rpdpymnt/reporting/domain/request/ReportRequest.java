package com.rpdpymnt.reporting.domain.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ReportRequest {

    @NotNull
    private Date fromDate;
    @NotNull
    private Date toDate;
    private int merchant;
    private int acquirer;
}
