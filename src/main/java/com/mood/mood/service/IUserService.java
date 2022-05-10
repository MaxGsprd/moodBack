package com.mood.mood.service;

import com.mood.mood.dto.in.UserForm;
import com.mood.mood.dto.out.UserDetails;
import com.mood.mood.model.User;

public interface IUserService {
    UserDetails find(Integer id);

    UserDetails findByEmail(String email);
    User save(User user);
    User update(Integer id, UserForm user) throws Exception;
    User updateMood(Integer id, Integer mood);
    User updateRole(Integer id, Integer role);
    boolean delete(Integer id);
}
