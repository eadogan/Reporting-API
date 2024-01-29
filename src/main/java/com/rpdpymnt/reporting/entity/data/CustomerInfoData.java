package com.rpdpymnt.reporting.entity.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoData {
    private String number;
    private String email;
    private String billingFirstName;
    private String billingLastName;
}
