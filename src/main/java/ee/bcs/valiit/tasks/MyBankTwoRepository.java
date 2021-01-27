package ee.bcs.valiit.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyBankTwoRepository extends JpaRepository<MyBankAccountTwo, Integer> {
    List<MyBankAccountTwo> findMyBankAccountTwoByOwnerNr(int nr);
}
