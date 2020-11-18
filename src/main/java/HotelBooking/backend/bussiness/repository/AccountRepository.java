package HotelBooking.backend.bussiness.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import HotelBooking.backend.persistant.Account;
import HotelBooking.backend.persistant.BookingTicket;
import HotelBooking.backend.persistant.ManagerAccount;


@Component
public interface AccountRepository extends JpaRepository<Account, String>{

	@Query(value="select * from account where username=?1 and password=?2",nativeQuery=true)
	public Account login(String username, String password);
	@Query(value="select * from account, manager_account where account.username=manager_account.username and account.username=?1", nativeQuery=true)
	public ManagerAccount getManagerAccount(String username);
}
