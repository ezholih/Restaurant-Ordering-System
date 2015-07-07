package edu.neu.ros.domain;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name="userName", 
	unique=true, length=30)
	private String userName;
	
	@NotEmpty 
	private String password;
	
	@NotEmpty
	private String role;

	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;

	@Transient
	private MultipartFile photo;
	
	@Lob private byte[] photoBytes;
	
	private String photoContentType; 
	
	private String photoFilename;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = FoodOrder.class)
//	@JoinTable(name = "user_foodorder",
//	joinColumns = @JoinColumn( name="user_id"),
//    inverseJoinColumns = @JoinColumn( name="foodorder_id"))
	private Set<FoodOrder> orders;

//    public enum Type{
//        ROLE_MANAGER("Administrator"),
//        ROLE_WAITER("Waiter"),
//        ROLE_CHEF("Chef");
//        
//        private String value;
//        private Type(String value){
//            this.value = value;
//        }
//        public String getValue(){
//            return value;
//        }
//    }
	
	public Long getId() {
		return id;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
		
		setPhotoContentType(photo.getContentType());
		setPhotoFilename(photo.getOriginalFilename());
		try {
			setPhotoBytes(photo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	public String getPhotoFilename() {
		return photoFilename;
	}
	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}
	public byte[] getPhotoBytes() {
		return photoBytes;
	}
	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}
	public Long assignId() {
		this.id = idSequence.incrementAndGet();
		return id;
	}
	
	private static final AtomicLong idSequence = new AtomicLong();

}
