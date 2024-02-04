package com.rpdpymnt.reporting.service;

import com.rpdpymnt.reporting.domain.ReportModel;
import com.rpdpymnt.reporting.domain.request.ReportRequest;
import com.rpdpymnt.reporting.domain.request.TransactionInsertRequest;
import com.rpdpymnt.reporting.domain.request.TransactionListRequest;
import com.rpdpymnt.reporting.domain.response.ReportResponse;
import com.rpdpymnt.reporting.domain.response.TransactionListResponse;
import com.rpdpymnt.reporting.entity.TransactionEntity;
import com.rpdpymnt.reporting.exception.InvalidDataException;
import com.rpdpymnt.reporting.mapper.TransactionMapper;
import com.rpdpymnt.reporting.repository.TransactionRepository;
import com.rpdpymnt.reporting.util.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionService {

    private TransactionMapper transactionMapper;
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionMapper transactionMapper, TransactionRepository transactionRepository) {
        this.transactionMapper = transactionMapper;
        this.transactionRepository = transactionRepository;
    }


    public ReportResponse getReport(final ReportRequest reportRequest) {

        try {
            List<TransactionEntity> transactionList = transactionRepository
                    .findAllByMerchantIdAndAcquirerIdBetweenFromDateAndToDate(reportRequest.getMerchant(), reportRequest.getAcquirer(), reportRequest.getFromDate(), reportRequest.getToDate());

            log.info("Total number of transactions: {}", transactionList.size());


            List<ReportModel> resultList = transactionList.stream()
                    .collect(Collectors.groupingBy(TransactionEntity::getCurrency,
                            Collectors.collectingAndThen(
                                    Collectors.toList(),
                                    responseList -> {
                                        BigDecimal total = responseList.stream()
                                                .map(TransactionEntity::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

                                        return List.of(ReportModel.builder()
                                                .count(responseList.size())
                                                .total(total.intValue())
                                                .currency(responseList.stream().findFirst().get().getCurrency())
                                                .build());
                                    }
                            )
                    ))
                    .values()
                    .stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            return new ReportResponse(StatusEnum.APPROVED,  resultList);
        } catch(InvalidDataException e) {
            throw new InvalidDataException("Invalid report exception {}", e);
        }
    }

    public TransactionListResponse getTransactionList(final TransactionListRequest transactionRequest) throws Exception {
        List<TransactionEntity> transactionEntityList = transactionRepository.findAllByQueryParams(transactionRequest);
        return transactionMapper.mapToTransactionListResponse(transactionEntityList, transactionRequest);
    }

    public int insertTransactionList (final TransactionInsertRequest transactionInsertRequest) {
        TransactionEntity transactionEntity = transactionMapper.mapToTransactionEntity(transactionInsertRequest);
        return transactionRepository.insert(transactionEntity);
    }
}