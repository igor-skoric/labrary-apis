package com.skb.course.apis.labraryapis.publisher;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.skb.course.apis.labraryapis.exception.LibraryResourceAlreadyExistException;
import com.skb.course.apis.labraryapis.exception.LibraryResourceNotFoundException;

@Service
public class PublisherService {

	private publisherRepository publisherRepository;
	
	public PublisherService(publisherRepository publisherRepository) {
		this.publisherRepository=publisherRepository;
	}
	
	public void addPublisher(Publisher publisherToBeAdded) 
			throws LibraryResourceAlreadyExistException{
	
		PublisherEntity publisherEntity = new PublisherEntity(
				publisherToBeAdded.getName(),
				publisherToBeAdded.getEmailId(),
				publisherToBeAdded.getPhoneNumber());
		
		PublisherEntity addedPublisher=null;
		try {
			addedPublisher = publisherRepository.save(publisherEntity);
		} catch (DataIntegrityViolationException e) {
			throw new LibraryResourceAlreadyExistException("Publisher already exists!!");
		}
		
		publisherToBeAdded.setPublisherId(addedPublisher.getPublisherId());
	}

	public Publisher getPublisher(Integer publisherId) throws LibraryResourceNotFoundException {
		
		Optional<PublisherEntity> publisherEntity=publisherRepository.findById(publisherId);
		
		Publisher publisher=null;
		
		if(publisherEntity.isPresent()) {
			
			PublisherEntity pe=publisherEntity.get();
			publisher=createPublisherFromEntity(pe);
		}else {
			throw new LibraryResourceNotFoundException("Publisher Id: " + publisherId + " Not Found");
		}
		return publisher;
	}

	private Publisher createPublisherFromEntity(PublisherEntity pe) {
		 
		return new Publisher(pe.getPublisherId(), pe.getName(),pe.getEmailId(),pe.getPhoneNumber());
	}
	
}
