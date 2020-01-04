package com.chat.messango.repository;

import java.util.List;
import com.chat.messango.model.Users;

public interface UsersRepository {
List<Users> get();
Users get(int id);
Users findUserByEmail(String email);
void save(Users user);
}
