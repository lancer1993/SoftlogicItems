package beans;
// Generated Dec 22, 2020 11:29:41 PM by Hibernate Tools 4.3.1

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

    private int idRole;
    private String roleName;

    public Role() {
    }

    public Role(int idRole) {
        this.idRole = idRole;
    }

    public int getIdRole() {
        return this.idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
