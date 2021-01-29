package ee.bcs.valiit.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyBankEntityAccountRepository extends JpaRepository<MyBankEntityAccount, Integer> {

    List<MyBankEntityAccount> findAllByCustomer_Name(String name);
}
