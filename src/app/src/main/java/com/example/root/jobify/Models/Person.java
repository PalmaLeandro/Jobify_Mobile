package com.example.root.jobify.Models;

import com.example.root.jobify.Views.GenericContentListPage.Content;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Created by root on 06/09/16.
 */
public class Person implements Content {


    private final String username;
    private final String city;
    private final String dob;
    private final String email;
    private final String gender;
    private final String name;

    private final String nationality;
    private final ArrayList<Experience> previous_exp;
    private final String profile;
    private final ArrayList<String> skills;
    private final ArrayList<String> contacts;
    private final ArrayList<String> recommended_by;
    private final ArrayList<String> chats;

    private String picture;

    public Person(String email, String name, String city, String dateOfBirth, String gender, String nationality, String profile, ArrayList<String> skills, ArrayList<Experience> experience, ArrayList<String> contacts, ArrayList<String> recommended_by, ArrayList<String> chats, String base64Image) {
        this.username = email;
        this.city = city;
        this.dob = dateOfBirth;
        this.email = email;
        this.gender = gender;
        this.name = name;
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
        if (previous_exp!=null)
            return previous_exp;
        else
            return new ArrayList<Experience>();
    }

    public ArrayList<String> getMyPeople() {
        if (contacts!=null)
            return contacts;
        else
            return new ArrayList<String>();
    }

    public ArrayList<String> getFellowsWhoRecommendMe() {
        if (recommended_by!=null)
            return recommended_by;
        else
            return new ArrayList<>();
    }

    public ArrayList<String> getChats() {
        if (chats!=null)
            return chats;
        else
            return new ArrayList<>();
    }

    public String getProfile() {
        if (profile!=null)
            return profile;
        else
            return "";
    }

    public ArrayList<String> getSkills() {

        if (skills!=null)
            return skills;
        else
            return new ArrayList<String>();
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
            return "";
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
        return picture;
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
        for(String personEmail: getMyPeople()){
            if(personEmail.equals(fellowToRemove.getEmail())){
                getMyPeople().remove(getMyPeople().indexOf(personEmail));
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
            }
        }
    }
}
