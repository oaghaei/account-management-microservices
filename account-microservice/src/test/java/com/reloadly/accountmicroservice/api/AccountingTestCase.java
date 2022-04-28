package com.reloadly.accountmicroservice.api;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public interface AccountingTestCase {
    String TEST_CASE_ACCOUNT_1 = "testcase/account-dto-1.json";
    String TEST_CASE_ACCOUNT_UPDATE_1 = "testcase/account-update-dto-1.json";

    static String readJsonCase(String filename) throws IOException {
        return StreamUtils.copyToString(new ClassPathResource(filename).getInputStream(), Charset.defaultCharset());
    }
}
