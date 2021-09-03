package br.com.daysesoares.helpdesk.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.daysesoares.helpdesk.api.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String>{

	
	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateRegistrationDesc(
			String title, String status, String Priority, PageRequest pages);
	
	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateRegistrationDesc(
			String title, String status, String Priority, String userId, PageRequest pages);
	
	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserId(
			String title, String status, String Priority, PageRequest pages);
	
	Page<Ticket> findByNumber(Integer number, PageRequest pages);

	Page<Ticket> findByUserIdOrderByDateRegistrationDesc(String userId, PageRequest pages);
	
}
