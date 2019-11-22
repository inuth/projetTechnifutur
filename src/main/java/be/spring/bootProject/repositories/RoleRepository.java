package be.spring.bootProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.spring.bootProject.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}
