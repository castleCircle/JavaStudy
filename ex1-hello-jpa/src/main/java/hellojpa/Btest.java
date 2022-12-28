package hellojpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Btest {

  @Id @GeneratedValue
  private Long id;

  @OneToMany
  @JoinColumn(name = "Btest_ID")
  private List<Atest> atestList = new ArrayList<>();


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Atest> getAtestList() {
    return atestList;
  }

  public void setAtestList(List<Atest> atestList) {
    this.atestList = atestList;
  }
}
