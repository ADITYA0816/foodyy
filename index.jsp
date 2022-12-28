<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>Foody : Home</title>

</head>

<body style="background-color: hsla(20, 50%, 98%, 0.733);">

    <div class="header">
        <div class="container-fluid jumbotron banner">
            <div class="container">

                <%@ include file="header.jsp" %>

                <div class="row mt-3">
                    <h1 class="col text-center slogan">Foody</h1>
                </div>

                <div class="row mt-1">
                    <h3 class="col text-center slogan">Order, Delivery and Take-out</h3>
                </div>

                <div class="row mt-1">
                    <h6 class="col text-center slogan">Find Best Restaurants and Food Near You</h6>
                </div>

                <div class="row mt-3 justify-content-center">
                    <div class="col-4 text-center slogan">
                        <div class="input-group rounded">
                            <input type="search" class="form-control rounded" placeholder="I would like to eat"
                                aria-label="Search" aria-describedby="search-addon" />
                            <div class="btn btn-danger ml-2">
                                Search
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row slogan justify-content-around mt-4">
                    <div class="col-2">1. Choose Restaurant</div>
                    <div class="col-2">2. Order Food</div>
                    <div class="col-2">3. Get it Delivered</div>
                </div>

            </div>
        </div>

    </div>

    <div class="container">
        <div class="row">
            <h5 class="col text-center bodytextcol d-block">Popular Dishes of the Month</h5>
        </div>
        <div class="row">
            <span class="col text-center bodytextcol font-weight-light text-small">The Easiest Way to Your Favourite
                Food</span>
        </div>

        <div class="row mt-3 justify-content-between">
            <div class="col-3">
                <div class="card" style="width: 16rem;">
                    <img class="card-img-top foodcardheight" src="static/images/food/food1.jfif" alt="Card image cap">
                    <div class="pintime">35min</div>
                    <div class="card-body p-1 pl-2 text-center">
                        <h5 class="card-title d-inline-block">Samosa</h5>
                    </div>
                    <div class="card-body mt-0 p-0 pr-1 pb-1">
                        <a href="#" class="btn btn-sm btn-outline-danger float-right">Order Now</a>
                    </div>
                </div>
            </div>
            <div class="col-3">
                <div class="card" style="width: 16rem;">
                    <img class="card-img-top foodcardheight" src="static/images/food/Food2.jpg" alt="Card image cap">
                    <div class="pintime">35min</div>
                    <div class="card-body p-1 pl-2 pb-0 mb-0 text-center text-muted">
                        <h5 class="card-title d-inline-block">Kathi Rolls</h5>
                    </div>
                    <div class="card-body mt-0 p-0 pr-1 pb-1">
                        <a href="#" class="btn btn-sm btn-outline-danger float-right">Order Now</a>
                    </div>
                </div>
            </div>
            <div class="col-3">
                <div class="card" style="width: 16rem;">
                    <img class="card-img-top foodcardheight" src="static/images/food/Food1.jpg" alt="Card image cap">
                    <div class="pintime">35min</div>
                    <div class="card-body p-1 pl-2 text-center text-muted">
                        <h5 class="card-title d-inline-block">Cutlet</h5>
                    </div>
                    <div class="card-body mt-0 p-0 pr-1 pb-1">
                        <a href="#" class="btn btn-sm btn-outline-danger float-right">Order Now</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-5">
            <div class="col">
                <h5 class="text-center bodytextcol">Easy 3 Step Order</h5>
            </div>
        </div>

        <div class="row mt-25 justify-content-between mb-5">
            <div class="col-3">
                <div class="card" style="width: 16rem;">
                    <img class="card-img-top foodcardheight" src="static/images/res.jpg" alt="Card image cap">
                    <div class="pinstep w-100 text-center">
                        1
                    </div>
                    <div class="pinline w-100">
                        <h5 class="text-center mb-1">Choose a Restaurant</h5>
                    </div>
                </div>
            </div>
            <div class="col-3">
                <div class="card" style="width: 16rem;">
                    <img class="card-img-top foodcardheight" src="static/images/food.jpg" alt="Card image cap">
                    <div class="pinstep w-100 text-center">
                        2
                    </div>
                    <div class="pinline w-100">
                        <h5 class="text-center mb-1">Choose a Tasty Dish</h5>
                    </div>
                </div>
            </div>
            <div class="col-3">
                <div class="card" style="width: 16rem;">
                    <img class="card-img-top foodcardheight" src="static/images/delivery.jpg" alt="Card image cap">
                    <div class="pinstep w-100 text-center">
                        3
                    </div>
                    <div class="pinline w-100">
                        <h5 class="text-center mb-1">Get it Delivered</h5>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <%@ include file="footer.jsp" %>


    <script src="static/js/popper.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
</body>

</html>