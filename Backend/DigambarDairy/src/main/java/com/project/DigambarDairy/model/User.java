package com.project.DigambarDairy.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
@Entity
@Table (name = "users")
public class User {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, length = 100)
	    private String name;

	    @Email(message = "Invalid email format")
	    @Column(nullable = false, unique = true, length = 100)
	    private String email;

	    @Size(min = 6, message = "Password must be at least 6 characters")
	    @Column(nullable = false, length = 255)
	    private String password;

	    @Transient
	    private String confirmPassword;
	    
	    @Column(length = 255)
	    private String address;

	    @Column(name = "mobile_number", nullable = false, unique = true, length = 15)
	    private String mobileNumber;

	    @Enumerated(EnumType.STRING) 
	    @Column(nullable = false, columnDefinition = "ENUM('BUSINESS','CUSTOMER') default 'CUSTOMER'")
	    private Role role = Role.CUSTOMER;

	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt = LocalDateTime.now();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", confirmPassword=" + confirmPassword + ", address=" + address + ", mobileNumber=" + mobileNumber
					+ ", role=" + role + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
		}

		public User(Long id, String name, String email, String password, String confirmPassword, String address,
				String mobileNumber, Role role, LocalDateTime createdAt, LocalDateTime updatedAt) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.confirmPassword = confirmPassword;
			this.address = address;
			this.mobileNumber = mobileNumber;
			this.role = role;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
		}

		public User() {
		}   
		
	    
}
