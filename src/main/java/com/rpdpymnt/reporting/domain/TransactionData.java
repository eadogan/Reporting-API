package com.rpdpymnt.reporting.domain;

import com.rpdpymnt.reporting.entity.data.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {
    
    private FxData fx;
    private  CustomerInfoData customerInfo;
    private MerchantData merchant;
    private IpnData ipn;
    private TransactionInfoData transaction;
    private AcquirerData acquirer;
    private boolean refundable;
}
