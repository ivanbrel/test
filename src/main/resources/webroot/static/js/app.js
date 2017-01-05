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

                    $("#ajaxResponse").html("" +
                        "<table class='table table-striped table-reflow'>" +
                            "<thead>" +
                                "<tr>" +
                                    "<th>SENDER NAME:</th>" +
                                    "<th>SENDER CARD ID:</th>" +
                                    "<th>SENDER CARD VALIDITY:</th>" +
                                    "<th>RECIPIENT NAME:</th>" +
                                    "<th>RECIPIENT CARD ID:</th>" +
                                    "<th>RECIPIENT CARD VALIDITY:</th> " +
                                    "<th>Commission brand:</th> " +
                                    "<th>Commission currency:</th> " +
                                    "<th>Commission value:</th> " +
                                    "<th>Transfer amount:</th> " +
                                    "<th>Transfer cost:</th>" +
                                "</tr>" +
                            "</thead>" +
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + data.resp.senderName  +"</td> " +
                                    "<td>" + data.resp.senderCardId + "</td> " +
                                    "<td>" + data.resp.senderCardValidity + "</td> " +
                                    "<td>" + data.resp.recipientName + "</td> " +
                                    "<td>" + data.resp.recipientCardId + "</td> " +
                                    "<td>" + data.resp.recipientCardValidity + "</td> " +
                                    "<td>" + data.resp.commissionBrand + "</td> " +
                                    "<td>" + data.resp.commissionCurrency + "</td> " +
                                    "<td>" + data.resp.commissionValue + "</td> " +
                                    "<td>" + data.resp.transferAmount + "</td> " +
                                    "<td>" + data.resp.transactionCost + "</td> " +
                                "</tr>" +
                            "</tbody>" +
                        "</table>");
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