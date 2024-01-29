package com.rpdpymnt.reporting.domain;

import com.rpdpymnt.reporting.util.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReportModel {

    private final int count;
    private final int total;
    private final CurrencyEnum currency;
}
