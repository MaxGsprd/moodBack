package com.mood.mood.service;

import com.mood.mood.repository.UserRepository;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.dto.out.InvitationDetails;
import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.dto.out.UserDetails;
import com.mood.mood.model.Group;
import com.mood.mood.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails find(String email) {
        User user = userRepository.findByEmail(email);

        URL url =  new URL("https://api-adresse.data.gouv.fr/reverse/?lon="+user.getLocalisation().getLongitude()+"&lat="+user.getLocalisation().getLatitude()+"&type=housenumber&autocomplete=1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        String response = con.getResponseMessage();

        List<GroupDetails> groupDetailsList = new ArrayList<>();
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
    public User update(Integer id, User user) throws Exception {
        User updatedUser = userRepository.findById(id).orElse(null);

        if(updatedUser == null) return null;

        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setMood(user.getMood());
        updatedUser.setBirthdate(user.getBirthdate());

        return userRepository.save(updatedUser);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
