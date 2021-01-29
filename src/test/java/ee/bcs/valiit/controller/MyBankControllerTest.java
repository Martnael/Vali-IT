package ee.bcs.valiit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.bcs.valiit.tasks.MyBankCustomer;
import ee.bcs.valiit.tasks.MyBankException;
import ee.bcs.valiit.tasks.MyBankTransaction;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MyBankControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Object Exception;
    private Object MyBankException;

    @Test
    void menu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/solutions/bank/menu")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void createAccount() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MyBankCustomer customer = new MyBankCustomer();
        customer.setId(4);
        customer.setCustomerName("Martin");
        customer.setSocialNumber("39005264231");
        mockMvc.perform(MockMvcRequestBuilders.post("/solutions/bank/createaccount")
                .contentType("application/json")
                .content(mapper.writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getBalance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/solutions/bank/getbalance?accountnr=EE4")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBalanceNoAccount() throws Exception {
        String exceptionParam = "No such account in system";
        mockMvc.perform(MockMvcRequestBuilders.get("/solutions/bank/getbalance?accountnr=EE54")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void depositMoney() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountTo("EE5");
        transaction.setSum(BigDecimal.TEN);
        mockMvc.perform(MockMvcRequestBuilders.put("/solutions/bank/depositmoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void depositMoneyNoAccount() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountTo("EE50");
        transaction.setSum(BigDecimal.TEN);
        mockMvc.perform(MockMvcRequestBuilders.put("/solutions/bank/depositmoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void depositMoneyNegativeSum() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountTo("EE5");
        BigDecimal test = new BigDecimal(-100);
        transaction.setSum(test);
        mockMvc.perform(MockMvcRequestBuilders.put("/solutions/bank/depositmoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void withdrawMoney() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountFrom("EE3");
        BigDecimal test = new BigDecimal(5);
        transaction.setSum(test);
        mockMvc.perform(MockMvcRequestBuilders.put("/solutions/bank/withdrawmoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void withdrawMoneyNoMoney() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountFrom("EE3");
        BigDecimal test = new BigDecimal(500000);
        transaction.setSum(test);
        mockMvc.perform(MockMvcRequestBuilders.put("/solutions/bank/withdrawmoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void withdrawMoneyNoAccount() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountFrom("EE50");
        BigDecimal test = new BigDecimal(500000);
        transaction.setSum(test);
        mockMvc.perform(MockMvcRequestBuilders.put("/solutions/bank/withdrawmoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void withdrawMoneyNegativeSum() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountFrom("EE4");
        BigDecimal test = new BigDecimal(-500000);
        transaction.setSum(test);
        mockMvc.perform(MockMvcRequestBuilders.put("/solutions/bank/withdrawmoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void transferMoney() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountFrom("EE3");
        transaction.setAccountTo("EE4");
        BigDecimal test = new BigDecimal(20);
        transaction.setSum(test);
        mockMvc.perform(MockMvcRequestBuilders.post("/solutions/bank/transfermoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void transferMoneyNoMoney() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountFrom("EE3");
        transaction.setAccountTo("EE4");
        BigDecimal test = new BigDecimal(20000);
        transaction.setSum(test);
        mockMvc.perform(MockMvcRequestBuilders.post("/solutions/bank/transfermoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void transferMoneyNoAccount() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountFrom("EE300");
        transaction.setAccountTo("EE4");
        BigDecimal test = new BigDecimal(20000);
        transaction.setSum(test);
        mockMvc.perform(MockMvcRequestBuilders.post("/solutions/bank/transfermoney")
                .contentType("application/json")
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void allAccount() {
    }

    @Test
    void lastAccount() {
    }

    @Test
    void allTransactions() {
    }

    @Test
    void getAccounts() {
    }

    @Test
    void oneOwner() {
    }
}