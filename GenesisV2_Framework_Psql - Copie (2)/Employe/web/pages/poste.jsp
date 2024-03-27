<%@page import="employe.entities.*" %>
<%
    Poste[] liste=(Poste[])request.getAttribute("o");
    
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6 offset-3">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de poste</h6>
                <table class="table">
                    <thead>
                        <tr>                            
                            <th scope="col">Idposte</th><th scope="col">Nomposte</th>
                        </tr>
                    <tbody>
                        <% for(Poste o:liste){ %>
                            <tr>
                                <td><%= o.getIdposte() %></td><td><%= o.getNomposte() %></td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal<%= o.getIdposte() %>">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal<%= o.getIdposte() %>" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                        <form action="/Employe/updateposte.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un poste</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="idposte" value="<%= o.getIdposte() %>">
                                            <div class="mb-3"> 
    <label for="nomposteinput" class="form-label">Nomposte
    </label> 
    <input type="text" class="form-control" id="nomposteinput" aria-describedby="nompostehelp" name="nomposte" value="<%= o.getNomposte() %>"> 
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
                                <td><form action="/Employe/deleteposte.do" method="delete">
                                    <input type="hidden" name="idposte" value="<%= o.getIdposte() %>">
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
            <form action="/Employe/insertposte.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un poste</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3"> 
    <label for="nomposteinput" class="form-label">Nomposte
    </label> 
    <input type="text" class="form-control" id="nomposteinput" aria-describedby="nompostehelp" name="nomposte"> 
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
