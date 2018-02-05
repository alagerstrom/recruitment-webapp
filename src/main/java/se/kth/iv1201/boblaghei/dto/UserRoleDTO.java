package se.kth.iv1201.boblaghei.dto;

/**
 * DTO representing entity class <code>UserRole</code>.
 */
public class UserRoleDTO {

    private long id;
    private UserDTO user;
    private RoleDTO role;

    public UserRoleDTO(UserDTO user, RoleDTO role) {
        this.user = user;
        this.role = role;
    }

    public UserRoleDTO() {
    }

    public UserRoleDTO(long id, UserDTO user, RoleDTO role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
