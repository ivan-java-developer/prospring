package com.prospring.ch10.objects;

import com.prospring.ch10.services.CheckCountrySinger;
import org.joda.time.DateTime;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URL;
import java.text.SimpleDateFormat;

@CheckCountrySinger
public class Singer {

    @NotNull
    @Size(min=2, max=60)
    private String firstName;

    private String lastName;

    private DateTime birthDate;

    private Genre genre;

    private URL site;

    @AssertTrue(message = "Country singer should have personal site")
    public boolean isCountrySinger() {
        boolean result = true;
        if (genre != null && (genre == Genre.COUNTRY && site == null)) {
            result = false;
        }
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public URL getSite() {
        return site;
    }

    public void setSite(URL site) {
        this.site = site;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Singer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + (birthDate != null ? dateFormat.format(birthDate.toDate()) : null) +
                ", site=" + site +
                '}';
    }
}
