package com.rpdpymnt.reporting.entity;

import com.rpdpymnt.reporting.entity.data.*;
import com.rpdpymnt.reporting.util.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Holds the transaction state, failed() and succeed() methods returns state of existing transactions
 */
@Data
@Builder
@AllArgsConstructor
public class TransactionEntity {

    private final int id;
    private final CurrencyEnum currency;
    private final BigDecimal amount;
    private final Date date;
    private final FxData fx;
    private final CustomerInfoData customerInfo;
    private final MerchantData merchant;
    private final IpnData ipn;
    private final TransactionInfoData transaction;
    private final AcquirerData acquirer;
    private final boolean refundable;
    private final StatusEnum status;
    private final OperationEnum operation;
    private final PaymentMethodEnum paymentMethod;
    private final Map<FilterField, String> attributes;

}