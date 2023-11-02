package de.ait.app.model;

import lombok.*;

import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Account {
    protected Long id;
    private String iban;
    private Double balance;

    public Account(String iban, Double balance) {
        this.iban = iban;
        this.balance = balance;
    }
}