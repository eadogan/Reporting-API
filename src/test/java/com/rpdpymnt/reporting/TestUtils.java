package com.rpdpymnt.reporting;


import com.rpdpymnt.reporting.domain.ReportModel;
import com.rpdpymnt.reporting.domain.response.ReportResponse;
import com.rpdpymnt.reporting.entity.TransactionEntity;
import com.rpdpymnt.reporting.entity.data.*;
import com.rpdpymnt.reporting.util.*;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestUtils {

    public static ReportResponse reportResponse(final List<ReportModel> respons) {
        return new ReportResponse(StatusEnum.APPROVED, respons);
    }

    public static TransactionEntity produceMockTransaction() {
        MerchantData merchantData = new MerchantData();
        merchantData.setMerchantId(1);

        AcquirerData acquirerData = new AcquirerData();
        acquirerData.setId(1);

        CustomerInfoData infoData = new CustomerInfoData();
        IpnData ipnData = new IpnData();
        TransactionInfoData transactionInfoData = TransactionInfoData.builder()
                .number("123")
                .email("test@test.com")
                .billingFirstName("test")
                .billingLastName("test")
                .build();



        return new TransactionEntity(
                RandomUtils.nextInt(),
                CurrencyEnum.GBP,
                BigDecimal.TEN,
                new Date(),
                new FxData(merchantData),
                infoData,
                merchantData,
                ipnData,
                transactionInfoData,
                acquirerData,
                false,
                StatusEnum.APPROVED,
                OperationEnum.DIRECT,
                PaymentMethodEnum.CREDITCARD,
                Map.of(FilterField.TransactionUUID, "test"));
    }
}
