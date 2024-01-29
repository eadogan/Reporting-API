package com.rpdpymnt.reporting.repository;


import com.rpdpymnt.reporting.domain.request.TransactionListRequest;
import com.rpdpymnt.reporting.entity.TransactionEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository {

    TransactionEntity findById(UUID id);

    List<TransactionEntity> findAllByMerchantIdAndAcquirerIdBetweenFromDateAndToDate(int merchantId, int acquirerId, Date fromDate, Date toDate);

    List<TransactionEntity> findAllByBetweenFromDateAndToDate(Date fromDate, Date toDate);

    List<TransactionEntity> findAllByQueryParams(TransactionListRequest requestParams) throws Exception;

    Integer insert(TransactionEntity entity);
}