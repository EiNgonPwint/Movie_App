package iss.workshop.adproject_team5_movieapp.Model;

import java.io.Serializable;
import java.util.List;

//public class User {
//
//
//    private int id;
//    private String name;
//    private String password;
//    private boolean isActive;
//    //private RoleEnum role;
//    private List<Favourite> favourites;
//
//
//    public User(int id,String name, String password, boolean isActive) {
//        this.id=id;
//        this.name = name;
//        this.password = password;
//        this.isActive = isActive;
//        //this.role=role;
//    }
//    public User(int id, String name){
//        this.id=id;
//        this.name=name;
//    }
//
////  public User(int userId) {
////    this.userId = userId;
////  }
//
//    public int getUserId() {
//        return id;
//    }
//
//    public void setUserId(int userId) {
//        this.id = userId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean getIsActive(){
//        return isActive;
//    }
//
//    public void setIsActive(boolean isActive){
//        this.isActive = isActive;
//    }
////    public RoleEnum getRole(){
////        return role;
////    }
////    public void setRole(RoleEnum role){
////        this.role=role;
////    }
//}
public class User implements Serializable {


    private int id;
    private String name;
    private String password;
    private boolean isActive;
    private String email;
    //private RoleEnum role;
    private List<Favourite> favourites;


    public User(int id,String name, String password, boolean isActive) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.isActive = isActive;
        //this.role=role;
    }
    public User(int id, String name){
        this.id=id;
        this.name=name;
    }

    public User(String name,String email,String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User() {

    }

//  public User(int userId) {
//    this.userId = userId;
//  }

    public int getUserId() {
        return id;
    }

    public void setUserId(int userId) {
        this.id = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive(){
        return isActive;
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
    //    public RoleEnum getRole(){
//        return role;
//    }
//    public void setRole(RoleEnum role){
//        this.role=role;
//    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}

