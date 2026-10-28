
package business;

import entities.Module;
import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModuleBusiness {

    private static List<Module> modules;

    private UniteEnseignementBusiness uniteEnseignementBusiness =
            new UniteEnseignementBusiness();

    public ModuleBusiness() {

        // Initialiser la liste une seule fois
        if (modules == null) {

            modules = new ArrayList<Module>();

            // Donnees de test fournies dans le projet
            modules.add(new Module(
                    "M101",
                    "Algorithmique",
                    3,
                    30,
                    Module.TypeModule.PROFESSIONNEL,
                    uniteEnseignementBusiness.getUEByCode(1)
            ));

            modules.add(new Module(
                    "M102",
                    "Base de données",
                    2,
                    20,
                    Module.TypeModule.PROFESSIONNEL,
                    uniteEnseignementBusiness.getUEByCode(1)
            ));

            modules.add(new Module(
                    "M201",
                    "Communication",
                    1,
                    15,
                    Module.TypeModule.TRANSVERSAL,
                    uniteEnseignementBusiness.getUEByCode(2)
            ));
        }
    }

    // 1. Ajouter un module
    public boolean addModule(Module module) {

        if (module == null ||
                module.getMatricule() == null ||
                module.getUniteEnseignement() == null) {
            return false;
        }

        // Eviter les matricules en double
        if (getModuleByMatricule(module.getMatricule()) != null) {
            return false;
        }

        int code = module.getUniteEnseignement().getCode();

        UniteEnseignement ue =
                uniteEnseignementBusiness.getUEByCode(code);

        if (ue != null) {
            module.setUniteEnseignement(ue);
            return modules.add(module);
        }

        return false;
    }

    // 2. Recuperer un module par son matricule
    public Module getModuleByMatricule(String matricule) {

        for (Module m : modules) {
            if (m.getMatricule().equals(matricule)) {
                return m;
            }
        }

        return null;
    }

    // 3. Recuperer les modules par type
    public List<Module> getModulesByType(Module.TypeModule type) {

        List<Module> result = new ArrayList<>();

        for (Module m : modules) {
            if (m.getType() == type) {
                result.add(m);
            }
        }

        return result;
    }

    // 4. Mettre a jour un module
    public boolean updateModule(String matricule, Module updatedModule) {

        if (updatedModule == null) {
            return false;
        }

        for (int i = 0; i < modules.size(); i++) {

            Module existingModule = modules.get(i);

            if (existingModule.getMatricule().equals(matricule)) {

                // Conserver le matricule de l'URL
                updatedModule.setMatricule(matricule);

                // Verifier l'UE associee
                if (updatedModule.getUniteEnseignement() == null) {

                    updatedModule.setUniteEnseignement(
                            existingModule.getUniteEnseignement()
                    );

                } else {

                    int code = updatedModule
                            .getUniteEnseignement()
                            .getCode();

                    UniteEnseignement ue =
                            uniteEnseignementBusiness.getUEByCode(code);

                    if (ue == null) {
                        return false;
                    }

                    updatedModule.setUniteEnseignement(ue);
                }

                modules.set(i, updatedModule);
                return true;
            }
        }

        return false;
    }

    // 5. Supprimer un module
    public boolean deleteModule(String matricule) {

        Iterator<Module> iterator = modules.iterator();

        while (iterator.hasNext()) {

            Module m = iterator.next();

            if (m.getMatricule().equals(matricule)) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    // 6. Recuperer tous les modules
    public List<Module> getAllModules() {
        return modules;
    }

    // 7. Recuperer les modules d'une UE specifique
    public List<Module> getModulesByUE(UniteEnseignement ue) {

        List<Module> result = new ArrayList<>();

        if (ue == null) {
            return result;
        }

        for (Module m : modules) {

            if (m.getUniteEnseignement() != null &&
                    m.getUniteEnseignement().getCode() == ue.getCode()) {

                result.add(m);
            }
        }

        return result;
    }
}
