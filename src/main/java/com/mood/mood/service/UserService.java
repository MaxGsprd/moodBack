package com.mood.mood.service;

import com.mood.mood.controller.ImageController;
import com.mood.mood.controller.LocalisationController;
import com.mood.mood.dto.in.UserForm;
import com.mood.mood.dto.out.*;
import com.mood.mood.model.*;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.RoleRepository;
import com.mood.mood.repository.UserRepository;
import com.mood.mood.util.LocalisationUtil;
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

    @Autowired
    private LocalisationController localisationController;
    @Autowired
    private LocalisationUtil localisationUtil;
    @Autowired
    private ImageController imageController;

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

        LocalisationDetails localisationDetails;

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

        Localisation localisation = user.getLocalisation();
        UserDetails userDetails = new UserDetails();
        userDetails.setId(user.getId());
        userDetails.setName(user.getName());
        userDetails.setFirstname(user.getFirstname());
        userDetails.setBirthDate(user.getBirthdate().toString());
        userDetails.setEmail(user.getEmail());
        userDetails.setPhone(user.getPhone());
        userDetails.setRole(user.getRole().getTitle());
        // args in api response
        try {
            localisationDetails = localisationController.getAddressFromLatLon(String.valueOf(localisation.getLatitude()), String.valueOf(localisation.getLongitude()));
        } catch (Exception e) {
            throw new RuntimeException();
        }
        userDetails.setAddress(localisationDetails);
        userDetails.setImageID(user.getImage().getId());
        userDetails.setCategory(user.getMood().getId());
        userDetails.setGroup(groupDetailsList);

        return userDetails;
    }

    @Override
    public UserDetails findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        LocalisationDetails localisationDetails;

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

        Localisation localisation = user.getLocalisation();


        UserDetails userDetails = new UserDetails();
                userDetails.setId(user.getId());
                userDetails.setName(user.getName());
                userDetails.setFirstname(user.getFirstname());
                userDetails.setBirthDate(user.getBirthdate().toString());
                userDetails.setEmail(user.getEmail());
                userDetails.setPhone(user.getPhone());
                userDetails.setRole(user.getRole().getTitle());
                // args in api response
                try {
                    localisationDetails = localisationController.getAddressFromLatLon(String.valueOf(localisation.getLatitude()), String.valueOf(localisation.getLongitude()));
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                userDetails.setAddress(localisationDetails);
                userDetails.setImageID(user.getImage().getId());
                userDetails.setCategory(user.getMood().getId());
                userDetails.setGroup(groupDetailsList);


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
        //updatedUser.setBirthdate(user.getBirthdate());

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
