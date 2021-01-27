package ee.bcs.valiit.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyBankAccountTwoRepository extends JpaRepository<MyBankAccountTwo, Integer> {
    MyBankAccountTwo findByAccountNumber(String accountNr);
    List<MyBankAccountTwo> findAllByOwner(String owner);
}
