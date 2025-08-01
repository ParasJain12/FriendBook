package com.friendbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.friendbook.model.User;
import com.friendbook.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
	boolean existsByFollowerAndFollowing(User follower, User following);
	Optional<Follow> findByFollowerAndFollowing(User follower, User following);
	List<Follow> findByFollower(User follower);
	List<Follow> findByFollowing(User following);
	
	Long countByFollower(User user);
	Long countByFollowing(User user);
}
