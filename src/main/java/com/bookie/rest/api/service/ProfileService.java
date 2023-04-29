package com.bookie.rest.api.service;

import java.util.List;

import com.bookie.rest.api.entity.Profile;

public interface ProfileService {

	public void saveProfile(Profile profile);

	public Profile getProfileById(Long ProfileId);

	public List<Profile> getAllProfile();
}
