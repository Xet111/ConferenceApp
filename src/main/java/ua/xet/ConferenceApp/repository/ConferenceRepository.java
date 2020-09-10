package ua.xet.ConferenceApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.xet.ConferenceApp.entity.Conference;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference,Long> {
    public Optional<Conference>findByName(String name);
    public List<Conference>findByActiveTrue();
    public Optional<Conference> findById(Long id);
}
