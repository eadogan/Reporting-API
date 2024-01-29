package com.rpdpymnt.reporting.entity;

import com.rpdpymnt.reporting.util.CurrencyEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "reportModel")
public class ReportModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int count;
    private int total;
    private CurrencyEnum currency;
}
