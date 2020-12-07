package com.skb.course.apis.labraryapis.publisher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skb.course.apis.labraryapis.exception.LibraryResourceAlreadyExistException;
import com.skb.course.apis.labraryapis.exception.LibraryResourceNotFoundException;

@RestController
@RequestMapping(path="/v1/publishers")
public class PublisherController {

	private PublisherService publisherService;
	
	
	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	
	@GetMapping(path="/{publisherId}")
	public ResponseEntity<?> getPublisher(@PathVariable Integer publisherId) {
		Publisher publisher=null;
		
		try {
			publisher = publisherService.getPublisher(publisherId);
		}catch(LibraryResourceNotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publisher,HttpStatus.OK);
	
	}
	
	@PostMapping
	public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher){
		try {
				publisherService.addPublisher(publisher);
		}catch(LibraryResourceAlreadyExistException e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(publisher,HttpStatus.CREATED);
	}
}
