package de.ait.shop.repositories;

import de.ait.shop.models.Account;
import de.ait.shop.models.User;

public interface AccountsRepository extends CrudRepository<Account>{
    Account findOneByIban(String iban);


}
