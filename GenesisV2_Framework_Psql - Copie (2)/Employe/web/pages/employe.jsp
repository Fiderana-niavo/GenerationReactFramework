<%@page import="employe.entities.*" %>
<%
    Employe[] liste=(Employe[])request.getAttribute("o");
    Departement[] departement=(Departement[])request.getAttribute("departements");
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6 offset-3">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de employe</h6>
                <table class="table">
                    <thead>
                        <tr>                            
                            <th scope="col">Idemploye</th><th scope="col">Nomemploye</th><th scope="col">Datenaissance</th><th scope="col">Departement</th>
                        </tr>
                    <tbody>
                        <% for(Employe o:liste){ %>
                            <tr>
                                <td><%= o.getIdemploye() %></td><td><%= o.getNomemploye() %></td><td><%= o.getDatenaissance() %></td><td><%= o.getDepartement().getLabel() %></td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal<%= o.getNomemploye() %>">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal<%= o.getNomemploye() %>" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                        <form action="/Employe/updateemploye.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un employe</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="nomemploye" value="<%= o.getNomemploye() %>">
                                            <div class="mb-3"> 
    <label for="idemployeinput" class="form-label">Idemploye
    </label> 
    <input type="number" step="1.0" class="form-control" id="idemployeinput" name="idemploye" value="<%= o.getIdemploye() %>"> 
</div>
<div class="mb-3"> 
    <label for="datenaissanceinput" class="form-label">Datenaissance
    </label> 
    <input type="date" class="form-control" id="datenaissanceinput" aria-describedby="datenaissancehelp" name="datenaissance" value="<%= o.getDatenaissance() %>"> 
</div>
<div class="form-floating mb-3"> 
    <select class="form-select" id="departementSelect" name="departement">
        <% for(Departement f:departement){ %>
        <option value="<%= f.getIddepartement() %>" <% if(f.getIddepartement().equals(o.getDepartement().getIddepartement())){ out.print("selected"); } %>>
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="departementSelect">
        Departement
    </label> 
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
                                <td><form action="/Employe/deleteemploye.do" method="delete">
                                    <input type="hidden" name="nomemploye" value="<%= o.getNomemploye() %>">
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
            <form action="/Employe/insertemploye.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un employe</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3"> 
    <label for="idemployeinput" class="form-label">Idemploye
    </label> 
    <input type="number" step="1.0" class="form-control" id="idemployeinput" name="idemploye"> 
</div>
<div class="mb-3"> 
    <label for="datenaissanceinput" class="form-label">Datenaissance
    </label> 
    <input type="date" class="form-control" id="datenaissanceinput" aria-describedby="datenaissancehelp" name="datenaissance"> 
</div>
<div class="form-floating mb-3"> 
    <select class="form-select" id="departementSelect" name="departement">
        <% for(Departement f:departement){ %>
        <option value="<%= f.getIddepartement() %>">
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="departementSelect">
        Departement
    </label> 
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
