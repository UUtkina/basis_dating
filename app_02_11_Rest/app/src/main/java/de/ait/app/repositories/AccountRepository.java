package de.ait.app.repositories;

import de.ait.app.model.Account;

public interface AccountRepository extends CrudRepository<Account>{
    public Account findByIban(String iban);
}
