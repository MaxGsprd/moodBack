package com.mood.mood.service;

import com.mood.mood.dto.in.UserForm;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.dto.out.GroupUserDetails;
import com.mood.mood.dto.out.InvitationEvenementDetails;
import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.dto.out.UserDetails;
import com.mood.mood.model.Category;
import com.mood.mood.model.Group;
import com.mood.mood.model.Role;
import com.mood.mood.model.User;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.RoleRepository;
import com.mood.mood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoleRepository roleRepository;

    private GroupUserDetails userDtoToGroup(User userEntity) {
        GroupUserDetails groupUserDetail = new GroupUserDetails();
        groupUserDetail.setId(userEntity.getId());
        groupUserDetail.setName(userEntity.getName());
        groupUserDetail.setFirstname(userEntity.getFirstname());
        groupUserDetail.setEmail(userEntity.getEmail());
        groupUserDetail.setPhone(userEntity.getPhone());

        return groupUserDetail;
    }

    @Override
    public UserDetails find(Integer id) {
        User user = userRepository.findById(id).orElse(null);

        List<GroupDetails> groupDetailsList = new ArrayList<>();
        assert user != null;
        for (Group group : user.getGroups()) {
            groupDetailsList.add(
                    new GroupDetails(
                            group.getTitle(),
                            group.getUsers().stream()
                                    .map(this::userDtoToGroup)
                                    .collect(Collectors.toList()),
                            // invitation repository findbyGroup
                            new ArrayList<InvitationEvenementDetails>()
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
