import java.util.ArrayList;

public class Administrador extends User {

    private ArrayList<Permissions>permissions;
    private boolean isSuperAdmin;

    public Administrador(Profile profile, String username, String password,ArrayList<Permissions> permissions, Boolean isSuperAdmin) {
        super(profile, username, password);
        this.permissions = permissions;
        this.isSuperAdmin = isSuperAdmin;

    }

    public ArrayList<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permissions> permissions) {
        this.permissions = permissions;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public enum Permissions{
        READ,WRITE,DELETE
    }
}
