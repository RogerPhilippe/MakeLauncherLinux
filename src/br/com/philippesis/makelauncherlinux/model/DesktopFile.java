package br.com.philippesis.makelauncherlinux.model;

public class DesktopFile {

    private String mVersion;
    private String mName;
    private String mComment;
    private boolean mTerminal;
    private String mAppType;
    private String mIconPath;
    private String mExec;

    private DesktopFile(String version, String name, String comment, boolean terminal, String appType, String iconPath, String exec) {
        this.mVersion = version;
        this.mName = name;
        this.mComment = comment;
        this.mTerminal = terminal;
        this.mAppType = appType;
        this.mIconPath = iconPath;
        this.mExec = exec;
    }

    public static class Builder {

        private String version;
        private String name;
        private String comment;
        private boolean terminal;
        private String appType;
        private String iconPath;
        private String exec;

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setTerminal(boolean terminal) {
            this.terminal = terminal;
            return this;
        }

        public Builder setAppType(String appType) {
            this.appType = appType;
            return this;
        }

        public Builder setIconPath(String iconPath) {
            this.iconPath = iconPath;
            return this;
        }

        public Builder setExec(String exec) {
            this.exec = exec;
            return this;
        }

        public DesktopFile build() {
            return new DesktopFile(version, name, comment, terminal, appType, iconPath, exec);
        }

    }

    public String getmVersion() {
        return mVersion;
    }

    public String getmName() {
        return mName;
    }

    public String getmComment() {
        return mComment;
    }

    public boolean ismTerminal() {
        return mTerminal;
    }

    public String getmAppType() {
        return mAppType;
    }

    public String getmIconPath() {
        return mIconPath;
    }

    public String getmExec() {
        return mExec;
    }

}
