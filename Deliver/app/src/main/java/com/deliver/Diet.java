package com.deliver;

import android.widget.TextView;

import com.deliver.database.DatabasesHelper;

import org.jacop.constraints.SumWeight;
import org.jacop.constraints.XgteqC;
import org.jacop.constraints.XlteqC;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.*;


import java.util.ArrayList;

public class Diet {

    Store store;
    ArrayList<IntVar> allVars;
    IntVar[] x;
    IntVar cost;
    int n;
    int m;


    String[] foodMC = {"Big Chicken Snack Box", "Snack box with wings", "Chicken wings 3x", "Chicken wings 5x", "Chicken wings 7x", "Strips 3x", "Strips 5x", "Strips 7x", "Cheese sticks 3x", "Cheese sticks 15x", "Chicken McNuggets 6x", "Chicken McNuggets 18x", "Big Mac Bacon", "Big Tasty Roll", "McChicken Premier", "Big Tasty", "Triple Cheeseburger", "Big Mac", "Royal", "Big Tasty with chicken", "Caesar Roll", "McChicken", "Chickenburger", "Cheeseburger", "Hamburger", "Double Filet-O-Fish", "Filet-O-Fish", "French Fries S", "French Fries M", "French Fries L", "Potato WedgesM", "Potato Wedges L", "McFlurry", "Sundae", "Milkshake", "Pie", "Muffin", "Coke", "Orage Juice", "Bonaqua"};
    String[] ingredients = {"Calories", "Proteins", "Fats", "Fat"};
    String[] foodBK = {"WHOPPER ", "BIG KING ", "WHOPPER  Junior", "WHOPPER  with cheese", "Cheeseburger", "Hamburger", "Double Whopper", "Bekonizer", "Triple WHOPPER ", "Steakhouse", "Double Cheeseburger", "Whopper Roll", "Caesar King", "Grillburger", "King Go Rustic", "King Go Nuggets", "Barbecue Chicken Grill", "Long Chicken", "Chickenburger", "Chicken King", "Strips King", "Fish King", "Fish Roll", "Caesar Roll with strips", "RUSTIC potatoes", "King Fries", "Wings King", "King Nuggets", "Onion Rings", "King Bouquet Big Mix", "King Bouquet Wings XXL", "Bucket King Bouquet Snack&Mix", "Caesar salad with shrimp", "Orange juice", "Pepsi", "Strawberry-vanilla pie", "Strawberry donat", "Waffle Cone", "Sunday Chocolate", "Vanilla shake", "Hot brownie with ice cream"};
    String[] foodKFC = {"Boxmaster","Schefburger","The shefburger is spicy","Double Schefburger","Double Shefburger spicy","Cheeseburger","Longer","Ai-Twister Cheese","Strips 5 pcs. original","Strips 2 pcs. Original","Strips 3 pcs. original","Strips 5 pcs. sharp","Strips 2 pcs. sharp","Strips 3 pcs. Sharp","Chicken wings 3 pieces","Chicken wings 5 pieces","Chicken wings 8 pieces","Caesar salad with sauce","French fries  small","French fries standard","Free Basket","Ice cream (cone)","Cheesecake with topping and raisins","Cherry pie","Americano","Cappuccino","Milk Shake","French fries  small","French fries standard"};


    public void model() {
        DatabasesHelper db = new DatabasesHelper();
        Diet diet = new Diet();
        store = new Store();
        n = 4; // number of ingredients
        int[] maxVals = {200000, 2000000, 2000000, 200000};
        int[] price = db.getPriceBK();
        int[] limits  = {1500, 70, 80, 80};
        m =price.length ;
        String[] food = db.getFoodBK();
        int[][] matrix = db.getMatrixBK() ;
        x = new IntVar[m];
        for(int i = 0; i < m; i++) {
            x[i] = new IntVar(store,
                    "x_" + i,
                    0,
                    10);
        }

        IntVar[] sums = new IntVar[n];
        for(int i = 0; i < n; i++) {
            sums[i] = new IntVar(store, "sums_" + i, 0, 10000);
            store.impose(new SumWeight(x, matrix[i], sums[i]));
            store.impose(new XgteqC(sums[i], limits[i]));
        }

        cost = new IntVar(store, "cost", 0, 100000);
        store.impose( new SumWeight(x, price, cost) );


        allVars = new ArrayList<IntVar>();
        for(IntVar v : x)
            allVars.add(v);

        allVars.add(cost);


    }


    public void search(TextView TC) {

        SelectChoicePoint select =
                new SimpleSelect(allVars.toArray(new IntVar[1]),
                        new SmallestDomain(),
                        new IndomainMin()
                );

        Search label = new DepthFirstSearch ();
        label.getSolutionListener().searchAll(true);
        label.getSolutionListener().recordSolutions(true);
        boolean result = label.labeling(store, select, cost);
        if (result) {
            TC.setText(cost.value());
            for(int i = 0; i < m; i++) {
                System.out.println(foodBK[i] + ": " + x[i].value());
            }
        }  else {
            System.out.println("No solution.");
        }

    }
}
