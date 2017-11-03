package qcryptic.sphin.enums;

/**
 * Created by Kyle on 10/8/2017.
 */
public enum SettingsEnum {

    ABOUT("About Sphin", "Information about this installation"),
    NETWORK("Network Settings", "Modify application network settings"),
    USERS("Users and Invitations", "Manage user groups"),
    CONNECTIONS("Configure Connections", "Setup connections to external services");

    private String title;
    private String subtitle;

    SettingsEnum(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

}
