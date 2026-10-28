
package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

public class QueryResolver implements GraphQLRootResolver {

    private final ModuleBusiness moduleHelper;
    private final UniteEnseignementBusiness ueHelper;

    public QueryResolver() {
        ueHelper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    public List<UniteEnseignement> allUEs() {
        return ueHelper.getListeUE();
    }

    public List<Module> allModules() {
        return moduleHelper.getAllModules();
    }

    public List<Module> modulesByType(Module.TypeModule type) {
        return moduleHelper.getModulesByType(type);
    }

    public Module moduleByMatricule(String matricule) {
        return moduleHelper.getModuleByMatricule(matricule);
    }

    public UniteEnseignement ueByCode(int code) {
        return ueHelper.getUEByCode(code);
    }

    public List<UniteEnseignement> uesByDomaine(String domaine) {
        return ueHelper.getUEByDomaine(domaine);
    }

    public List<UniteEnseignement> uesBySemestre(int semestre) {
        return ueHelper.getUEBySemestre(semestre);
    }
}
