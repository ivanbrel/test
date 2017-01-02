$(document).ready(function() {

    //Stops the submit request
    $("#myAjaxRequestForm").submit(function(e){
        e.preventDefault();
    });

    //checks for the button click event
    $("#myButton").click(function(e){

        //get the form data and then serialize that
        dataString = $("#myAjaxRequestForm").serialize();

        //make the AJAX request, dataType is set to json
        //meaning we are expecting JSON data in response from the server
        $.ajax({
            type: "POST",
            url: "/apppost",
            data: dataString,
            dataType: "json",

            //if received a response from the server
            success: function( data, textStatus, jqXHR) {

                console.log(data);
                if(data.success){

                    $("#myAjaxRequestForm").hide();

                    $("#ajaxResponse").html("");
                    $("#ajaxResponse").append("<b>SENDER NAME:</b> " + data.resp.senderName + "<br>");
                    $("#ajaxResponse").append("<b>SENDER CARD ID:</b> " + data.resp.senderCardId + "<br>");
                    $("#ajaxResponse").append("<b>SENDER CARD VALIDITY:</b>" + data.resp.senderCardValidity + "<br>");
                    $("#ajaxResponse").append("<b>RECIPIENT NAME:</b> " + data.resp.recipientName + "<br>");
                    $("#ajaxResponse").append("<b>RECIPIENT CARD ID:</b> " + data.resp.recipientCardId + "<br>");
                    $("#ajaxResponse").append("<b>RECIPIENT CARD VALIDITY:</b> " + data.resp.recipientCardValidity + "<br>");
                    $("#ajaxResponse").append("<b>Commission brand:</b> " + data.resp.commissionBrand + "<br>");
                    $("#ajaxResponse").append("<b>Commission currency:</b> "+  data.resp.commissionCurrency + "<br>");
                    $("#ajaxResponse").append("<b>Commission value:</b> "+ + data.resp.commissionValue + "<br>");
                    $("#ajaxResponse").append("<b>Transfer amount:</b> " + data.resp.transferAmount + "<br>");
                    $("#ajaxResponse").append("<b>Transfer cost:</b> " + data.resp.transactionCost);
                }
                //display error message
                else {
                    $("#ajaxResponse").html("<div><b>Transaction fail!</b></div>");

                }
            },

            //If there was no resonse from the server
            error: function(jqXHR, textStatus, errorThrown){
                console.log("Something really bad happened " + textStatus);
                $("#ajaxResponse").html(jqXHR.responseText);
            },

            //capture the request before it was sent to server
            beforeSend: function(jqXHR, settings){
                //adding some Dummy data to the request
                settings.data += "&dummyData=whatever";
                //disable the button until we get the response
                $('#myButton').attr("disabled", true);
            },

            //this is called after the response or error functions are finsihed
            //so that we can take some action
            complete: function(jqXHR, textStatus){
                //enable the button
                $('#myButton').attr("disabled", false);
            }

        });
    });

});