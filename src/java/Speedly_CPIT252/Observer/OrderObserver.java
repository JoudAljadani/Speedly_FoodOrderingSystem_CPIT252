package Speedly_CPIT252.Observer;

import Speedly_CPIT252.OrderManagement.Order;

//Observer abstract class for order notifications
public abstract class OrderObserver {

    //the order being observed for notifications
    protected Order order;

    //called automatically when order state changes
    public abstract void update();
}