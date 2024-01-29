package com.rpdpymnt.reporting.controller;

import com.rpdpymnt.reporting.TestUtils;
import com.rpdpymnt.reporting.domain.request.ReportRequest;
import com.rpdpymnt.reporting.domain.request.TransactionListRequest;
import com.rpdpymnt.reporting.domain.response.ReportResponse;
import com.rpdpymnt.reporting.domain.response.TransactionListResponse;
import com.rpdpymnt.reporting.exception.InvalidTransactionException;
import com.rpdpymnt.reporting.mapper.TransactionMapper;
import com.rpdpymnt.reporting.repository.TransactionRepository;
import com.rpdpymnt.reporting.service.TransactionService;
import com.rpdpymnt.reporting.util.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TransactionControllerTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    private TransactionService transactionService;
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        this.transactionService = new TransactionService(this.transactionMapper, this.transactionRepository);
        this.transactionController = new TransactionController(this.transactionService);

        // Create transactions
        transactionRepository.insert(TestUtils.produceMockTransaction());
    }


    @Test
    @DisplayName("Should return success response when reports requested with valid params")
    public void shouldReturnResponse_whenReportsRequestedWithValidParams() {
        ReportRequest mockedReportRequest = mock(ReportRequest.class);


//        assertThrows(InvalidTransactionException.class,
//                () -> transactionController.reports(mockedReportRequest));

        doReturn(new InvalidTransactionException())
                .when(transactionController).reports(mockedReportRequest);

//        ResponseEntity<ReportResponse> actual = this.transactionController.reports(mockedReportRequest);

//        assertEquals(HttpStatus.OK, actual.getStatusCode());
//        assertEquals(actual.getBody().getStatus(), StatusEnum.APPROVED);
//        assertThat(actual.getBody().getReportModel()).hasSize(0);
    }

    @Test
    @DisplayName("Should return success response when reports requested with valid params")
    public void shouldReturnSuccessResponse_whenReportsRequestedWithValidParams() {
        ReportRequest mockedReportRequest = mock(ReportRequest.class);
        mockedReportRequest.setFromDate(Date.valueOf("2024-01-24"));
        mockedReportRequest.setToDate(Date.valueOf("2024-02-24"));
        mockedReportRequest.setMerchant(1);
        mockedReportRequest.setAcquirer(1);

        ResponseEntity<ReportResponse> actual = this.transactionController.reports(mockedReportRequest);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(actual.getBody().getStatus(), StatusEnum.APPROVED);
        assertThat(actual.getBody().getReportModel()).hasSize(0);
    }

    @Test
    @DisplayName("Should return success response when TransactionList requested with valid params")
    public void shouldReturnSuccessResponse_whenTransactionListRequestedWithValidParams() throws Exception {
        TransactionListRequest request = new TransactionListRequest();
        request.setFromDate(Date.valueOf("2024-01-24"));
        request.setToDate(Date.valueOf("2024-12-12"));
        request.setMerchantId(1);
        request.setAcquirerId(1);

        ResponseEntity<TransactionListResponse> actual = this.transactionController.transactionList(request);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertThat(actual.getBody().getData()).hasSize(1);
        assertThat(actual.getBody().getCurrent_page()).isEqualTo(0);
    }

}
