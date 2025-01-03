package github.com.emreisler.simplebanking.persistence.repository;

import github.com.emreisler.simplebanking.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
