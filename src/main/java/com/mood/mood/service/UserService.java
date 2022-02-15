package com.mood.mood.service;

import com.mood.mood.dto.in.UserForm;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.dto.out.InvitationDetails;
import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.dto.out.UserDetails;
import com.mood.mood.model.Category;
import com.mood.mood.model.Group;
import com.mood.mood.model.Role;
import com.mood.mood.model.User;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.RoleRepository;
import com.mood.mood.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails find(Integer id) {
        User user = userRepository.findById(id).orElse(null);

        List<GroupDetails> groupDetailsList = new ArrayList<>();
        assert user != null;
        for (Group group : user.getGroups()) {
            groupDetailsList.add(
                    new GroupDetails(
                            group.getTitle(),
                            group.getUsers(),
                            // invitation repository findbyGroup
                            new ArrayList<InvitationDetails>()
                    )
            );
        }

        UserDetails userDetails = new UserDetails(
                user.getName(),
                user.getFirstname(),
                user.getBirthdate().toString(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().getTitle(),
                // args in api response
                new LocalisationDetails(),
                user.getMood().getId(),
                groupDetailsList
        );

        return userDetails;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Integer id, UserForm user) throws Exception {
        User updatedUser = userRepository.findById(id).orElse(null);

        if(updatedUser == null) return null;

        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setBirthdate(user.getBirthdate());

        return userRepository.save(updatedUser);
    }

    @Override
    public User updateMood(Integer id, Integer mood) {
        User updatedUser = userRepository.findById(id).orElse(null);
        Category newMood = categoryRepository.findById(mood).orElse(null);

        if(updatedUser == null) return null;

        updatedUser.setMood(newMood);

        return userRepository.save(updatedUser);
    }

    @Override
    public User updateRole(Integer id, Integer role) {
        User updatedUser = userRepository.findById(id).orElse(null);
        Role newRole = roleRepository.findById(role).orElse(null);

        if(updatedUser == null) return null;

        updatedUser.setRole(newRole);

        return userRepository.save(updatedUser);
    }

    @Override
    public boolean delete(Integer id) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) return false;

        userRepository.delete(user);

        return true;
    }
}
