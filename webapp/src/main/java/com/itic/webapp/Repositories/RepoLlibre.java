package com.itic.webapp.Repositories;

@Repository
public class RepoLlibre {

    ArrayList<Llibre> llibres = new ArrayList<Llibre>();

    public RepoLlibre() {
        llibres.add(new Llibre(1, "HARRY POTTER I EL PRESONER D'AZKABAN", "JK Rowling", "Salamandra", "26/9/2006",
                "fantastica"));
        llibres.add(new Llibre(2, "CODI DA VINCI", "Dan Brown", "Ariel", "26/9/2006", "ficcio"));
    }

    public ArrayList<Llibre> getAllLlibres() {
        return llibres;
    }

    public void InsertaLlibre(Llibre llibre) {
        llibres.add(llibre);
    }

    public Llibre getLlibreID(int id) {
        Llibre llibre = null;

        // Opció clàssica, imperativa
        for (Llibre l1 : llibres) {
            if (l1.getIdLlibre() == id) {
                llibre = l1;
            }
        }

        return llibre;
    }

}
