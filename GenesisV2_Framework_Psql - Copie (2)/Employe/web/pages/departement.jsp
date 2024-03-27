<%@page import="employe.entities.*" %>
<%
    Departement[] liste=(Departement[])request.getAttribute("o");
    
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6 offset-3">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de departement</h6>
                <table class="table">
                    <thead>
                        <tr>                            
                            <th scope="col">Iddepartement</th><th scope="col">Nomdepartement</th>
                        </tr>
                    <tbody>
                        <% for(Departement o:liste){ %>
                            <tr>
                                <td><%= o.getIddepartement() %></td><td><%= o.getNomdepartement() %></td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal<%= o.getIddepartement() %>">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal<%= o.getIddepartement() %>" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                        <form action="/Employe/updatedepartement.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un departement</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="iddepartement" value="<%= o.getIddepartement() %>">
                                            <div class="mb-3"> 
    <label for="nomdepartementinput" class="form-label">Nomdepartement
    </label> 
    <input type="text" class="form-control" id="nomdepartementinput" aria-describedby="nomdepartementhelp" name="nomdepartement" value="<%= o.getNomdepartement() %>"> 
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
                                <td><form action="/Employe/deletedepartement.do" method="delete">
                                    <input type="hidden" name="iddepartement" value="<%= o.getIddepartement() %>">
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
            <form action="/Employe/insertdepartement.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un departement</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3"> 
    <label for="nomdepartementinput" class="form-label">Nomdepartement
    </label> 
    <input type="text" class="form-control" id="nomdepartementinput" aria-describedby="nomdepartementhelp" name="nomdepartement"> 
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
