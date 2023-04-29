package com.bookie.rest.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookie.rest.api.entity.Profile;
import com.bookie.rest.api.repositories.ProfileRepository;
import com.bookie.rest.api.service.ProfileService;

public class ProfileServiceImpl implements ProfileService{

	@Autowired
	public ProfileRepository profileRepository;
	
	@Override
	public void saveProfile(Profile profile) {
		this.profileRepository.save(profile);
	}

	@Override
	public Profile getProfileById(Long ProfileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profile> getAllProfile() {
		// TODO Auto-generated method stub
		return null;
	}

}
