
package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;

public class MutationResolver implements GraphQLRootResolver {

    private final UniteEnseignementBusiness ueHelper;
    private final ModuleBusiness moduleHelper;

    public MutationResolver() {
        ueHelper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    // 1. Ajouter une UE
    public boolean addUniteEnseignement(
            int code,
            String domaine,
            String responsable,
            int credits,
            int semestre) {

        UniteEnseignement ue = new UniteEnseignement(
                code, domaine, responsable, credits, semestre
        );

        return ueHelper.addUniteEnseignement(ue);
    }

    // 2. Modifier une UE
    public boolean updateUniteEnseignement(
            int code,
            String domaine,
            String responsable,
            int credits,
            int semestre) {

        UniteEnseignement ue = new UniteEnseignement(
                code, domaine, responsable, credits, semestre
        );

        return ueHelper.updateUniteEnseignement(code, ue);
    }

    // 3. Supprimer une UE
    public boolean deleteUniteEnseignement(int code) {
        return ueHelper.deleteUniteEnseignement(code);
    }

    // 4. Ajouter un module
    public boolean addModule(
            String matricule,
            String nom,
            int coefficient,
            int volumeHoraire,
            int codeUE,
            Module.TypeModule type) {

        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);

        if (ue == null) {
            return false;
        }

        Module module = new Module(
                matricule, nom, coefficient,
                volumeHoraire, type, ue
        );

        return moduleHelper.addModule(module);
    }

    // 5. Modifier un module
    public Module updateModule(
            String matricule,
            String nom,
            int coefficient,
            int volumeHoraire,
            int codeUE,
            Module.TypeModule type) {

        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);

        if (ue == null) {
            return null;
        }

        Module module = new Module(
                matricule, nom, coefficient,
                volumeHoraire, type, ue
        );

        boolean updated = moduleHelper.updateModule(
                matricule, module
        );

        return updated ? module : null;
    }

    // 6. Supprimer un module
    public String deleteModule(String matricule) {

        if (moduleHelper.deleteModule(matricule)) {
            return "Module deleted";
        }

        return "Module not found";
    }
}
