package com.rpdpymnt.reporting.entity.data;

import com.rpdpymnt.reporting.util.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantData {
    private int merchantId;
    private String name;
    private int originalAmount;
    private String originalCurrency;
    private String referenceNo;
    private String status;
    private String operation;
    private String message;
    private String created_at;
    private String transactionId;
    private String email;
    private String password;

}
