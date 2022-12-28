<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>Deliverer : SignUp</title>

    <link rel="stylesheet" href="static/css/form.css">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>

</head>

<body style="background-color: hsla(20, 50%, 98%, 0.733);">

    <div class="header">
        <div class="container-fluid jumbotron banner" style="height: 70px;">
            <div class="container">

                <%@ include file="header.jsp" %>

            </div>
        </div>
    </div>

    <div id="page-container" class="container mt-2">
        <div class="row justify-content-center">
            <div class="col-6 p-0 m-0">

                <div class="">
                    <h3 class="text-primary">Sign Up</h3>
                    <h6 style="font-size: 12px;">or <a href="deliverer_signin.do" class="sublink">Sign In to your account</a>
                    </h6>
                </div>

                <% String errors=(String)request.getAttribute("errors"); %>

                <% if(errors!=null){ %>
                    <h6 class="error-box">
                        <%= errors %>
                    </h6>
                <% } %>

                <form action="deliverer_signup.do" method="post" class="mt-3">

                    <input type="text" id="id_uname" value="${param.name}" class="form-control" name="name"
                        placeholder="Deliverer Name" minlength="5" maxlength="20" autocomplete="off">
                    <small id="name_help" class="form-text text-muted pl-1">Characters Allowed: a-z, A-z,
                        0-9 and spaces.</small>
                    <small id="name_error" class="form-text text-danger pl-1 hide">Invalid Name. Please
                        enter valid Name.</small>

                    <input type="email" id="id_email" value="${param.email}" class="form-control"
                        name="email" placeholder="Email" minlength="10" maxlength="50" autocomplete="off">
                    <small id="email_help" class="form-text text-muted pl-1">Enter valid email.</small>
                    <small id="email_error" class="form-text text-danger pl-1 hide">Invalid Email. Please
                        enter valid Email.</small>

                    <input type="password" id="id_password" class="form-control d-inline-block"
                        style="width: 95%" name="password" placeholder="Password"><i id="view_password"
                        class="fa fa-eye float-right mt-3 pl-0" aria-hidden="true"></i>
                    <small id="password_help" class="form-text text-muted pl-1">Characters Allowed: a-z,
                        A-z, 0-9, - and _.</small>
                    <small id="password_error" class="form-text text-danger pl-1 hide">Invalid Password.
                        Please enter valid Password.</small>

                    <input type="text" id="id_contact" value="${param.contact}" class="form-control"
                        name="contact" placeholder="Contact" minlength="10" maxlength="10"
                        autocomplete="off">
                    <small id="contact_help" class="form-text text-muted pl-1">Characters Allowed: 0-9 and
                        must start with 5-9.</small>
                    <small id="contact_error" class="form-text text-danger pl-1 hide">Invalid Contact.
                        Please enter valid Contact.</small>

                    <div class="form-check justify-content-center mt-3 mb-2 p-0">
                        <div class="g-recaptcha" data-sitekey="6LeoXUgcAAAAABMQyKU_wLRjV9gz1ZKbbaqDR0c1">
                        </div>
                    </div>

                    <input type="submit" class="btn btn-danger self-left mt-2" value="Sign Up">
                </form>
            </div>
        </div>
    </div>


    <%@ include file="footer.jsp" %>

    <script src="static/js/deliverer_signup.js"></script>

</body>

</html>