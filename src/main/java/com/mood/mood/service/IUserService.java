package com.mood.mood.service;

import com.mood.mood.dto.out.UserDetails;
import com.mood.mood.model.User;

public interface IUserService {
    UserDetails find(String email);
    User save(User user);
    User update(Integer id,User user) throws Exception;
    void delete(User user);
}
