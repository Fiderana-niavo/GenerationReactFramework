<%@page import="employe.entities.*" %>
<%
    Entreprise[] liste=(Entreprise[])request.getAttribute("o");
    
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6 offset-3">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de entreprise</h6>
                <table class="table">
                    <thead>
                        <tr>                            
                            <th scope="col">Identreprise</th><th scope="col">Nomentreprise</th>
                        </tr>
                    <tbody>
                        <% for(Entreprise o:liste){ %>
                            <tr>
                                <td><%= o.getIdentreprise() %></td><td><%= o.getNomentreprise() %></td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal<%= o.getIdentreprise() %>">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal<%= o.getIdentreprise() %>" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                        <form action="/Employe/updateentreprise.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un entreprise</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="identreprise" value="<%= o.getIdentreprise() %>">
                                            <div class="mb-3"> 
    <label for="nomentrepriseinput" class="form-label">Nomentreprise
    </label> 
    <input type="text" class="form-control" id="nomentrepriseinput" aria-describedby="nomentreprisehelp" name="nomentreprise" value="<%= o.getNomentreprise() %>"> 
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
                                <td><form action="/Employe/deleteentreprise.do" method="delete">
                                    <input type="hidden" name="identreprise" value="<%= o.getIdentreprise() %>">
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
            <form action="/Employe/insertentreprise.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un entreprise</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3"> 
    <label for="nomentrepriseinput" class="form-label">Nomentreprise
    </label> 
    <input type="text" class="form-control" id="nomentrepriseinput" aria-describedby="nomentreprisehelp" name="nomentreprise"> 
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
