package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.EmailVerificationToken;
import com.softserve.edu.delivery.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailTokenRepository extends BaseRepository<EmailVerificationToken, Long>  {

    @Query("select t from EmailVerificationToken t where t.user.email = :email")
    EmailVerificationToken findByEmail(@Param("email") String email);

    @Query("select t from EmailVerificationToken t where t.expiredDate < CURRENT_TIMESTAMP")
    List<EmailVerificationToken> findAllExpired();

    EmailVerificationToken findByToken(String token);

    EmailVerificationToken findByUser(User user);

}
