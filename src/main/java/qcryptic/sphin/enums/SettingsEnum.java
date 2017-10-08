package qcryptic.sphin.enums;

/**
 * Created by Kyle on 10/8/2017.
 */
public enum SettingsEnum {

    ABOUT("About Sphin", "Information about this installation", true),
    NETWORK("Network Settings", "Modify application network settings", true),
    USERS("Users", "Adjust user groups", true);

    private String title;
    private String subtitle;
    private boolean showSaveBtn;

    SettingsEnum(String title, String subtitle, boolean showSaveBtn) {
        this.title = title;
        this.subtitle = subtitle;
        this.showSaveBtn = showSaveBtn;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean isShowSaveBtn() {
        return showSaveBtn;
    }
}
