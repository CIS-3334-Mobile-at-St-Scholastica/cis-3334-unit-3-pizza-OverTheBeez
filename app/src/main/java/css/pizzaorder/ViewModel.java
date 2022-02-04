package css.pizzaorder;

import android.os.Handler;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<String> orderStatus;

    // initializing pizzaOrder object
    PizzaOrder pizzaOrder = new PizzaOrder();

    public String addToOrder(String topping, Integer size) {
        pizzaOrder.OrderPizza(topping, size);
        return pizzaOrder.toString();
    }

    public void placeOrder() {
        orderStatus.postValue("Order Placed");
        //pizzaOrder.PlaceOrder();
        startPizzaTimer();
    }

    public String getOrder() {
        List<String> pizza = pizzaOrder.getOrder();
        String pizzaString = "";

        for(String strPizza : pizza) {
            pizzaString = pizzaString + strPizza + "\n";
        }
        return pizzaString;
    }

    public MutableLiveData<String> getOrderStatus() {
        if(orderStatus == null) {
            orderStatus = new MutableLiveData<String>();
        }
        return orderStatus;
    }

    private static Runnable pizzaTimer;
    private Handler handler;
    private void startPizzaTimer(){
        handler = new Handler();
        pizzaTimer = new PizzaTimer();
        handler.postDelayed(pizzaTimer, 1000);
    }
    private class PizzaTimer implements Runnable {
        private Integer count = 0;
        @Override
        public void run() {
            count++;
            if (count > 4) {
                orderStatus.postValue("Pizza ready to eat");
            } else if (count > 3) {
                orderStatus.postValue("Pizza is cooling");
                handler.postDelayed(this, 2000);
            } else if (count > 2) {
                orderStatus.postValue("Pizza is baking");
                handler.postDelayed(this, 5000);
            } else {
                orderStatus.postValue("Pizza is being prepared  ");
                handler.postDelayed(this, 2000);
            }

        }
    }

    }
