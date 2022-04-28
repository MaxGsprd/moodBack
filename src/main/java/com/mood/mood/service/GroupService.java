package com.mood.mood.service;

import com.mood.mood.dto.in.GroupForm;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.dto.out.GroupUserDetails;
import com.mood.mood.model.Group;
import com.mood.mood.model.User;
import com.mood.mood.repository.GroupRepository;
import com.mood.mood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class GroupService implements IGroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;


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
    public GroupDetails find(Integer id) {
        Group group = groupRepository.findById(id).orElse(null);

        GroupDetails groupDetails = new GroupDetails();

        assert group != null;
        groupDetails.setTitle(group.getTitle());
        groupDetails.setUsers(group.getUsers()
                .stream()
                .map(this::userDtoToGroup)
                .collect(Collectors.toList())
        );

        return groupDetails;
    }

    @Override
    public GroupDetails create(GroupForm groupForm, Integer userId) {
        Group group = new Group();
        GroupDetails groupDetails = new GroupDetails();

        User user = userRepository.findById(userId).orElse(null);

        assert user != null;
        group.setTitle(groupForm.getTitle());
        group.addUser(user);
        group.setCreatedDate(LocalDateTime.now());
        group.setUpdatedDate(LocalDateTime.now());
        groupRepository.save(group);

        groupDetails.setTitle(group.getTitle());
        groupDetails.setUsers(group.getUsers()
                .stream()
                .map(this::userDtoToGroup)
                .collect(Collectors.toList())
        );

        return groupDetails;
    }

    @Override
    public GroupDetails inviteUser(Integer groupId,Integer userId) {
        Group group = groupRepository.findById(groupId).orElse(null);

        User user = userRepository.findById(userId).orElse(null);

        assert group != null && user != null;
        group.addUser(user);
        user.addGroup(group);

        groupRepository.save(group);
        userRepository.save(user);

        GroupDetails groupDetails = new GroupDetails();

        groupDetails.setTitle(group.getTitle());
        groupDetails.setUsers(group.getUsers()
                .stream()
                .map(this::userDtoToGroup)
                .collect(Collectors.toList())
        );

        return groupDetails;
    }

    @Override
    public GroupDetails removeUser(Integer groupId, Integer userId) {
        Group group = groupRepository.findById(groupId).orElse(null);

        User user = userRepository.findById(userId).orElse(null);

        assert group != null && user != null;
        group.removeUser(user);

        groupRepository.save(group);

        GroupDetails groupDetails = new GroupDetails();

        groupDetails.setTitle(group.getTitle());
        groupDetails.setUsers(group.getUsers()
                .stream()
                .map(this::userDtoToGroup)
                .collect(Collectors.toList())
        );

        return groupDetails;
    }

    @Override
    public boolean delete(Integer id) {
        Group group = groupRepository.findById(id).orElse(null);

        if(group == null) return false;

        groupRepository.delete(group);

        return true;

    }

    @Override
    public GroupDetails rename(Integer id, String name) {
        Group group = groupRepository.findById(id).orElse(null);

        GroupDetails groupDetails = new GroupDetails();

        assert group != null;
        group.setTitle(name);
        groupDetails.setTitle(group.getTitle());
        groupDetails.setUsers(group.getUsers()
                .stream()
                .map(this::userDtoToGroup)
                .collect(Collectors.toList())
        );

        groupRepository.save(group);

        return groupDetails;
    }
}
