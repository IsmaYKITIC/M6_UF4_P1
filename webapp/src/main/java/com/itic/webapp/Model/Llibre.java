package com.itic.webapp.Model;

public class Llibre {
    private int idLlibre;
    private String titol;
    private String autor;
    private String editorial;
    private String datapublicacio;
    private String tematica;

    public Llibre() {
    }

    public Llibre(int idLlibre, String titol, String autor, String editorial, String datapublicacio, String tematica) {
        this.idLlibre = idLlibre;
        this.titol = titol;
        this.autor = autor;
        this.editorial = editorial;
        this.datapublicacio = datapublicacio;
        this.tematica = tematica;
    }

    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getDatapublicacio() {
        return datapublicacio;
    }

    public void setDatapublicacio(String datapublicacio) {
        this.datapublicacio = datapublicacio;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }
}
