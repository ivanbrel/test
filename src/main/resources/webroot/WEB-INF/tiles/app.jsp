<!DOCTYPE html>
<!-- saved from url=(0049)http://v4-alpha.getbootstrap.com/examples/cover/# -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>test app</title>
    <!-- Bootstrap core CSS -->
    <link href="../../static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../../static/css/cover.css" rel="stylesheet">
</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand">test app</h3>
                    <nav class="nav nav-masthead">
                        <a class="nav-link active" href="/">Home</a>
                        <a class="nav-link" href="/app">App</a>
                        <a class="nav-link" href="">Contact</a>
                    </nav>
                </div>
            </div>

            <div class="container">

                <form id="myAjaxRequestForm" class="">

                    <p> Cart data</p>
                    <div class="form-group row">
                        <label for="numberCard" class="col-xs-2 col-form-label">Number card</label>
                        <div class="col-xs-10">
                            <input class="form-control" type="number" id="numberCard" name="numberCard" required="required">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="holdersName" class="col-xs-2 col-form-label">Holder's name</label>
                        <div class="col-xs-10">
                            <input class="form-control" type="text" id="holdersName" name="holdersName" required="required">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="validity" class="col-xs-2 col-form-label">Validity</label>
                        <div class="col-xs-10">
                            <input class="form-control" type="date" id="validity" name="validity" required="required">
                        </div>
                    </div>

                    <p> Enter the recipient of the sender data</p>
                    <div class="form-group row">
                        <label for="numberCardRecipient" class="col-xs-2 col-form-label">Number card</label>
                        <div class="col-xs-10">
                            <input class="form-control" type="number"  id="numberCardRecipient" name="numberCardRecipient" required="required">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="nameRecipient" class="col-xs-2 col-form-label">Name recipient</label>
                        <div class="col-xs-10">
                            <input class="form-control" type="text" id="nameRecipient" name="nameRecipient" required="required">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="recipientValidity" class="col-xs-2 col-form-label">Validity</label>
                        <div class="col-xs-10">
                            <input class="form-control" type="date" id="recipientValidity" name="recipientValidity" required="required">
                        </div>
                    </div>

                    <%--Currency--%>
                    <div class="form-group row">
                        <label for="currency" class="col-xs-2 col-form-label">Currency</label>
                        <div class="col-xs-10">
                            <select class="form-control" id="currency" name="currency">
                                <option>RUB</option>
                                <option>BYN</option>
                                <option>USD</option>
                                <option>EUR</option>
                            </select>
                        </div>
                    </div>

                    <%--summ--%>
                    <p>Transfer amount</p>
                    <div class="form-group row">
                        <label for="amount" class="col-xs-2 col-form-label">Transfer amount</label>
                        <div class="col-xs-10">
                            <input class="form-control" type="number" id="amount" name="amount" required="required">
                        </div>
                    </div>

                    <button id="myButton" type="submit" value="Add" class="btn btn-primary">Transfer cash</button>
                </form>
            </div>

            <%--<div id="anotherSection" style="display: none">--%>
            <div id="anotherSection">
                <div id="ajaxResponse"></div>
            </div>

            <div class="mastfoot">
                <div class="inner">
                    <p> by <a href="https://www.linkedin.com/in/ivan-brel-32a0b0124?trk=hp-identity-name">ibrel</a>.</p>
                </div>
            </div>

        </div>

    </div>

</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="../../static/js/jquery.min.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="../../static/js/app.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
</body></html>