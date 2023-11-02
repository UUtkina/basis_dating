package de.ait.app.repositories;

import de.ait.app.model.User;

public interface UserRepository extends CrudRepository<User>{
    public User findByEmail(String email);
}
