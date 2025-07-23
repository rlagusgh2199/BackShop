package maneger;

public class ManegerDTO {
    private String id1;
    private String id2;
    private String pwd1;
    private String alias;
    private String email;
    private String gender;
    private String favorite;
    private String address; // 새로운 필드 추가

    // 기존의 getter와 setter ...
    public String getId1() {
        return id1;
    }
    public void setId1(String id1) {
        this.id1 = id1;
    }
    public String getId2() {
        return id2;
    }
    public void setId2(String id2) {
        this.id2 = id2;
    }
    public String getPwd1() {
        return pwd1;
    }
    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getFavorite() {
        return favorite;
    }
    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
    public String getAddress() { // 새로운 getter
        return address;
    }
    public void setAddress(String address) { // 새로운 setter
        this.address = address;
    }
}
