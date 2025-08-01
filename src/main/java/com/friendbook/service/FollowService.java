package com.friendbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendbook.model.Follow;
import com.friendbook.model.User;
import com.friendbook.repository.FollowRepository;

@Service
public class FollowService {

	@Autowired
	private FollowRepository followRepository;
	
	public boolean isFollowing(User follower, User following) {
		return followRepository.existsByFollowerAndFollowing(follower, following);
	}
	
	public void follow(User follower, User following) {
		if(!isFollowing(follower,following)) {
			Follow follow = new Follow();
			follow.setFollower(follower);
			follow.setFollowing(following);
			followRepository.save(follow);
		}
	}
	
	public void unfollow(User follower, User following) {
		followRepository.findByFollowerAndFollowing(follower, following).ifPresent(followRepository::delete);
	}
	
	public long countFollowers(User user) {
		return followRepository.countByFollower(user);
	}
	
	public long countFollowing(User user) {
		return followRepository.countByFollowing(user);
	}
	
	public List<User> getFollowers(User user){
		return followRepository.findByFollowing(user).stream().map(Follow::getFollower).toList();
	}
	
	public List<User> getFollowing(User user){
		return followRepository.findByFollower(user).stream().map(Follow::getFollowing).toList();
	}
}
