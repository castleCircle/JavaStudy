package hellojpa;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

  @Id @GeneratedValue
  private Long id;

  @Embedded
  private Address address;

  public Address getAddress() {
    return address;
  }

}
