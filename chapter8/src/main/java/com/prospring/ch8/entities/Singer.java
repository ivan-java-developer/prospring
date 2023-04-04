package com.prospring.ch8.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="singer")
@NamedQueries({
        @NamedQuery(name = "Singer.findAllWithDetails",
                query = "select distinct s from Singer s "
                        + "left join fetch s.albums a "
                        + "left join fetch s.instruments i"),
        @NamedQuery(name = "Singer.findById",
                query = "select distinct s from Singer s "
                        + "left join fetch s.albums a "
                        + "left join fetch s.instruments i "
                        + "where s.id = :id"),
        @NamedQuery(name = "Singer.findAll",
                query = "select s from Singer s")
})
@SqlResultSetMapping(name = "singerResult",
        entities = @EntityResult(entityClass = Singer.class))
public class Singer implements Serializable {
    public static final String FIND_ALL_WITH_DETAILS = "Singer.findAllWithDetails";
    public static final String FIND_BY_ID = "Singer.findById";
    public static final String FIND_ALL = "Singer.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(mappedBy = "singer", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "singer_id"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id"))
    private Set<Instrument> instruments = new HashSet<>();

    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public boolean addInstrument(Instrument instrument) {
        return instruments.add(instrument);
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

    public boolean addAlbum(Album album) {
        album.setSinger(this);
        return albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
