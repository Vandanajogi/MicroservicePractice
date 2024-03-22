package com.quest.userResource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users=new ArrayList<>();
private static int userscount=0	;
	
	static {
		users.add(new User(++userscount,"Pari",LocalDate.now().minusYears(30)) );
		users.add(new User(++userscount,"Vandana",LocalDate.now().minusYears(10)) );
		users.add(new User(++userscount,"Jack",LocalDate.now().minusYears(20)) );
		users.add(new User(++userscount,"Maya",LocalDate.now().minusYears(35)) );
	}
	
	//findAll
	public List<User> findAll(){
		return users;
	}
	
//save user
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userscount);
		}
		users.add(user);
		return user;
	}
	
	//create/save user
	public User createUser(User user) {
		user.setId(++userscount);
		users.add(user);
		return user;
	}
	
	//find one
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}

	
	//by using java8 Features
	public User findOneuser(int id) {
		
		Predicate<? super User> predicate=user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
		
		
	}
	
public void deleteById(int id) {
		
		Predicate<? super User> predicate=user -> user.getId().equals(id);
		users.removeIf(predicate);
		
		
	}
}
