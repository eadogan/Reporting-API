package com.rpdpymnt.reporting.domain.request;

import com.rpdpymnt.reporting.util.ErrorCodeEnum;
import com.rpdpymnt.reporting.util.OperationEnum;
import com.rpdpymnt.reporting.util.PaymentMethodEnum;
import com.rpdpymnt.reporting.util.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionListRequest {

    private Date fromDate;
    private Date toDate;
    private StatusEnum status;
    private OperationEnum operation;
    private int merchantId;
    private int acquirerId;
    private PaymentMethodEnum paymentMethod;
    private ErrorCodeEnum errorCode;
    private String filterField;
    private String filterValue;
    private int page;
}
