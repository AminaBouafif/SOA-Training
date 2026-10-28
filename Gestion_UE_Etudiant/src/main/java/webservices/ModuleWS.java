
package webservices;

import entities.Module;
import entities.UniteEnseignement;
import filtres.Secured;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleWS {

    ModuleBusiness helper = new ModuleBusiness();

    UniteEnseignementBusiness ueHelper =
            new UniteEnseignementBusiness();

    // 1. Afficher tous les modules
    @Path("/list")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }

    // 2. Ajouter un module
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {

        if (helper.addModule(module)) {
            return Response.status(201)
                    .entity("Module added")
                    .build();
        } else {
            return Response.status(400)
                    .entity("Module not added")
                    .build();
        }
    }

    // 3. Rechercher un module par matricule
    @Path("/{matricule}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(
            @PathParam("matricule") String matricule) {

        Module module = helper.getModuleByMatricule(matricule);

        if (module != null) {
            return Response.status(200)
                    .entity(module)
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module not found")
                    .build();
        }
    }

    // 4. Modifier un module
    @Path("/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(
            @PathParam("matricule") String matricule,
            Module module) {

        if (helper.updateModule(matricule, module)) {
            return Response.status(200)
                    .entity("Module updated")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module not found")
                    .build();
        }
    }

    // 5. Supprimer un module
    @Path("/{matricule}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(
            @PathParam("matricule") String matricule) {

        if (helper.deleteModule(matricule)) {
            return Response.status(200)
                    .entity("Module deleted")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module not found")
                    .build();
        }
    }

    // 6. Afficher les modules d'une UE spécifique
    @Path("/UE")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(
            @QueryParam("codeUE") int codeUE) {

        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);

        if (ue == null) {
            return Response.status(404)
                    .entity("UE not found")
                    .build();
        }

        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build();
    }
}
