package com.skb.course.apis.labraryapis.publisher;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.skb.course.apis.labraryapis.exception.LibraryResourceAlreadyExistException;

@Service
public class PublisherService {

	private publisherRepository publisherRepository;
	
	public PublisherService(publisherRepository publisherRepository) {
		this.publisherRepository=publisherRepository;
	}
	
	public Publisher addPublisher(Publisher publisherToBeAdded) 
			throws LibraryResourceAlreadyExistException{
	
		PublisherEntity publisherEntity = new PublisherEntity(
				publisherToBeAdded.getName(),
				publisherToBeAdded.getEmailId(),
				publisherToBeAdded.getPhoneNumber());
		
		PublisherEntity addedPublisher=null;
		
		try {
			addedPublisher = publisherRepository.save(addedPublisher);
		} catch (DataIntegrityViolationException e) {
			throw new LibraryResourceAlreadyExistException("Publisher already exists!!");
		}
		publisherToBeAdded.setPublisherId(addedPublisher.getPublisherId());
		
		return publisherToBeAdded;
	}
	
}
