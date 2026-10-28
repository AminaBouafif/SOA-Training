
package business;

import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UniteEnseignementBusiness {

    private static List<UniteEnseignement> unitesEnseignement;

    // Initialiser les donnees une seule fois
    public UniteEnseignementBusiness() {

        if (unitesEnseignement == null) {

            unitesEnseignement = new ArrayList<>();

            unitesEnseignement.add(
                    new UniteEnseignement(
                            1, "Informatique",
                            "Mme Maroua Douiri", 6, 1
                    )
            );

            unitesEnseignement.add(
                    new UniteEnseignement(
                            2, "Mathématiques",
                            "Mme Ines ElMejid", 5, 1
                    )
            );

            unitesEnseignement.add(
                    new UniteEnseignement(
                            3, "Physique",
                            "Mme Sarra Abidi", 4, 2
                    )
            );

            unitesEnseignement.add(
                    new UniteEnseignement(
                            4, "Infographie",
                            "Mme Oumeima Ibnelfkih", 3, 1
                    )
            );

            unitesEnseignement.add(
                    new UniteEnseignement(
                            5, "Chimie",
                            "M. Mohamed Amine Chebbi", 4, 2
                    )
            );
        }
    }

    // 1. Rechercher une UE par son code
    public UniteEnseignement getUEByCode(int code) {

        for (UniteEnseignement ue : unitesEnseignement) {

            if (ue.getCode() == code) {
                return ue;
            }
        }

        return null;
    }

    // 2. Ajouter une UE
    public boolean addUniteEnseignement(UniteEnseignement ue) {

        if (ue == null) {
            return false;
        }

        // Refuser les codes deja existants
        if (getUEByCode(ue.getCode()) != null) {
            return false;
        }

        return unitesEnseignement.add(ue);
    }

    // 3. Recuperer les UE par domaine
    public List<UniteEnseignement> getUEByDomaine(String domaine) {

        List<UniteEnseignement> liste = new ArrayList<>();

        if (domaine == null) {
            return liste;
        }

        for (UniteEnseignement ue : unitesEnseignement) {

            if (ue.getDomaine() != null &&
                    ue.getDomaine().equalsIgnoreCase(domaine)) {

                liste.add(ue);
            }
        }

        return liste;
    }

    // 4. Recuperer les UE par semestre
    public List<UniteEnseignement> getUEBySemestre(int semestre) {

        List<UniteEnseignement> liste = new ArrayList<>();

        for (UniteEnseignement ue : unitesEnseignement) {

            if (ue.getSemestre() == semestre) {
                liste.add(ue);
            }
        }

        return liste;
    }

    // 5. Supprimer une UE
    public boolean deleteUniteEnseignement(int code) {

        Iterator<UniteEnseignement> iterator =
                unitesEnseignement.iterator();

        while (iterator.hasNext()) {

            UniteEnseignement ue = iterator.next();

            if (ue.getCode() == code) {

                iterator.remove();
                return true;
            }
        }

        return false;
    }

    // 6. Modifier une UE
    public boolean updateUniteEnseignement(
            int code,
            UniteEnseignement updatedUE) {

        if (updatedUE == null) {
            return false;
        }

        for (int i = 0; i < unitesEnseignement.size(); i++) {

            if (unitesEnseignement.get(i).getCode() == code) {

                // Conserver le code de l'UE recherchee
                updatedUE.setCode(code);

                unitesEnseignement.set(i, updatedUE);

                return true;
            }
        }

        return false;
    }

    // 7. Recuperer toutes les UE
    public List<UniteEnseignement> getListeUE() {

        return unitesEnseignement;
    }

    // 8. Remplacer la liste des UE
    public void setUnitesEnseignement(
            List<UniteEnseignement> unitesEnseignement) {

        UniteEnseignementBusiness.unitesEnseignement =
                unitesEnseignement;
    }
}
