package javaProjects.HRMS.core.repositories;

import javaProjects.HRMS.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
	User getByEmail(String email);
}
