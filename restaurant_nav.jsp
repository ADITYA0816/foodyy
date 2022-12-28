<div class="col-1 pl-0" id="side_menu">
    <div class="list-group list-group-flush menu-list">

        <button type="button" class="btn btn-lg" data-toggle="collapse" data-target="#demo" id="nav_btn"><i class="fa fa-bars"></i></button>
		<div id="demo" class="collapse">
            <h6 class="nav-label text-muted mt-2 pb-0 mb-0">Home</h6>

            <a href="restaurant_dashboard.do" class="btn-light list-group-item list-group-flush list-group-item-action mt-1 menu-button">
                <i class="fa fa-tachometer"></i><span class="pl-2">Dashboard</span>
            </a>

            <h6 class="nav-label text-muted mt-2  pb-0 mb-0">Log</h6>

            <button type="" class="btn list-group-item list-group-flush list-group-item-action mt-1 menu-button"
            data-toggle="collapse" data-target="#restaurant">
                <i class="fa fa-cutlery"></i><span class="pl-2">Foods</span>
                <div id="restaurant" class="collapse collapsedlist">
                    <a href="add_food.do" class="btn-xsm d-block">Add Food</a>
                    <a href="all_food.do" class="btn-xsm mt-1 pt-0">All Foods</a>
                </div>
            </button>

            <button type="button" class="btn-light list-group-item list-group-flush list-group-item-action mt-1 menu-button"
            data-toggle="collapse" data-target="#Orders">
                <i class="fa fa-shopping-cart"></i><span class="pl-2">Orders</span>
                <div id="Orders" class="collapse collapsedlist">
                    <a href="restaurant_recent_orders.do" class="btn-xsm d-block">Recent Orders</a>
                    <a href="restaurant_all_orders.do" class="btn-xsm mt-1">All Orders</a>
                </div>
            </button>

            <button type="button" class="btn-light list-group-item list-group-flush list-group-item-action mt-1 menu-button"
            data-toggle="collapse" data-target="#Feedback">
                <i class="fa fa-comments"></i><span class="pl-2">Feedbacks</span>
                <div id="Feedback" class="collapse collapsedlist">
                    <a href="" class="btn-xsm">All Feedbacks</a>
                </div>
            </button>

        </div>

    </div>
</div>

            