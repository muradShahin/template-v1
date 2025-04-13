package com.swiftref.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this class is just for template purposes it does not have any use in this project
 * as we are returning the response as is from Swift ref so this model is not required
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Iban {
    @Id
    private long id;
    private String isValid;
    private String bicCode;
    private String iban;
}
