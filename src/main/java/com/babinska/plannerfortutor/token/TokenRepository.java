package com.babinska.plannerfortutor.token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("select t from Token t inner join User u on t.id = u.id where t.user.id = ?1 and (t.expired = false or t.revoked = false) ")
    List<Token> findAllValidTokensByUserId(Integer userId);

    Optional<Token> findByToken(String token);

    @Modifying
    @Query("update Token t set t.revoked = true, t.expired = true where t.user.id = ?1 and (t.revoked = false or t.expired = false) ")
    int revokeAndExpireTokensByUserId(Long id);
}
