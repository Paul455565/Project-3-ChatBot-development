package za.ac.cput.repository;

import org.springframework.stereotype.Repository;
import za.ac.cput.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
