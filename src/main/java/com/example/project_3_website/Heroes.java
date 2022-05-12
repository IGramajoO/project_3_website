package com.example.project_3_website;

import javax.persistence.*;

@Entity
public class Heroes {
    @Id
    @Column(name = "heroId", nullable = false)
    private int heroId;
    @Column(name = "intelligence", nullable = false)
    private int intelligence;
    @Column(name = "strength", nullable = false)
    private int strength;
    @Column(name = "speed", nullable = false)
    private int speed;
    @Column(name = "durability", nullable = false)
    private int durability;
    @Column(name = "power", nullable = false)
    private int power;
    @Column(name = "combat", nullable = false)
    private int combat;

    public int getId() {
        return heroId;
    }

    public void setId(int id) {
        this.heroId = id;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    @Override
    public String toString() {
        return "Heroes{" +
                "id=" + heroId +
                ", intelligence=" + intelligence +
                ", strength=" + strength +
                ", speed=" + speed +
                ", durability=" + durability +
                ", power=" + power +
                ", combat=" + combat +
                '}';
    }
}
