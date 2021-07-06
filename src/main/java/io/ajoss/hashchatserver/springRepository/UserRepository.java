package io.ajoss.hashchatserver.springRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.ajoss.hashchatserver.model.HashUser;

@Repository
public interface UserRepository extends CrudRepository<HashUser, String> {

	HashUser findByusername(String username);

}
