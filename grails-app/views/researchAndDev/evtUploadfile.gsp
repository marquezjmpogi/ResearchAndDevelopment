<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
    </head>
    <body>
        <script>
            function sendForm(){
                console.log("boom");
                document.getElementById("myForm").submit();
                /*var filetopass = $("#file2")[0].files[0];                
                var description = $("#description").val(); 
                var oMyForm = new FormData();
                oMyForm.append("file", filetopass);
                oMyForm.append("description", description);*/
                
            }
        </script>    
        <g:form  id="myForm" name="myForm" url="[action:'createDirectory',controller:'FileExample']" enctype="multipart/form-data" method="POST" >
            <input id="photo" class="btn btn-info-form-control" type="file" name="photo" />
        </g:form> 
        <button onclick="sendForm();">SEND</button>
    </body>
</html>
