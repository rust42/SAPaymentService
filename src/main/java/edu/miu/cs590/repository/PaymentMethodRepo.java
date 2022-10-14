package edu.miu.cs590.repository;

import edu.miu.cs590.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findAllByUsername(String username);
    Optional<PaymentMethod> findByIdAndUsername(long id, String username);

    @Query("select paymentMethod from PaymentMethod as paymentMethod where paymentMethod.isDefault = true and paymentMethod.username = :username")
    List<PaymentMethod> findAllDefaultByUsername(@Param("username") String username);

    @Query("select paymentMethod from PaymentMethod as paymentMethod where" +
            " paymentMethod.username = :username AND paymentMethod.id = :id")
    Optional<PaymentMethod>findByUsernameAndId(@Param("username") String username, @Param("id") long id);
}
