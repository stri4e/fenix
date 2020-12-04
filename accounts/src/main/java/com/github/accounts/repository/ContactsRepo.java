package com.github.accounts.repository;

import com.github.accounts.entity.Contact;
import com.github.accounts.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepo extends JpaRepository<Contact, Long> {

    @Modifying
    @Query(value = "update Contact c set c.phone=:phone, c.email=:email where c.id=:id")
    void update(@Param(value = "id") Long id,
                @Param(value = "phone") String phone,
                @Param(value = "email") String email
    );

    @Modifying
    @Query(value = "update Contact c set c.status=:status where c.id=:id")
    void delete(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
