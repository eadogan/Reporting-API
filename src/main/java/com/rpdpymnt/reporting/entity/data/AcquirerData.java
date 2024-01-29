package com.rpdpymnt.reporting.entity.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcquirerData {
    private int id;
    private String name;
    private String code;
    private String type;
}
