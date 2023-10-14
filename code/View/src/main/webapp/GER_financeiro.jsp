<%-- 
    Document   : GER_financeiro
    Created on : 3 de out. de 2023, 11:00:04
    Author     : VictorN77
--%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/financeiro.css">

    <title>JSP Page</title>
</head>

<body>

    <h1>Finaceiro</h1>
    <div class="conteudo">
        <div class="box">
            <p>Recebimento previsto para o mês:</p>
            <p id="recebimento">R$: 1000,00</p>
            <! -- <p style="size:0.8em">Data de recebimento: XX/XX/XXXX</p>
        </div>
        <div class="box">
            <p>Gastos feitos neste mês:</p>
            <p id="gastos">R$: 1000,00</p>

        </div>
        <div class="box">
            <p>Saldo total</p>
            <p id="saldo">R$: 1000,00</p>


        </div>
    </div>
    <br>
    <div class="conteudo">
        <div id="calendario">
            <ul class="row">
                <li id="mesAnterior">&lt</li>
                <li><span id="Mes">Outubro </span><span id="Ano">2023</span></li>
                <li id="proximoMes">&gt</li>
            </ul>
            <ul class="row">
                <li>Dom</li>
                <li>Seg</li>
                <li>Ter</li>
                <li>Qua</li>
                <li>Qui</li>
                <li>Sex</li>
                <li>Sab</li>

            </ul>
            <ul id="dias">
                <li>1</li>
                <li>2</li>
                <li>3</li>
                <li>4</li>
                <li>5</li>
                <li>6</li>
                <li>7</li>
                <li>8</li>
                <li>9</li>
                <li>10</li>
                <li>11</li>
                <li>12</li>
                <li>13</li>
                <li>14</li>
                <li>15</li>
                <li class="diaAtual">16</li>
                <li>17</li>
                <li>18</li>
                <li>19</li>
                <li>20</li>
                <li>21</li>
                <li>22</li>
                <li>23</li>
                <li>24</li>
                <li>25</li>
                <li>26</li>
                <li>27</li>
                <li>28</li>
                <li>29</li>
                <li>30</li>
                <li>31</li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
        <div id="nota">
            <p>Adicionar nota para dia marcado</p>
            <input type="text" id="texto">
        </div>


    </div>
    <script src="js/calendario.js"></script>
</body>

</html>