package com.rpdpymnt.reporting.domain.request;

import com.rpdpymnt.reporting.domain.TransactionData;
import com.rpdpymnt.reporting.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionInsertRequest {
    private CurrencyEnum currency;
    private BigDecimal amount;
    private  Date date;
    private  boolean refundable;
    private  StatusEnum status;
    private  OperationEnum operation;
    private PaymentMethodEnum paymentMethod;
    private Map<FilterField, String> attributes;
    private TransactionData data;
}
