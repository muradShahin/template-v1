package com.swiftref.repositories;

import com.swiftref.models.Iban;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * this interface is just for template purposes to show how JPA repositories will be created
 * it does not have any use in this project
 *
 */
public interface IbanRepository extends JpaRepository<Iban,Long> {
}
