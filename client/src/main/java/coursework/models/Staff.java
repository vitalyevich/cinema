package coursework.models;

import java.io.Serializable;

public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String phone;

    private Character gender;

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Staff() { }

    public Staff(int id, String lastName, String firstName, String middleName, String phone,
                 Character gender) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
        this.gender = gender;
    }

    public Staff(String lastName, String firstName, String middleName, String phone,
                 Character gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
        this.gender = gender;
    }

}