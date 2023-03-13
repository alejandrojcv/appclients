package pin.app.appclients.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pin.app.appclients.model.Clients;

@Repository
public interface ClientRepository extends JpaRepository<Clients,Long> {

}
