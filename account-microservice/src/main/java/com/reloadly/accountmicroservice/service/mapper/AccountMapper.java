package com.reloadly.accountmicroservice.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reloadly.accountmicroservice.model.Account;
import com.reloadly.api.dto.AccountDto;
import com.reloadly.api.dto.UpdateAccountDto;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public interface AccountMapper {

    @Named("AccountDtoToAccountEntity")
    @Mapping(source = "accountTypeId", target = "accountType.id")
    Account accountDtoToAccountEntity(AccountDto dto);

    @Named("AccountToAccountDto")
    @Mapping(source = "accountType.id", target = "accountTypeId")
    AccountDto accountToAccountDto(Account account);


    @Named("UpdateAccountDtoToAccountEntity")
    @Mapping(source = "accountTypeId", target = "accountType.id")
    Account updateAccountDtoToAccountEntity(UpdateAccountDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void accountToAccount(Account updateAccount, @MappingTarget Account target);

    @Named("jsonToAccountDto")
    default AccountDto jsonToAccountDto(String json) throws JsonProcessingException {
        if (Objects.nonNull(json)) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, AccountDto.class);
        }
        return null;
    }
}
