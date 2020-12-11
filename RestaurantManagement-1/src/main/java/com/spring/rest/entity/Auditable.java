package com.spring.rest.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

	
	
	 	@LastModifiedDate
	    @Column(name = "last_modified_date")
	    private LocalDate lastModifiedDate;

		public LocalDate getLastModifiedDate() {
			return lastModifiedDate;
		}

		public void setLastModifiedDate(LocalDate lastModifiedDate) {
			lastModifiedDate = LocalDate.now();
			this.lastModifiedDate = lastModifiedDate;
		}
	 	
		  
}
