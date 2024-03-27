<%@page import="huhu.entities.*" %>
<%
    Etudiant[] liste=(Etudiant[])request.getAttribute("o");
    
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6 offset-3">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de etudiant</h6>
                <table class="table">
                    <thead>
                        <tr>                            
                            <th scope="col">Id</th><th scope="col">Numero</th><th scope="col">Nom</th><th scope="col">Admission status</th>
                        </tr>
                    <tbody>
                        <% for(Etudiant o:liste){ %>
                            <tr>
                                <td><%= o.getId() %></td><td><%= o.getNumero() %></td><td><%= o.getNom() %></td><td><%= o.getAdmissionStatus() %></td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal<%= o.getId() %>">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal<%= o.getId() %>" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                        <form action="/Huhu/updateetudiant.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un etudiant</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="id" value="<%= o.getId() %>">
                                            <div class="mb-3"> 
    <label for="numeroinput" class="form-label">Numero
    </label> 
    <input type="text" class="form-control" id="numeroinput" aria-describedby="numerohelp" name="numero" value="<%= o.getNumero() %>"> 
</div>
<div class="mb-3"> 
    <label for="nominput" class="form-label">Nom
    </label> 
    <input type="text" class="form-control" id="nominput" aria-describedby="nomhelp" name="nom" value="<%= o.getNom() %>"> 
</div>
<div class="mb-3"> 
    <label for="admissionStatusinput" class="form-label">Admission status
    </label> 
    <input type="number" step="1.0" class="form-control" id="admissionStatusinput" name="admissionStatus" value="<%= o.getAdmissionStatus() %>"> 
</div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary">Valider</button>
                                        </div>
                                        </form>
                                        </div>
                                    </div>
                                    </div>
                                </td>
                                <td><form action="/Huhu/deleteetudiant.do" method="delete">
                                    <input type="hidden" name="id" value="<%= o.getId() %>">
                                    <button type="submit" class="btn btn-danger">
                                        <p><i class="bi bi-trash"></i>Supprimer</p>
                                    </button>
                                </form></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ajoutmodal">
                    <p><i class="bi bi-plus"></i>Ajouter</p>
                </button>
            </div>
        </div>
        <!-- Button modal ajout -->

        <!-- Modal Ajout -->
        <div class="modal fade" id="ajoutmodal" tabindex="-1" aria-labelledby="ajoutmodalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
            <form action="/Huhu/insertetudiant.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un etudiant</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3"> 
    <label for="numeroinput" class="form-label">Numero
    </label> 
    <input type="text" class="form-control" id="numeroinput" aria-describedby="numerohelp" name="numero"> 
</div>
<div class="mb-3"> 
    <label for="nominput" class="form-label">Nom
    </label> 
    <input type="text" class="form-control" id="nominput" aria-describedby="nomhelp" name="nom"> 
</div>
<div class="mb-3"> 
    <label for="admissionStatusinput" class="form-label">Admission status
    </label> 
    <input type="number" step="1.0" class="form-control" id="admissionStatusinput" name="admissionStatus"> 
</div>

            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary">Valider</button>
            </div>
            </form>
            </div>
        </div>
        </div>
    </div>
</div>
