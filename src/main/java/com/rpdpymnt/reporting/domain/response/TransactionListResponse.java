package com.rpdpymnt.reporting.domain.response;

import com.rpdpymnt.reporting.domain.TransactionData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TransactionListResponse {

    private int per_page;
    private int current_page;
    private String next_page_url;
    private String prev_page_url;
    private int from;
    private int to;
    private List<TransactionData> data;

    public TransactionListResponse() {}
}
