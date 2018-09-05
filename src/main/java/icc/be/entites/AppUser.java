package icc.be.entites;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
public class AppUser implements UserDetails {
	public  enum AppRole{

	    ADMIN("Administrateur"),
	    USER("Simple utilisateur");
	    

	    private final String displayName;

	    AppRole(String displayName) {
	        this.displayName = displayName;
	    }

	    public String getDisplayName() {
	        return displayName;
	    }
	}
	
	
	private static final long serialVersionUID = 1L;
		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		@Column(unique=true)
		@NotNull
		
	private String username;
		@NotNull
		
	private String password;
	private String nom;
	private String prenom;
	private String mail;
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private AppRole role;
	@OneToMany(mappedBy="user")
	private Collection<Reservation> reservations;
		
	public AppUser() {}
	
	
		public AppUser(@NotNull String username, @NotNull String password, String nom, String prenom, String mail) {
		super();
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}

		
		public String getNom() {
			return nom;
		}


		public void setNom(String nom) {
			this.nom = nom;
		}


		public String getPrenom() {
			return prenom;
		}


		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}


		public String getMail() {
			return mail;
		}


		public void setMail(String mail) {
			this.mail = mail;
		}


		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		@JsonIgnore
		public String getPassword() {
			return password;
		}
		@JsonSetter
		public void setPassword(String password) {
			this.password = password;
		}
		public AppRole getRole() {
			return role;
		}
		public void setRole(AppRole role) {
			this.role = role;
		}
		
		
		public Collection<Reservation> getReservations() {
			return reservations;
		}


		public void setReservations(Collection<Reservation> reservations) {
			this.reservations = reservations;
		}


		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Arrays.asList(new SimpleGrantedAuthority(role.name()));
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
		
		
		
		
}
