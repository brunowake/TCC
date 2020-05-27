$(function() {

    $("#inscricaoMaratonaForm input,#inscricaoMaratonaForm textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
            // Prevent spam click and default submit behaviour
            event.preventDefault();
            
            // get values from FORM
            var nomeTime = $("input#nomeTime").val();
 
            // competidor 1
            var nomeCompetidor1 = $("input#nomeCompetidor1").val();
            var nomeTurma1 = $("select#nomeTurma1").val();
            var nascimentoCompetidor1 = $("input#nascimentoCompetidor1").val();
            var cpfCompetidor1 = $("input#cpfCompetidor1").val();
            var emailCompetidor1 = $("input#emailCompetidor1").val();
 
            // competidor 2
            var nomeCompetidor2 = $("input#nomeCompetidor2").val();
            var nomeTurma2 = $("select#nomeTurma2").val();
            var nascimentoCompetidor2 = $("input#nascimentoCompetidor2").val();
            var cpfCompetidor2 = $("input#cpfCompetidor2").val();
            var emailCompetidor2 = $("input#emailCompetidor2").val();
 
            // competidor 3
            var nomeCompetidor3 = $("input#nomeCompetidor3").val();
            var nomeTurma3 = $("select#nomeTurma3").val();
            var nascimentoCompetidor3 = $("input#nascimentoCompetidor3").val();
            var cpfCompetidor3 = $("input#cpfCompetidor3").val();
            var emailCompetidor3 = $("input#emailCompetidor3").val();
 
            // antispam
            var antispam = $("input#antispam").val();

            var nome = nomeCompetidor1; // For Success/Failure Message
            // Check for white space in name for Success/Fail message
            if (nome.indexOf(' ') >= 0) {
                nome = name.split(' ').slice(0, -1).join(' ');
            }
            $('#myPleaseWait').modal('show');
            $.ajax({
                url: "././php/inscricaoMaratona.php",
                type: "POST",
                dataType: 'json',
                data: {
                    nomeTime: nomeTime,					
                    nomeCompetidor1: nomeCompetidor1,
                    nomeTurma1: nomeTurma1,                    
                    nascimentoCompetidor1: nascimentoCompetidor1,
                    cpfCompetidor1: cpfCompetidor1,
                    emailCompetidor1: emailCompetidor1,					
                    nomeCompetidor2: nomeCompetidor2,
                    nomeTurma2: nomeTurma2,                  
                    nascimentoCompetidor2: nascimentoCompetidor2,
                    cpfCompetidor2: cpfCompetidor2,
                    emailCompetidor2: emailCompetidor2,
					
                    nomeCompetidor3: nomeCompetidor3,
                    nomeTurma3: nomeTurma3,                    
                    nascimentoCompetidor3: nascimentoCompetidor3,
                    cpfCompetidor3: cpfCompetidor3,
                    emailCompetidor3: emailCompetidor3,
                    antispam: antispam
                },
                cache: false,
                success: function(json) {
                    setTimeout(function(){
                        $('#myPleaseWait').modal('hide');
                    }, 1000);                    
                    var erros = ''
                    $('input').parents("div.control-group").removeClass('has-error');
                    for(var tipoNome in json) {
                        if(json[tipoNome] != '')
                            erros += json[tipoNome] + '<br>';
                    }
                    if(json.nomeTime != '') {
                         $('#nomeTime').parents("div.control-group").addClass('has-error');
                    }
                    
                    if(json.nomeCompetidor1 != '') {
                         $('#nomeCompetidor1').parents("div.control-group").addClass('has-error');
                    }
                    if(json.nomeTurma1 != '') {
                         $('#nomeTurma1').parents("div.control-group").addClass('has-error');
                    }
                    if(json.nascimentoCompetidor1 != '') {
                         $('#nascimentoCompetidor1').parents("div.control-group").addClass('has-error');
                    }
                    if(json.cpfCompetidor1 != '') {
                         $('#cpfCompetidor1').parents("div.control-group").addClass('has-error');
                    }
                    if(json.emailCompetidor1 != '') {
                         $('#emailCompetidor1').parents("div.control-group").addClass('has-error');
                    }
                    if(json.nomeCompetidor2 != '') {
                         $('#nomeCompetidor2').parents("div.control-group").addClass('has-error');
                    }
                    if(json.nomeTurma2 != '') {
                         $('#nomeTurma2').parents("div.control-group").addClass('has-error');
                    }
                    if(json.nascimentoCompetidor2 != '') {
                         $('#nascimentoCompetidor2').parents("div.control-group").addClass('has-error');
                    }
                    if(json.cpfCompetidor2 != '') {
                         $('#cpfCompetidor2').parents("div.control-group").addClass('has-error');
                    }
                    if(json.emailCompetidor2 != '') {
                         $('#emailCompetidor2').parents("div.control-group").addClass('has-error');
                    }                    
                    if(json.nomeCompetidor3 != '') {
                         $('#nomeCompetidor3').parents("div.control-group").addClass('has-error');
                    }
                    if(json.nomeTurma3 != '') {
                         $('#nomeTurma3').parents("div.control-group").addClass('has-error');
                    }
                    if(json.nascimentoCompetidor3 != '') {
                         $('#nascimentoCompetidor3').parents("div.control-group").addClass('has-error');
                    }
                    if(json.cpfCompetidor3 != '') {
                         $('#cpfCompetidor3').parents("div.control-group").addClass('has-error');
                    }
                    if(json.emailCompetidor3 != '') {
                         $('#emailCompetidor3').parents("div.control-group").addClass('has-error');
                    }
                    if(json.antispam != '') {
                         $('#antispam').parents("div.control-group").addClass('has-error');
                    }

                    if(erros === ''){
                        // Enable button & show success message
                        $('#container').addClass('hide');
                        $('#btnAnterior').addClass('hide');
                        $('#btnProximo').addClass('hide');
                        $('#btnSubmit').addClass('hide');
                        $('#container').addClass('hide');
                        $('#antispamGroup').addClass('hide');
                        $('#successInscricao').html("<div class='alert alert-success'>");
                        $('#successInscricao > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                            .append("</button>");
                        $('#successInscricao > .alert-success')
                            .append("<strong>Sua inscrição foi enviada com sucesso!</strong>");
                        $('#successInscricao > .alert-success')
                            .append('</div>');

                        //clear all fields
                        $('#inscricaoForm').trigger("reset");
                        
                    }else{
                        // Fail message
                        $('#successInscricao').html("<div class='alert alert-danger'>");
                        $('#successInscricao > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                            .append("</button>");
                        $('#successInscricao > .alert-danger').append("<strong>Aparentemente o formulário não foi preenchido corretamente!<br></strong>");
                        $('#successInscricao > .alert-danger').append("<strong>" + erros + "</strong>");
                        $('#successInscricao > .alert-danger').append('</div>');
                        

                    }
                },
                error: function() {
                    // Fail message
                    setTimeout(function(){
                        $('#myPleaseWait').modal('hide');
                    }, 1000);                    

                    $('#successInscricao').html("<div class='alert alert-danger'>");
                    $('#successInscricao > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#successInscricao > .alert-danger').append("<strong>Desculpe " + nome + ", aparentemente o servidor não está respondendo. Tente novamente mais tarde!");
                    $('#successInscricao > .alert-danger').append('</div>');
                },
            })
        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
});

// When clicking on Full hide fail/success boxes
$('#name').focus(function() {
    $('#success').html('');
});
