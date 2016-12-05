package br.com.loewe.registrodeamecasambientais.model;

public class Threat implements FirebaseModel {

    private Long id;
    private String description;
    private String address;
    private String district;
    private Integer potential;
    private String image;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPotential() {
        return potential;
    }

    public void setPotential(Integer potential) {
        this.potential = potential;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
