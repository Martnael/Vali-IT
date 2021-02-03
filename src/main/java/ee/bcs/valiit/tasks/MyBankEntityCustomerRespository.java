package ee.bcs.valiit.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBankEntityCustomerRespository extends JpaRepository<MyBankEntityCustomer, Integer> {
     MyBankEntityCustomer findByName(String name);
     MyBankEntityCustomer findByUserName(String userName);
}
