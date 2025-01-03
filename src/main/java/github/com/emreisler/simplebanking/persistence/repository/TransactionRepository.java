package github.com.emreisler.simplebanking.persistence.repository;

import github.com.emreisler.simplebanking.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
