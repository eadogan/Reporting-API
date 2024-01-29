package com.rpdpymnt.reporting.controller;

import com.rpdpymnt.reporting.exception.handler.CustomExceptionHandler;
import com.rpdpymnt.reporting.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;

    private TransactionController transactionController;


    @BeforeEach
    public void setUp() {
        this.transactionController = new TransactionController(this.transactionService);
        mvc = MockMvcBuilders.standaloneSetup(this.transactionController).setControllerAdvice(new CustomExceptionHandler()).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser
    @DisplayName("Should collection get noAuth returns unauthorized")
    @Disabled
    public void shouldCollectionGet_noAuthReturnsUnauthorized() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        this.mvc.perform(post("/api/v3/transaction/report"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    @DisplayName("Should controller return success response when valid params passed")
    @Disabled
    void shouldControllerReturnSuccessResponse_whenValidParamsPassed() throws Exception {

        File file = ResourceUtils.getFile("classpath:core/transactionReportPayload.json");
        String content = new String(Files.readAllBytes(file.toPath()));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        this.mvc.perform(post("/api/v3/transaction/report").content(content).with(jwt()
                        .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
