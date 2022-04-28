package com.reloadly.accountmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ACCOUNT_TYPE")
public class AccountType {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @OneToMany(mappedBy = "accountType", fetch = FetchType.LAZY)
    private Set<Account> accounts;
}
