package com.mood.mood.service;


import com.mood.mood.repository.InvitationRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class InvitationService implements IInvitationService {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final InvitationRepository invitationRepository;
}
