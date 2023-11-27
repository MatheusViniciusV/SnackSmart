<%-- 
    Document   : GestaoAlugueis
    Created on : 20 de nov. de 2023, 10:21:01
    Author     : VictorN77
--%>
<%@ page import= "br.cefetmg.snacksmart.dao.ContratosDAO" %>
<%@ page import= "br.cefetmg.snacksmart.dto.ContratoDTO" %>
<%@ page import= "br.cefetmg.snacksmart.dto.MaquinaDTO" %>

<%@ page import= "java.util.ArrayList" %>
<%@ page import= "java.io.PrintWriter" %>
<%@ page import= "com.google.gson.Gson" %>
<%@ page import= "br.cefetmg.snacksmart.utils.DataManager" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel ="stylesheet" href="css/alugueis.css">
    </head>
    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <h1>Aluguéis</h1>
        <div class="conteudo">
            <p>Dias de pagamento das máquinas</p>
            <p>Formulário</p>
        </div>

        <div class="conteudo">
            <div class="box" id="listaMaquinas">
                <%
                    response.setContentType("text/html");
                    PrintWriter writer = response.getWriter();
                
                ContratosDAO Contratos = new ContratosDAO();

                try{
                    ArrayList<String> nomeMaquinas = new ArrayList();
                    ArrayList<DataManager> dataContratos = new ArrayList();
                    ArrayList<ContratoDTO> ListaContratos = Contratos.listaTodos();
                    ArrayList<String> jsonContratos = new ArrayList();

                    for(ContratoDTO c : ListaContratos){
                        //JsonMaquinas.add(m.toJson());
                        nomeMaquinas.add(c.getMaquina().getNome());
                        dataContratos.add(c.getDataPagamento());
                        jsonContratos.add(c.toJson());
                        request.setAttribute("jsonContratos", jsonContratos);
                        
                    
                        //writer.println("<p>" + m.getNome()+"</p>");
                    }
                    for(ContratoDTO c : ListaContratos){
                        //System.out.println("<p class='listaOculta' style=''> + '<%=c.getMaquina()%' + '>' + '</p>'");
                        //System.out.println("<br>");
                        //System.out.println("<p class='listaOculta' style=''>" + '<%= c.getDataPagamento()%' + '>' + "</p>");
                    }
                    //System.out.println("</p>");
                    //Gson gson = new Gson();
                    //String json = gson.toJson(JsonMaquinas);
                    
                    //request.setAttribute("JsonMaquinas", json);
                    }
                  catch(Exception e){}  
                    
                %>
                <p id='listaOculta'>
                    <%= jsonContratos%>
                </p>

            </div>
            <div class="box">

                <form id="pedido">
                    <input type="text" id="tipo" value='tipo'>
                    <input type="text" id="local" value='local'>
                    <input type="text" id="loc" value='loc'>


                    <input type="submit">
                </form>

            </div>
        </div>
        <script src="js/Alugueis.js">
            </body>
            </html> 