package com.yarin.pokemon.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yarin.pokemon.models.ERole;
import com.yarin.pokemon.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
