package me.aski.EMSIStage.repositories;

import me.aski.EMSIStage.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);


}
