package com.rpdpymnt.reporting.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantUtils {
    public final static int AUTH_PERIOD = 10;
    public final static int MAX_LIMIT_PER_PAGE = 50;

    public final static String PAGING_URL = "https://reporting.rpdpymnt.com/api/v3/transaction/list/?page=%s";
}
