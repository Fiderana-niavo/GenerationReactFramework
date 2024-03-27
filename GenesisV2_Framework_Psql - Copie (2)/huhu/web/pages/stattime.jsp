<%@page import="huhu.entities.*" %>
<%
    Stattime[] liste=(Stattime[])request.getAttribute("o");
    
%>
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-6 offset-3">
            <div class="bg-secondary rounded h-100 p-4">
                <h6 class="mb-4">Liste de stattime</h6>
                <table class="table">
                    <thead>
                        <tr>                            
                            <th scope="col">Id</th><th scope="col">Matchid</th><th scope="col">Player</th><th scope="col">Shottime</th>
                        </tr>
                    <tbody>
                        <% for(Stattime o:liste){ %>
                            <tr>
                                <td><%= o.getId() %></td><td><%= o.getMatchid() %></td><td><%= o.getPlayer() %></td><td><%= o.getShottime() %></td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifiermodal<%= o.getId() %>">
                                        <p><i class="bi bi-pencil"></i>Modifier</p>
                                    </button>
                                    <div class="modal fade" id="modifiermodal<%= o.getId() %>" tabindex="-1" aria-labelledby="modifiermodalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                        <form action="/Huhu/updatestattime.do" method="put">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="modifiermodalLabel">Modifier un stattime</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="id" value="<%= o.getId() %>">
                                            <div class="mb-3"> 
    <label for="matchidinput" class="form-label">Matchid
    </label> 
    <input type="number" step="1.0" class="form-control" id="matchidinput" name="matchid" value="<%= o.getMatchid() %>"> 
</div>
<div class="mb-3"> 
    <label for="playerinput" class="form-label">Player
    </label> 
    <input type="number" step="1.0" class="form-control" id="playerinput" name="player" value="<%= o.getPlayer() %>"> 
</div>
<div class="mb-3"> 
    <label for="shottimeinput" class="form-label">Shottime
    </label> 
    <input type="text" class="form-control" id="shottimeinput" aria-describedby="shottimehelp" name="shottime" value="<%= o.getShottime() %>"> 
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
                                <td><form action="/Huhu/deletestattime.do" method="delete">
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
            <form action="/Huhu/insertstattime.do" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="ajoutmodalLabel">Ajouter un stattime</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3"> 
    <label for="matchidinput" class="form-label">Matchid
    </label> 
    <input type="number" step="1.0" class="form-control" id="matchidinput" name="matchid"> 
</div>
<div class="mb-3"> 
    <label for="playerinput" class="form-label">Player
    </label> 
    <input type="number" step="1.0" class="form-control" id="playerinput" name="player"> 
</div>
<div class="mb-3"> 
    <label for="shottimeinput" class="form-label">Shottime
    </label> 
    <input type="text" class="form-control" id="shottimeinput" aria-describedby="shottimehelp" name="shottime"> 
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
