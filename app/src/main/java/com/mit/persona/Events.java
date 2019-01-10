package com.mit.persona;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "events")
public class Events {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "event_img")
    private String img;

    public void setImg(String img) {
        this.img = img;
    }


    @ColumnInfo(name = "event_name")
    private String name;

    @ColumnInfo(name = "event_desc")
    private String description;

    @ColumnInfo(name = "event_date")
    private String date;

    @ColumnInfo(name = "event_time")
    private String time;

    @ColumnInfo(name = "event_venue")
    private String venue;

    @ColumnInfo(name = "event_category")
    private String category;

    @ColumnInfo(name = "event_type")
    private String type;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ColumnInfo(name = "event_individual")
    private Boolean individual;


    @ColumnInfo(name = "event_openForAll")
    private Boolean open_for_all;

    @ColumnInfo(name = "event_talentHub")
    private Boolean talentHub;

    @ColumnInfo(name = "event_mastiMagic")
    private Boolean mastiMagic;

    @ColumnInfo(name = "event_culturalEvents")
    private Boolean culturalEvents;

    @ColumnInfo(name = "event_manetIOD")
    private Boolean manetIOD;

    @ColumnInfo(name = "event_vedicSciences")
    private Boolean vedicSciences;


    @ColumnInfo(name = "event_management")
    private Boolean management;


    @ColumnInfo(name = "event_electronics")
    private Boolean electronics;


    @ColumnInfo(name = "event_civil")
    private Boolean civil;


    @ColumnInfo(name = "event_mech")
    private Boolean mech;


    @ColumnInfo(name = "event_aerospace")
    private Boolean aerospace;


    @ColumnInfo(name = "event_cse")
    private Boolean cse;


    @ColumnInfo(name = "event_footTech")
    private Boolean footTech;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Boolean getIndividual() {
        return individual;
    }

    public void setIndividual(Boolean individual) {
        this.individual = individual;
    }

    public Boolean getOpen_for_all() {
        return open_for_all;
    }

    public void setOpen_for_all(Boolean open_for_all) {
        this.open_for_all = open_for_all;
    }

    public Boolean getTalentHub() {
        return talentHub;
    }

    public void setTalentHub(Boolean talentHub) {
        this.talentHub = talentHub;
    }

    public Boolean getMastiMagic() {
        return mastiMagic;
    }

    public void setMastiMagic(Boolean mastiMagic) {
        this.mastiMagic = mastiMagic;
    }

    public Boolean getCulturalEvents() {
        return culturalEvents;
    }

    public void setCulturalEvents(Boolean culturalEvents) {
        this.culturalEvents = culturalEvents;
    }

    public Boolean getManetIOD() {
        return manetIOD;
    }

    public void setManetIOD(Boolean manetIOD) {
        this.manetIOD = manetIOD;
    }

    public Boolean getVedicSciences() {
        return vedicSciences;
    }

    public void setVedicSciences(Boolean vedicSciences) {
        this.vedicSciences = vedicSciences;
    }

    public Boolean getManagement() {
        return management;
    }

    public void setManagement(Boolean management) {
        this.management = management;
    }

    public Boolean getElectronics() {
        return electronics;
    }

    public void setElectronics(Boolean electronics) {
        this.electronics = electronics;
    }

    public Boolean getCivil() {
        return civil;
    }

    public void setCivil(Boolean civil) {
        this.civil = civil;
    }

    public Boolean getMech() {
        return mech;
    }

    public void setMech(Boolean mech) {
        this.mech = mech;
    }

    public Boolean getAerospace() {
        return aerospace;
    }

    public void setAerospace(Boolean aerospace) {
        this.aerospace = aerospace;
    }

    public Boolean getCse() {
        return cse;
    }

    public void setCse(Boolean cse) {
        this.cse = cse;
    }

    public Boolean getFootTech() {
        return footTech;
    }

    public void setFootTech(Boolean footTech) {
        this.footTech = footTech;
    }

    public String getImg() {
        return img;
    }


}
