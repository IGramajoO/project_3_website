package com.example.project_3_website;

import javax.persistence.*;

@Entity
@Table(name = "heroes")
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


    public Heroes() {
        this.heroId = 0;
        this.intelligence = 0;
        this.strength = 0;
        this.speed = 0;
        this.durability = 0;
        this.power = 0;
        this.combat = 0;
    }

    public Heroes(int heroId, int intelligence, int strength, int speed, int durability, int power, int combat) {
        this.heroId = heroId;
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.power = power;
        this.combat = combat;
    }

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
