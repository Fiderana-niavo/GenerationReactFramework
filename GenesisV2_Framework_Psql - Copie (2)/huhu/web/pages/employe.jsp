<%@page import="huhu.entities.*" %>
<%
    Employe[] liste=(Employe[])request.getAttribute("o");
    Direction[] direction=(Direction[])request.getAttribute("directions");Employe[] employe=(Employe[])request.getAttribute("employes");Category[] category=(Category[])request.getAttribute("categorys");
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6 offset-3">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de employe</h6>
                <table class="table">
                    <thead>
                        <tr>                            
                            <th scope="col">Id</th><th scope="col">Nom</th><th scope="col">Direction</th><th scope="col">Employe</th><th scope="col">Category</th>
                        </tr>
                    <tbody>
                        <% for(Employe o:liste){ %>
                            <tr>
                                <td><%= o.getId() %></td><td><%= o.getNom() %></td><td><%= o.getDirection().getLabel() %></td><td><%= o.getCategory().getLabel() %></td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal<%= o.getId() %>">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal<%= o.getId() %>" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                        <form action="/Huhu/updateemploye.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un employe</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="id" value="<%= o.getId() %>">
                                            <div class="mb-3"> 
    <label for="nominput" class="form-label">Nom
    </label> 
    <input type="text" class="form-control" id="nominput" aria-describedby="nomhelp" name="nom" value="<%= o.getNom() %>"> 
</div>
<div class="form-floating mb-3"> 
    <select class="form-select" id="directionSelect" name="direction">
        <% for(Direction f:direction){ %>
        <option value="<%= f.getId() %>" <% if(f.getId().equals(o.getDirection().getId())){ out.print("selected"); } %>>
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="directionSelect">
        Direction
    </label> 
</div>
<div class="form-floating mb-3"> 
    <select class="form-select" id="categorySelect" name="category">
        <% for(Category f:category){ %>
        <option value="<%= f.getId() %>" <% if(f.getId().equals(o.getCategory().getId())){ out.print("selected"); } %>>
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="categorySelect">
        Category
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
                                <td><form action="/Huhu/deleteemploye.do" method="delete">
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
            <form action="/Huhu/insertemploye.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un employe</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3"> 
    <label for="nominput" class="form-label">Nom
    </label> 
    <input type="text" class="form-control" id="nominput" aria-describedby="nomhelp" name="nom"> 
</div>
<div class="form-floating mb-3"> 
    <select class="form-select" id="directionSelect" name="direction">
        <% for(Direction f:direction){ %>
        <option value="<%= f.getId() %>">
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="directionSelect">
        Direction
    </label> 
</div>
<div class="form-floating mb-3"> 
    <select class="form-select" id="employeSelect" name="employe">
        <% for(Employe f:employe){ %>
        <option value="<%= f.getId() %>">
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="employeSelect">
        Employe
    </label> 
</div>
<div class="form-floating mb-3"> 
    <select class="form-select" id="categorySelect" name="category">
        <% for(Category f:category){ %>
        <option value="<%= f.getId() %>">
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="categorySelect">
        Category
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
