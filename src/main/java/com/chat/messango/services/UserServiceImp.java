package com.chat.messango.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.messango.model.Users;
import com.chat.messango.repository.UsersRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UsersRepository userRepo;
	@Transactional
	@Override
	public List<Users> get() {
		return userRepo.get();
	}
	
	@Override
	public Users findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findUserByEmail(email);
	}
	
	@Transactional
	@Override
	public Users get(int id) {
		// TODO Auto-generated method stub
		return userRepo.get(id);
	}
	
	@Transactional
	@Override
	public void save(Users user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}

}
