package ch.sweng.inventory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinitions {
    private Inventory inventory;

    @Given("there are {int} sweaters in the inventory")
    public void withXSweatersInInventory(int nSweaters){
        this.inventory = new Inventory();
        for (int i = 0; i < nSweaters; i++) {
            this.inventory.add(Product.SWEATER);
        }
    }

    @Given("there is only one sweater in the inventory")
    public void withOneSweaterInInventory(){
        this.inventory = new Inventory();
        this.inventory.add(Product.SWEATER);
    }


    @When("the User removes {int} sweaters from the inventory")
    public void userRemovesXSweatersToInventory(int nSweaters){
        for (int i = 0; i < nSweaters; i++) {
            this.inventory.remove(Product.SWEATER);
        }
    }

    @When("the User adds {int} sweaters to the inventory")
    public void userAddsXSweatersToInventory(int nSweaters){
        for (int i = 0; i < nSweaters; i++) {
            this.inventory.add(Product.SWEATER);
        }
    }

    @When("a Client returns {int} sweaters")
    public void returnXSweaters(int nSweaters) {
        for (int i = 0; i < nSweaters; i++) {
            this.inventory.returnAndRefund(Product.SWEATER);
        }
    }


    @Then("there are {int} sweaters in the inventory")
    public void checkThatAdded(int nSweaters){
       assertEquals(this.inventory.getProductQuantity(Product.SWEATER), nSweaters);
    }


}
