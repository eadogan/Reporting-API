package com.rpdpymnt.reporting.repository;

import com.rpdpymnt.reporting.domain.request.ReportRequest;
import com.rpdpymnt.reporting.domain.request.TransactionListRequest;
import com.rpdpymnt.reporting.domain.response.ReportResponse;
import com.rpdpymnt.reporting.entity.TransactionEntity;
import com.rpdpymnt.reporting.exception.PageNotFoundException;
import com.rpdpymnt.reporting.util.FilterField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.rpdpymnt.reporting.util.ConstantUtils.MAX_LIMIT_PER_PAGE;

@Slf4j
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private static Map<Integer, TransactionEntity> transactions = new HashMap<>();

    @Override
    public TransactionEntity findById(UUID id) {
        return transactions.get(id);
    }

    @Override
    public List<TransactionEntity> findAllByMerchantIdAndAcquirerIdBetweenFromDateAndToDate(int merchantId, int acquirerId, Date fromDate, Date toDate) {

        return transactions.values().stream()
                .filter(t -> {
                    return t.getMerchant().getMerchantId() == merchantId
                            && t.getAcquirer().getId() == acquirerId
                            && fromDate.before(t.getDate())
                            && toDate.after(t.getDate());
                }).collect(Collectors.toList());
    }

    @Override
    public List<TransactionEntity> findAllByBetweenFromDateAndToDate(Date fromDate, Date toDate) {
        return transactions.values().stream()
                .filter(t -> fromDate.after(t.getDate())
                        && toDate.before(t.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionEntity> findAllByQueryParams(TransactionListRequest requestParams) throws Exception {
        List<TransactionEntity> transactionEntityList =  transactions.values().stream()
                .filter(trx -> {
                    boolean beforeMatch = false;
                    if(ObjectUtils.isNotEmpty(requestParams.getFromDate())
                        && requestParams.getFromDate().before(trx.getDate())) {
                        log.info("before");
                        beforeMatch = true;
                    } else if (ObjectUtils.isEmpty(requestParams.getFromDate())) {
                        beforeMatch = true;
                    }

                    boolean afterMatch = false;
                    if(ObjectUtils.isNotEmpty(requestParams.getToDate())
                            && requestParams.getToDate().after(trx.getDate())) {
                        log.info("after");
                        afterMatch = true;
                    } else if (ObjectUtils.isEmpty(requestParams.getToDate())) {
                        afterMatch = true;
                    }

                    boolean acquirerMatch = false;
                    if(ObjectUtils.isNotEmpty(requestParams.getAcquirerId())
                        && requestParams.getAcquirerId() == trx.getAcquirer().getId()) {
                        log.info("acquirerMatch");
                        acquirerMatch = true;
                    } else if(ObjectUtils.isEmpty(requestParams.getAcquirerId())) {
                        acquirerMatch = true;
                    }

                    boolean merchantMatch = false;
                    if(ObjectUtils.isNotEmpty(requestParams.getMerchantId())
                            && requestParams.getMerchantId() == trx.getMerchant().getMerchantId()) {
                        log.info("merchantMatch");
                        merchantMatch = true;
                    } else if (ObjectUtils.isEmpty(requestParams.getMerchantId())) {
                        merchantMatch = true;
                    }

                    boolean statusMatch = false;
                    if(ObjectUtils.isNotEmpty(requestParams.getStatus())
                            && requestParams.getStatus() == trx.getStatus()) {
                        log.info("statusMatch");
                        statusMatch = true;
                    } else if(ObjectUtils.isEmpty(requestParams.getStatus())) {
                        statusMatch = true;
                    }

                    boolean operationMatch = false;
                    if(ObjectUtils.isNotEmpty(requestParams.getOperation())
                            && requestParams.getOperation() == trx.getOperation()) {
                        log.info("operationMatch");
                        operationMatch = true;
                    } else if(ObjectUtils.isEmpty(requestParams.getOperation())) {
                        operationMatch = true;
                    }

                    boolean paymentMethodMatch = false;
                    if(ObjectUtils.isNotEmpty(requestParams.getPaymentMethod())
                            && requestParams.getPaymentMethod() == trx.getPaymentMethod()) {
                        log.info("paymentMethodMatch");
                        paymentMethodMatch = true;
                    } else if(ObjectUtils.isEmpty(requestParams.getPaymentMethod())) {
                        paymentMethodMatch = true;
                    }

                    boolean customFieldMatch = false;
                    if(StringUtils.isNotEmpty(requestParams.getFilterField())
                            || StringUtils.isNotEmpty(requestParams.getFilterValue())) {
                        if(!trx.getAttributes().isEmpty()
                                && trx.getAttributes().containsKey(FilterField.valueOf(requestParams.getFilterField()))
                                && trx.getAttributes().get(FilterField.valueOf(requestParams.getFilterField())).equals(requestParams.getFilterValue())) {
                            log.info("customFieldMatch");
                            customFieldMatch = true;
                        }
                    } else if(StringUtils.isEmpty(requestParams.getFilterField())) {
                        customFieldMatch = true;
                    }


                    return (beforeMatch
                            && afterMatch
                            && acquirerMatch
                            && merchantMatch
                            && statusMatch
                            && operationMatch
                            && paymentMethodMatch
                            && customFieldMatch);
                }).toList();

        List<List<TransactionEntity>> pagedTransactions = ListUtils.partition(transactionEntityList, MAX_LIMIT_PER_PAGE);

        log.info("Transactions requested, Partition Size: {}, Page Size: {}", pagedTransactions.size(), requestParams.getPage());

        if(pagedTransactions.isEmpty()) {
            return Collections.emptyList();
        }

        if(requestParams.getPage()> pagedTransactions.size() - 1) {
            throw new PageNotFoundException("Page not found.");
        }

        return pagedTransactions.get(requestParams.getPage());
    }

    @Override
    public Integer insert(TransactionEntity entity) {
        transactions.put(entity.getId(), entity);
        return entity.getId();
    }
}