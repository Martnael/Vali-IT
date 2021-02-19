package ee.bcs.valiit.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyBankEntityTransactionRepository extends JpaRepository<MyBankEntityTransaction, Integer> {

    List<MyBankEntityTransaction> findAllByAccountFrom_AccountTo(String accountNumber);
    List<MyBankEntityTransaction> findAllByAccountFromAndAccountTo(int number, int accountNumber2);
    List<MyBankEntityTransaction> findAllByAccountFrom_AccountNumber(String accountNumber);

    List<MyBankEntityTransaction> findAllByAccountFromAccountNumber(String accountNumber);
}
