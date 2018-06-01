package com.ar.redbee.socialMediaAggregator_Daemon.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ar.redbee.socialMediaAggregator_Daemon.entity.TopicEntity;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Integer>
{
	@Query(value= "SELECT t FROM TopicEntity t WHERE t.userName = ?1")
	List<TopicEntity> getPostByUserName(String userName);
}