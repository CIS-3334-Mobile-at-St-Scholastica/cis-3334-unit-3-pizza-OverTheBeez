package css.pizzaorder;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {

    // initializing pizzaOrder object
    PizzaOrder pizzaOrder = new PizzaOrder();

    public String addToOrder(String topping, Integer size) {
        pizzaOrder.OrderPizza(topping, size);
        return pizzaOrder.toString();
    }

    public String getOrder() {
        List<String> pizza = pizzaOrder.getOrder();
        String pizzaString = "";

        for(String strPizza : pizza) {
            pizzaString = pizzaString + strPizza + "\n";
        }
        return pizzaString;
    }

    }
