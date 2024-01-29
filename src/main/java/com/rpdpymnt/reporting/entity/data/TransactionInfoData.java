package com.rpdpymnt.reporting.entity.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInfoData {
    private String number;
    private String email;
    private String billingFirstName;
    private String billingLastName;
}
