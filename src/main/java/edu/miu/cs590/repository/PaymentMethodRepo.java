package edu.miu.cs590.repository;

import edu.miu.cs590.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findAllByUsername(String username);
    Optional<PaymentMethod> findByIdAndUsername(long id, String username);

    List<PaymentMethod> findAllByUsernameAndIsDefaultIsTrue(String username);
}
