package com.reloadly.accountmicroservice.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long accountId;
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "ACCOUNT_NUMBER", unique = true)
    private String accountNumber;
    @Column(name = "BALANCE")
    private Long balance;
    @Column(name = "CREATION_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
    @ManyToOne
    @JoinColumn(name = "FK_ACCOUNT_TYPE", nullable = false)
    private AccountType accountType;
}
