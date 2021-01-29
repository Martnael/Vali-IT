package ee.bcs.valiit.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBankEntityTransactionRepository extends JpaRepository<MyBankEntityTransaction, Integer> {

}
