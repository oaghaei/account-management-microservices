package com.reloadly.accountmicroservice.api;

import com.reloadly.accountmicroservice.service.mapper.AccountMapper;
import com.reloadly.api.dto.AccountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.reloadly.accountmicroservice.api.AccountingTestCase.TEST_CASE_ACCOUNT_1;
import static com.reloadly.accountmicroservice.api.AccountingTestCase.TEST_CASE_ACCOUNT_UPDATE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class AccountMicroserviceApiTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountMapper accountMapper;

    @Test
    void accountControllerTest() throws Exception {
        mockMvc.perform(post("/account/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AccountingTestCase.readJsonCase(TEST_CASE_ACCOUNT_1)))
                .andExpect(status().isCreated());

        mockMvc.perform(patch("/account/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AccountingTestCase.readJsonCase(TEST_CASE_ACCOUNT_UPDATE_1)))
                .andExpect(status().isOk());

        AccountDto accountDto =
                accountMapper.jsonToAccountDto(AccountingTestCase.readJsonCase(TEST_CASE_ACCOUNT_UPDATE_1));
        MvcResult loadAccountMvcResult = mockMvc.perform(get("/account/load/" + accountDto.getAccountNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        AccountDto loadedAccountDto = accountMapper.jsonToAccountDto(loadAccountMvcResult.getResponse().getContentAsString());
        assertEquals(accountDto.getAccountNumber(), loadedAccountDto.getAccountNumber());
        assertEquals(accountDto.getCustomerId(), loadedAccountDto.getCustomerId());
        assertEquals(accountDto.getAccountTypeId(), loadedAccountDto.getAccountTypeId());
    }
}
