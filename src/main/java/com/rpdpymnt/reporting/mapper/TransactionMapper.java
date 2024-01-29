package com.rpdpymnt.reporting.mapper;

import com.google.common.collect.Iterables;
import com.rpdpymnt.reporting.domain.TransactionData;
import com.rpdpymnt.reporting.domain.request.TransactionInsertRequest;
import com.rpdpymnt.reporting.domain.request.TransactionListRequest;
import com.rpdpymnt.reporting.domain.response.TransactionListResponse;
import com.rpdpymnt.reporting.entity.TransactionEntity;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.rpdpymnt.reporting.util.ConstantUtils.MAX_LIMIT_PER_PAGE;
import static com.rpdpymnt.reporting.util.ConstantUtils.PAGING_URL;
import static java.lang.String.format;

@Component
public class TransactionMapper {

    /**
     * Responsible to handle mapping TransactionEntity and TransactionListRequest to the http response.
     *
     * @param transactionEntityList {@link List}
     * @param request {@link TransactionListRequest}
     * @return TransactionListResponse
     */
    public TransactionListResponse mapToTransactionListResponse(final List<TransactionEntity> transactionEntityList, TransactionListRequest request) {

        List<TransactionData> transactionData = transactionEntityList.stream().map(trx
                -> new TransactionData(trx.getFx(), trx.getCustomerInfo(), trx.getMerchant(), trx.getIpn(),
                trx.getTransaction(), trx.getAcquirer(), trx.isRefundable())).toList();

        TransactionListResponse response = new TransactionListResponse();

        response.setData(transactionData);
        response.setCurrent_page(request.getPage());
        response.setPer_page(MAX_LIMIT_PER_PAGE);

        if(!transactionEntityList.isEmpty()) {
            if(request.getPage() > 0) {
                response.setPrev_page_url(format(PAGING_URL, request.getPage()));
            }
            response.setNext_page_url(format(PAGING_URL, request.getPage() + 1));

            response.setFrom(Iterables.get(transactionEntityList, 0).getId());
            response.setTo(Iterables.getLast(transactionEntityList).getId());
        }

        return response;
    }

    /**
     * Responsible to map TransactionInsertRequest to the response model -> TransactionEntity
     *
     * @param request {@link TransactionInsertRequest}
     * @return TransactionEntity
     */
    public TransactionEntity mapToTransactionEntity(final TransactionInsertRequest request) {

        return new TransactionEntity(
                produceNextTransactionId(),
                request.getCurrency(),
                request.getAmount(),
                request.getDate(),
                request.getData().getFx(),
                request.getData().getCustomerInfo(),
                request.getData().getMerchant(),
                request.getData().getIpn(),
                request.getData().getTransaction(),
                request.getData().getAcquirer(),
                request.getData().isRefundable(),
                request.getStatus(),
                request.getOperation(),
                request.getPaymentMethod(),
                request.getAttributes());
    }

    private int produceNextTransactionId() {
        return RandomUtils.nextInt();
    }
}
