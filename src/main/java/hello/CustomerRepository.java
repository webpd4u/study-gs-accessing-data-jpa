package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

//	List<Customer> findByLastName(String lastName);

	@Query(value = "SELECT c.id AS customer_id, c.first_name AS first_name, p.id as phone_id, p.number as number FROM customer c, phone p WHERE c.id = p.person_id and c.id = ?1", nativeQuery = true)
	List<CustomerPhoneProjection> findsByProjectedQuery(long id);

	@Query(value = "SELECT c.id AS customer_id, c.first_name AS first_name, p.id as phone_id, p.number as number FROM customer c, phone p WHERE c.id = p.person_id and c.id = ?1", nativeQuery = true)
	<T> List<T> findCollectionByIdNativeQuery(long id, Class<T> projection);

	@Query("select new hello.CustomerPhoneDto(c.id, c.firstName, p.id, p.number) from Customer c, Phone p where c.id = p.personId and c.id = ?1")
	List<CustomerPhoneDto> findDtoWithConstructorExpression(long id);

	@Query(value = "SELECT c.id AS customer_id, c.first_name AS first_name, p.id as phone_id, p.number as number FROM customer c, phone p WHERE c.id = p.person_id and c.id = ?1", nativeQuery = true)
	List<CustomerPhoneDto> findAllDtoedBy(long id);

	@Query(value = "SELECT c.id AS customer_id, c.first_name AS first_name, p.id as phone_id, p.number as number FROM customer c, phone p WHERE c.id = p.person_id and c.id = ?1", nativeQuery = true)
	List<Object[]> findObjectByIdNativeQuery(long id);
}
