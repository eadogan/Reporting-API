package com.rpdpymnt.reporting.service;

import com.rpdpymnt.reporting.domain.request.ReportRequest;
import com.rpdpymnt.reporting.domain.response.ReportResponse;
import com.rpdpymnt.reporting.entity.TransactionEntity;
import com.rpdpymnt.reporting.exception.InvalidTransactionException;
import com.rpdpymnt.reporting.repository.TransactionRepository;
import com.rpdpymnt.reporting.util.CurrencyEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TransactionEntityServiceTest {

    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("Should return success response when valid params")
    void shouldReturnSuccessResponse_whenValidParams() {
        // Given
        ReportRequest mockedReportRequest = mock(ReportRequest.class);
        when(mockedReportRequest.getFromDate()).thenReturn(Date.valueOf("2024-01-24"));
        when(mockedReportRequest.getToDate()).thenReturn(Date.valueOf("2024-02-24"));
        when(mockedReportRequest.getMerchant()).thenReturn(1);
        when(mockedReportRequest.getAcquirer()).thenReturn(1);

        List<TransactionEntity> mockedTransactions = List.of(
                TransactionEntity.builder().amount(BigDecimal.ONE).currency(CurrencyEnum.GBP).build(),
                TransactionEntity.builder().amount(BigDecimal.TEN).currency(CurrencyEnum.USD).build()
        );

        when(transactionRepository.findAllByMerchantIdAndAcquirerIdBetweenFromDateAndToDate(anyInt(),anyInt(), any(), any()))
                .thenReturn(mockedTransactions);

        // When
        ReportResponse existingCustomReport = transactionService.getReport(mockedReportRequest);

        // Then
        assertThat(existingCustomReport).isNotNull();
        assertThat(existingCustomReport.getReportModel().size()).isEqualTo(2);
        verify(transactionRepository, times(1))
                .findAllByMerchantIdAndAcquirerIdBetweenFromDateAndToDate(anyInt(),anyInt(), any(), any());
    }

    @Test
    @DisplayName("Should throw InvalidTransactionException when repository service has error")
    void shouldThrowInvalidTransactionException_whenRepositoryServiceHasError() {
        // Given
        ReportRequest mockedReportRequest = mock(ReportRequest.class);
        mockedReportRequest.setFromDate(Date.valueOf("2024-01-24"));
        mockedReportRequest.setToDate(Date.valueOf("2024-02-24"));

        doThrow(InvalidTransactionException.class).when(transactionRepository)
                .findAllByMerchantIdAndAcquirerIdBetweenFromDateAndToDate(anyInt(),anyInt(), any(), any());

        // When & Then
        Assertions.assertThrows(InvalidTransactionException.class,
                () -> transactionService.getReport(mockedReportRequest));
    }
}
