<%@page import="test3.entities.*" %>
<%
    Voiture[] liste=(Voiture[])request.getAttribute("o");
    Marque[] marque=(Marque[])request.getAttribute("marques");
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6 offset-3">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de voiture</h6>
                <table class="table">
                    <thead>
                        <tr>                            
                            <th scope="col">Idvoiture</th><th scope="col">Marque</th><th scope="col">Nomvoiture</th><th scope="col">Datasortie</th>
                        </tr>
                    <tbody>
                        <% for(Voiture o:liste){ %>
                            <tr>
                                <td><%= o.getIdvoiture() %></td><td><%= o.getMarque().getLabel() %></td><td><%= o.getNomvoiture() %></td><td><%= o.getDatasortie() %></td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal<%= o.getIdvoiture() %>">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal<%= o.getIdvoiture() %>" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                        <form action="/Test3/updatevoiture.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un voiture</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="idvoiture" value="<%= o.getIdvoiture() %>">
                                            <div class="form-floating mb-3"> 
    <select class="form-select" id="marqueSelect" name="marque">
        <% for(Marque f:marque){ %>
        <option value="<%= f.getIdmarque() %>" <% if(f.getIdmarque().equals(o.getMarque().getIdmarque())){ out.print("selected"); } %>>
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="marqueSelect">
        Marque
    </label> 
</div>
<div class="mb-3"> 
    <label for="nomvoitureinput" class="form-label">Nomvoiture
    </label> 
    <input type="text" class="form-control" id="nomvoitureinput" aria-describedby="nomvoiturehelp" name="nomvoiture" value="<%= o.getNomvoiture() %>"> 
</div>
<div class="mb-3"> 
    <label for="datasortieinput" class="form-label">Datasortie
    </label> 
    <input type="date" class="form-control" id="datasortieinput" aria-describedby="datasortiehelp" name="datasortie" value="<%= o.getDatasortie() %>"> 
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
                                <td><form action="/Test3/deletevoiture.do" method="delete">
                                    <input type="hidden" name="idvoiture" value="<%= o.getIdvoiture() %>">
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
            <form action="/Test3/insertvoiture.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un voiture</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-floating mb-3"> 
    <select class="form-select" id="marqueSelect" name="marque">
        <% for(Marque f:marque){ %>
        <option value="<%= f.getIdmarque() %>">
            <%= f.getLabel() %>
        </option>
        <% } %>
    </select> 
    <label for="marqueSelect">
        Marque
    </label> 
</div>
<div class="mb-3"> 
    <label for="nomvoitureinput" class="form-label">Nomvoiture
    </label> 
    <input type="text" class="form-control" id="nomvoitureinput" aria-describedby="nomvoiturehelp" name="nomvoiture"> 
</div>
<div class="mb-3"> 
    <label for="datasortieinput" class="form-label">Datasortie
    </label> 
    <input type="date" class="form-control" id="datasortieinput" aria-describedby="datasortiehelp" name="datasortie"> 
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
