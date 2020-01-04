package com.chat.messango.services;

import java.util.List;

import com.chat.messango.model.Users;

public interface UserService {
	List<Users> get();
	Users get(int id);
	Users findUserByEmail(String email);
	void save(Users user);
}
