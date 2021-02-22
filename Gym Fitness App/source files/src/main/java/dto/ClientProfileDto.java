package dto;

public class ClientProfileDto {

    private String name;

    private String birthday;

    private String sex;

    private int progress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String toString() {
        return "Name: " + this.name + "Birthday: " + this.birthday + "Sex: " + this.sex;
    }
}
