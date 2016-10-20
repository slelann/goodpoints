package fr.sll.goodpoints;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KidRepository extends JpaRepository<Kid, Long> {

	List<Kid> findByNameStartsWithIgnoreCase(String name);

}
