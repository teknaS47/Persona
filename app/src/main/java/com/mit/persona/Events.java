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

    @ColumnInfo(name = "e_1_prize")
    private String e_1_prize;

    @ColumnInfo(name = "e_2_prize")
    private String e_2_prize;

    @ColumnInfo(name = "e_3_prize")
    private String e_3_prize;

    @ColumnInfo(name = "event_e_staff_1")
    private String event_e_staff_1;

    @ColumnInfo(name = "event_e_staff_1_email")
    private String event_e_staff_1_email;

    @ColumnInfo(name = "event_e_staff_1_phone")
    private String event_e_staff_1_phone;

    @ColumnInfo(name = "event_e_staff_2")
    private String event_e_staff_2;

    @ColumnInfo(name = "event_e_staff_2_email")
    private String event_e_staff_2_email;

    @ColumnInfo(name = "event_e_staff_2_phone")
    private String event_e_staff_2_phone;

    @ColumnInfo(name = "event_e_student_1")
    private String event_e_student_1;

    @ColumnInfo(name = "event_e_student_1_email")
    private String event_e_student_1_email;

    @ColumnInfo(name = "event_e_student_1_phone")
    private String event_e_student_1_phone;

    @ColumnInfo(name = "event_e_student_2")
    private String event_e_student_2;

    @ColumnInfo(name = "event_e_student_2_email")
    private String event_e_student_2_email;

    @ColumnInfo(name = "event_e_student_2_phone")
    private String event_e_student_2_phone;

    @ColumnInfo(name = "event_e_whatsappLink")
    private String event_e_whatsappLink;

    @ColumnInfo(name = "event_e_likes")
    private String event_e_likes;

    public String getE_rules() {
        return e_rules;
    }

    public void setE_rules(String e_rules) {
        this.e_rules = e_rules;
    }

    @ColumnInfo(name = "e_rules")
    private String e_rules;


    public String getEvent_e_staff_1() {
        return event_e_staff_1;
    }

    public void setEvent_e_staff_1(String event_e_staff_1) {
        this.event_e_staff_1 = event_e_staff_1;
    }

    public String getEvent_e_staff_1_email() {
        return event_e_staff_1_email;
    }

    public void setEvent_e_staff_1_email(String event_e_staff_1_email) {
        this.event_e_staff_1_email = event_e_staff_1_email;
    }

    public String getEvent_e_staff_1_phone() {
        return event_e_staff_1_phone;
    }

    public void setEvent_e_staff_1_phone(String event_e_staff_1_phone) {
        this.event_e_staff_1_phone = event_e_staff_1_phone;
    }

    public String getEvent_e_staff_2() {
        return event_e_staff_2;
    }

    public void setEvent_e_staff_2(String event_e_staff_2) {
        this.event_e_staff_2 = event_e_staff_2;
    }

    public String getEvent_e_staff_2_email() {
        return event_e_staff_2_email;
    }

    public void setEvent_e_staff_2_email(String event_e_staff_2_email) {
        this.event_e_staff_2_email = event_e_staff_2_email;
    }

    public String getEvent_e_staff_2_phone() {
        return event_e_staff_2_phone;
    }

    public void setEvent_e_staff_2_phone(String event_e_staff_2_phone) {
        this.event_e_staff_2_phone = event_e_staff_2_phone;
    }

    public String getEvent_e_student_1() {
        return event_e_student_1;
    }

    public void setEvent_e_student_1(String event_e_student_1) {
        this.event_e_student_1 = event_e_student_1;
    }

    public String getEvent_e_student_1_email() {
        return event_e_student_1_email;
    }

    public void setEvent_e_student_1_email(String event_e_student_1_email) {
        this.event_e_student_1_email = event_e_student_1_email;
    }

    public String getEvent_e_student_1_phone() {
        return event_e_student_1_phone;
    }

    public void setEvent_e_student_1_phone(String event_e_student_1_phone) {
        this.event_e_student_1_phone = event_e_student_1_phone;
    }

    public String getEvent_e_student_2() {
        return event_e_student_2;
    }

    public void setEvent_e_student_2(String event_e_student_2) {
        this.event_e_student_2 = event_e_student_2;
    }

    public String getEvent_e_student_2_email() {
        return event_e_student_2_email;
    }

    public void setEvent_e_student_2_email(String event_e_student_2_email) {
        this.event_e_student_2_email = event_e_student_2_email;
    }

    public String getEvent_e_student_2_phone() {
        return event_e_student_2_phone;
    }

    public void setEvent_e_student_2_phone(String event_e_student_2_phone) {
        this.event_e_student_2_phone = event_e_student_2_phone;
    }

    public String getEvent_e_whatsappLink() {
        return event_e_whatsappLink;
    }

    public void setEvent_e_whatsappLink(String event_e_whatsappLink) {
        this.event_e_whatsappLink = event_e_whatsappLink;
    }

    public String getEvent_e_likes() {
        return event_e_likes;
    }

    public void setEvent_e_likes(String event_e_likes) {
        this.event_e_likes = event_e_likes;
    }

    public String getE_1_prize() {
        return e_1_prize;
    }

    public void setE_1_prize(String e_1_prize) {
        this.e_1_prize = e_1_prize;
    }

    public String getE_2_prize() {
        return e_2_prize;
    }

    public void setE_2_prize(String e_2_prize) {
        this.e_2_prize = e_2_prize;
    }

    public String getE_3_prize() {
        return e_3_prize;
    }

    public void setE_3_prize(String e_3_prize) {
        this.e_3_prize = e_3_prize;
    }



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
