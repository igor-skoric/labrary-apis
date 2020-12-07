package com.skb.course.apis.labraryapis.publisher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface publisherRepository extends CrudRepository<PublisherEntity, Integer>{
	

}
