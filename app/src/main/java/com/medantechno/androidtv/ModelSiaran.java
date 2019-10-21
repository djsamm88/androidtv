package com.medantechno.androidtv;

/**
 * Created by dinaskominfokab.pakpakbharat on 30/03/19.
 */

public class ModelSiaran {

    public ModelSiaran()
    {

    }

    public String link,nama,logo;
    public ModelSiaran(String link,String nama,String logo)
    {
        this.link=link;
        this.nama=nama;
        this.logo=logo;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getLogo() {
        return logo;
    }

    public String getNama() {
        return nama;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


}
