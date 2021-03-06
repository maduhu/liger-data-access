package net.canadensys.dataportal.occurrence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "publisher")
@SequenceGenerator(name = "publisher_id_seq", sequenceName = "publisher_id_seq", allocationSize = 1)
public class PublisherModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_id_seq")
	private Integer auto_id;
	
	private String name;
	private String description;
	private String address;
	private String city;
	private String administrative_area;
	private String postal_code;
	private String homepage;
	private String email;
	private String phone;
	private String logo_url;
	private Double decimallatitude;
	private Double decimallongitude;
	private Integer record_count;

	/**
	 * FetchType.EAGER will make contacts be always loaded. TODO: Add deepLoad condition to load() in DAO
	 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "publisher_fkey", nullable = true)
	private Set<ContactModel> contacts;
	
	/**
	 * FetchType.EAGER will make resources be always loaded. TODO: Add deepLoad condition to load() in DAO
	 */
	@JsonManagedReference
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<DwcaResourceModel> resources;

	/**
	 * Class constructor
	 */
	public PublisherModel() {
		// Initialize data structures:
		resources = new HashSet<DwcaResourceModel>();
		contacts = new HashSet<ContactModel>();
	}

	/**
	 * Add a ContactModel to the contact list.
	 * The reference to this publisherInformationModel instance will be set on the provided contact.
	 * 
	 * @param contact
	 */
	public void addContact(ContactModel contact) {
		contacts.add(contact);
	}
	
	/**
	 * Add a ResourceModel to the resource list.
	 * The reference to this ResourceInformationModel instance will be set on the provided contact.
	 * 
	 * @param contact
	 */
	public void addResource(DwcaResourceModel resource) {
		resource.setPublisher(this);
		resources.add(resource);
	}


	/** Getters and setters: **/

	public Integer getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(Integer auto_id) {
		this.auto_id = auto_id;
	}

	public Set<ContactModel> getContacts() {
		return contacts;
	}

	/**
	 * Contact list setter mainly used for Java Beans compliance.
	 * If you want to add contact, consider using addContact(ContactModel)
	 * 
	 * @param contacts
	 */
	public void setContacts(Set<ContactModel> contacts) {
		this.contacts = contacts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdministrative_area() {
		return administrative_area;
	}

	public void setAdministrative_area(String administrative_area) {
		this.administrative_area = administrative_area;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public Double getDecimallatitude() {
		return decimallatitude;
	}

	public void setDecimallatitude(Double decimallatitude) {
		this.decimallatitude = decimallatitude;
	}

	public Double getDecimallongitude() {
		return decimallongitude;
	}

	public void setDecimallongitude(Double decimallongitude) {
		this.decimallongitude = decimallongitude;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRecord_count() {
		return record_count;
	}

	public void setRecord_count(Integer record_count) {
		this.record_count = record_count;
	}

	public Set<DwcaResourceModel> getResources() {
		return resources;
	}

	public void setResources(Set<DwcaResourceModel> resources) {
		this.resources = resources;
	}
}
