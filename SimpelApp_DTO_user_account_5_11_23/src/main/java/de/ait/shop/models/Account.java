package de.ait.shop.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
public class Account {

    private Long id;
    private String iban;
    private Double balance;

    public Account() {
    }

    public Account(Long id, String iban, Double balance) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
    }

    public Account(String iban, Double balance) {
        this.iban = iban;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;

        if (!Objects.equals(id, account.id)) return false;
        return Objects.equals(iban, account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }


}