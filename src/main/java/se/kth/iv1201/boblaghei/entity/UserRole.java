package se.kth.iv1201.boblaghei.entity;


import se.kth.iv1201.boblaghei.dto.RoleDTO;
import se.kth.iv1201.boblaghei.dto.UserDTO;
import se.kth.iv1201.boblaghei.dto.UserRoleDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private long userRoleid;


    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;

    public UserRole() {
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public long getUserRoleid() {
        return userRoleid;
    }

    public void setUserRoleid(long userRoleid) {
        this.userRoleid = userRoleid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleid=" + userRoleid +
                ", user=" + user +
                ", role=" + role +
                '}';
    }

    public UserRoleDTO getDTO() {
        return new UserRoleDTO(userRoleid, getUser().getDTO(), getRole().getDTO());
    }
}
