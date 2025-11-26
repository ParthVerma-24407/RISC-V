package edu.univ.erp.domain;

public class User_info {
    private String username;
    private String role;
    private String password_hash;
    private String status;
    private String LastLogin;
    private String prevCreate;
    private String updatedAt;


    public User_info(String username, String role,String password_hash, String status, String LastLogin, String prevCreate, String updatedAt) {
        this.username = username;
        this.role = role;
        this.password_hash=password_hash;
        this.status=status;
        this.LastLogin=LastLogin;
        this.prevCreate=prevCreate;
        this.updatedAt=updatedAt;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username=username;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role){
        this.role=role;
    }
    public String getPassword_hash(){
        return password_hash;
    }
    public void setPassword_hash(String password_hash){
        this.password_hash=password_hash;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getLastLogin(){
        return LastLogin;
    }
    public void setLastLogin(String LastLogin){
        this.LastLogin=LastLogin;
    }
    public String getPrevCreate(){
        return prevCreate;
    }
    public void setPrevCreate(String prevCreate){
        this.prevCreate=prevCreate;
    }
    public String getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt){
        this.updatedAt=updatedAt;
    }
    public String toString(){
        return "Userinfo[username="+username+", role="+ role + ",status="+status+", Lastlogin="+LastLogin+", updatedAt="+updatedAt+"]";
    }
}
