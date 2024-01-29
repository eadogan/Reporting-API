package com.rpdpymnt.reporting.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rpdpymnt.reporting.domain.ReportModel;
import com.rpdpymnt.reporting.util.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportResponse {
    private final StatusEnum status;
    private final List<ReportModel> reportModel;
 }
