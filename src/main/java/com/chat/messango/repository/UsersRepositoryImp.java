package com.chat.messango.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chat.messango.model.Roles;
import com.chat.messango.model.Users;

@Repository
public class UsersRepositoryImp implements UsersRepository {

	@Autowired
	private EntityManager entitymanager;
	@Override
	public List<Users> get() {
		Session currentSession=entitymanager.unwrap(Session.class);
		Query<Users> query= currentSession.createQuery("from Users",Users.class);
		List<Users> lstUsers=query.getResultList();
		return lstUsers;
	}

	
	@Override
	public Users get(int id) {
		Session currentSession=entitymanager.unwrap(Session.class);		
		return currentSession.get(Users.class, id);
	}

	@Override
	public Users findUserByEmail(String email) {
		Session currentSession=entitymanager.unwrap(Session.class);	
		Query<Users> query= currentSession.createQuery("from Users where email = ?1",Users.class)
							.setParameter(1, email);
		List<Users> lstUsers = query.getResultList();
		return lstUsers.size()>0?lstUsers.get(0):null;
	}


	@Override
	public void save(Users user) {
		// TODO Auto-generated method stub
		Session currentSession=entitymanager.unwrap(Session.class);
		Set<Roles> roles=new HashSet<Roles>() 		
		{{
			add(currentSession.get(Roles.class, 2));
		}};
		//roles.add(currentSession.get(Roles.class, 2));
		user.setActive(1);
		user.setRoles(roles);
		currentSession.save(user);
		
	}

}
