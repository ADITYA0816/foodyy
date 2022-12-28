<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>SignUp : Success</title>
    <style>
        #success_box {
            border: 2px solid rgb(18, 173, 18);
            border-radius: 8px;
            background-color: rgb(217, 255, 217);
        }

        .green {
            color: rgb(9, 134, 9);
        }

        #success {
            border-bottom: 2px solid rgb(8, 163, 8);
        }
    </style>

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
            <div id="success_box" class="col-5 p-0 mt-5 text-center">
                <div id="success">
                    <h1 class="mb-0"><i class="fa fa-check green" aria-hidden="true"></i></h1>
                    <h3 class="green pt-0 mt-0">Success</h3>
                </div>
                <div>
                    <h4 class="lead mt-3 green">Registration completed successfully</h4>
                    <h4 class="lead mt-3 green">Please check your registered email for verification</h4>
                    <button class="btn btn-success mt-1 mb-2">OK</button>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

</body>

</html>