package com.blogapplication.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id" )
	private Integer categoryId;
	
	@Column(name = "Title")
	@NotEmpty(message = "this feilds must be required !!")
	private String CategoryTitle;
	
	@NotEmpty(message = "feilds must be required !!")
	@Column(name = "discriptions")
	private String categoryDescriptions;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<Post> post=new ArrayList<>();
	
	
	
}
