package ita.support.dba.entities.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "custmer_id", nullable = false)
	private Long custmerId;

	@Column(name = "name", nullable = true, length = 50)
	private String name;

	@Column(name = "phone", nullable = true, length = 50)
	private String phone;

	@Column(name = "phone_model", nullable = true, length = 50)
	private String phoneModel;

	public Customer() {

	}

	public Long getCustmerId() {
		return custmerId;
	}

	public void setCustmerId(Long custmerId) {
		this.custmerId = custmerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((phoneModel == null) ? 0 : phoneModel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (phoneModel == null) {
			if (other.phoneModel != null)
				return false;
		} else if (!phoneModel.equals(other.phoneModel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [custmerId=" + custmerId + ", name=" + name + ", phone=" + phone + ", phoneModel=" + phoneModel
				+ "]";
	}

}
