package by.testTask.testApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import by.testTask.testApp.domain.MessageApp;
import by.testTask.testApp.domain.Status;

public interface MessageAppRepository extends CrudRepository<MessageApp, Long> {
	
	@Query(value = "SELECT COUNT(*) FROM message_app WHERE status = ?1", nativeQuery = true)
	int countByStatus(int status);

	//SELECT COUNT(*) FROM message_app WHERE status = 0
	//"SELECT COUNT(*) AS COUNT FROM Book WHERE status = 'Y'"
}
