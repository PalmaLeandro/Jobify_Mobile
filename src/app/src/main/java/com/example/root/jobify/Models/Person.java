package com.example.root.jobify.Models;

import com.example.root.jobify.Globals;
import com.example.root.jobify.Views.GenericContentListPage.Content;

import java.util.ArrayList;

/**
 * Created by root on 06/09/16.
 */
public class Person implements Content {


    private String username;
    private String city;
    private String dob;
    private String email;
    private String gender;
    private String name;
    private String coordenates;

    private String firebase_token;

    private String nationality;
    private ArrayList<Experience> previous_exp;
    private String profile;
    private ArrayList<String> skills;
    private ArrayList<String> contacts;
    private ArrayList<String> recommended_by;
    private ArrayList<String> chats;

    private String picture;

    public Person(String email, String name, String city, String dateOfBirth, String gender, String coordenates, String nationality, String profile, ArrayList<String> skills, ArrayList<Experience> experience, ArrayList<String> contacts, ArrayList<String> recommended_by, ArrayList<String> chats, String base64Image) {
        this.username = email;
        this.city = city;
        this.dob = dateOfBirth;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.coordenates = coordenates;
        this.nationality = nationality;
        this.profile = profile;
        this.skills = skills;
        this.previous_exp = experience;
        this.contacts = contacts;
        this.recommended_by = recommended_by;
        this.chats = chats;
        this.picture = base64Image;
    }

    public String getName() {
        if(name!=null){
            return name;
        }else{
            return "";
        }
    }

    public String getCity() {
        if(city!=null){
            return city;
        } else {
            return "";
        }
    }

    public String getDateOfBirth() {
        if(dob!=null)
            return dob;
        else
            return "";
    }

    public String getEmail() {
        if(email!=null)
            return email;
        else
            return "";
    }

    public String getGender(){
    if(gender!=null)
        return gender;
    else
        return "";
    }

    public String getNationality() {
        if(nationality!=null)
            return nationality;
        else
            return "";
    }

    public ArrayList<Experience> getPreviousExperience() {
        if (previous_exp!=null) {
            return previous_exp;
        } else {
            this.previous_exp = new ArrayList<Experience>();
            return this.previous_exp;
        }
    }

    public ArrayList<String> getMyPeople() {
        if (contacts!=null) {
            return contacts;
        } else {
            this.contacts = new ArrayList<String>();
            return this.contacts;
        }
    }

    public ArrayList<String> getFellowsWhoRecommendMe() {
        if (this.recommended_by!=null) {
            return this.recommended_by;
        } else {
            this.recommended_by = new ArrayList<String>();
            return this.recommended_by;
        }
    }

    public ArrayList<String> getChats() {
        if (this.chats!=null) {
            return this.chats;
        } else {
            this.chats = new ArrayList<String>();
            return this.chats;
        }
    }

    public String getProfile() {
        if (profile!=null)
            return profile;
        else
            return "";
    }

    public ArrayList<String> getSkills() {
        if (this.skills!=null) {
            return this.skills;
        } else {
            this.skills = new ArrayList<String>();
            return this.skills;
        }
    }

    public String getPosition() {
        if (previous_exp!=null && previous_exp.size()>0)
            return previous_exp.get(0).getPosition();
        else
            return getProfile();
    }

    public String getUsername() {
        if (username!=null)
            return username;
        else
            return getEmail();
    }

    @Override
    public String getId() {
        return getUsername();
    }

    @Override
    public String getTitle() {
        return getName();
    }

    @Override
    public String getSubTitle() {
        return getPosition();
    }

    public String getPicture() {
        if(picture!=null)
            return picture;
        else
            return Globals.defaultBase64Image;
    }

    public void removeFellowFromConacts(final Person fellowToRemove){
        for(String personEmail: getMyPeople()){
            if(personEmail.equals(fellowToRemove.getEmail())){
                getMyPeople().remove(getMyPeople().indexOf(personEmail));
                break;
            }
        }
    }

    public void removeFellowFromGuysWhomRecommendedMe(final Person fellowToRemove){
        for(String personEmail: getFellowsWhoRecommendMe()){
            if(personEmail.equals(fellowToRemove.getEmail())){
                getFellowsWhoRecommendMe().remove(getFellowsWhoRecommendMe().indexOf(personEmail));
                break;
            }
        }
    }

    public void setProfileImage(String profileImage) {
        this.picture = profileImage;
    }

    public void removeSkills(final String skillId){
        getSkills().remove(skillId);
    }

    public void removeExperience(final String experienceId){
        for(Experience experience: getPreviousExperience()){
            if(experience.getId().equals(experienceId)){
                previous_exp.remove(experience);
                break;
            }
        }
    }

    public String getCoordenates() {
        return coordenates;
    }

    public void setFirebaseToken(String firebase_token) {
        this.firebase_token = firebase_token;
    }

    public Integer getRecomendations() {
        return getFellowsWhoRecommendMe().size();
    }
}
