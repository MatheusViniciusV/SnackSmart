<%-- 
    Document   : Calendario
    Created on : 24 de out. de 2023, 09:33:19
    Author     : marco
--%>
  <div id="container">
            <div id="header">
              <div id="monthDisplay"></div>
      
              <div>
                <button id="backButton", class="botaoCalendar">Voltar</button>
                <button id="nextButton", class="botaoCalendar">Próximo</button>
              </div>
                
            </div>
      
            <div id="weekdays">
              <div>Domingo</div>
              <div>Segunda</div>
              <div>Terça</div>
              <div>Quarta</div>
              <div>Quinta</div>
              <div>Sexta</div>
              <div>Sábado</div>
            </div>
      
      
            <!-- div dinamic -->
            <div id="calendar" ></div>
      
         
        </div>
      
        <div id="newEventModal">
          <h2 id="newEventeH2">Nota</h2>
      
          <input id="eventTitleInput" placeholder="Digite aqui"/>
      
          <button id="saveButton", class="botaoCalendar">Salvar</button>
          <button id="cancelButton", class="botaoCalendar">Cancelar</button>
        </div>
      
        <div id="deleteEventModal">
          <h2>Evento</h2>
      
          <div id="eventText"></div><br>
      
      
          <button id="deleteButton", class="botaoCalendar">Deletar</button>
          <button id="closeButton", class="botaoCalendar">Fechar</button>
        </div>
      
        <div id="modalBackDrop"></div>
        <script src="javaScript\Calendario.js"></script>
