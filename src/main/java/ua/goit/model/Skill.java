package ua.goit.model;

/**
 * Created by Main Server on 16.12.2016.
 */
public class Skill implements Model {
    private int skillId;
    private String skillName;

    public Skill(int skillId, String skillName) {
        this.skillId = skillId;
        this.skillName = skillName;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "Skill : " +
                "Id : " + skillId +
                ", Name : " + skillName + '\'';
    }
}
