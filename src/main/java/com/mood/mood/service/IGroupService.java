package com.mood.mood.service;

import com.mood.mood.dto.in.GroupForm;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.model.Group;

public interface IGroupService {
    GroupDetails find(Integer id);
    GroupDetails create(GroupForm group, Integer userId);
    GroupDetails inviteUser(Integer groupId, Integer userId);
    GroupDetails removeUser(Integer groupId,Integer userId);
    boolean delete(Integer id);
    GroupDetails rename(Integer id, String name);
}
