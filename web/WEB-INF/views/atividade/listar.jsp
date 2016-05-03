<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atividades</title>
        <style>
            tr:nth-child(odd){
                background-color: #e0e0e0;
            }
            tr:nth-child(even){
                background-color: #c0c0c0;
            }
        </style>
    </head>
    <body>
        <h1>Cadastro de Atividades</h1>
        <form method="POST">
        <table>
            <thead>
                <tr>
                    <th>Funcionário</th>
                    <th>Tipo</th>
                    <th>Horas</th>
                    <th>Descrição</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input type="text" name="funcionario" value="${atividadeEditar.funcionario}"/></td>                    
                    <td><input type="text" name="tipo" value="${atividadeEditar.tipo}"/></td>                    
                    <td><input type="number" name="horas" value="${atividadeEditar.horas}"/></td>                    
                    <td><input type="text" name="descricao" value="${atividadeEditar.descricao}"/></td>
                    <td><input type="Submit" value="Salvar"/></td>
                </tr>
            </tbody>
        </table>
        </form>
        <h1>Lista de Atividades</h1>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Funcionário</th>
                    <th>Tipo</th>
                    <th>Horas</th>
                    <th>Descrição</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${atividades}" var="atividade">

                    <tr>                    
                        <td>${atividade.id}</td>
                        <td>${atividade.funcionario}</td>                    
                        <td>${atividade.tipo}</td>                    
                        <td>${atividade.horas}</td>                    
                        <td>${atividade.descricao}</td>
                        <td><a href="../AtividadeController?url=editar&id=${atividade.id}">Editar</a></td> 
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
