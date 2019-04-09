package model;

public class Worker {
    private int id;
    private String name;
    private String address1;
    private int cityID;
    private City city;
    private String telephoneNum;
    private int roleID;
    private Role role;
    private String email;


    public Worker(int id, String name, int cityID, int roleID, String email) {
        this(id, name, "", cityID, "", roleID, email);
    }

    public Worker(int id, String name, String address1, int cityID, String telephoneNum, int roleID, String email) {
        this.id = id;
        this.name = name;
        this.address1 = address1;
        this.cityID = cityID;
        this.telephoneNum = telephoneNum;
        this.roleID = roleID;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", cityID=" + cityID +
                ", city=" + city +
                ", telephoneNum='" + telephoneNum + '\'' +
                ", roleID=" + roleID +
                ", role=" + role +
                ", email='" + email + '\'' +
                '}';
    }
}
