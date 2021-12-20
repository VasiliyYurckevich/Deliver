package com.deliver;

import android.widget.TextView;

import com.deliver.database.DatabasesHelper;

import org.jacop.constraints.SumWeight;
import org.jacop.constraints.XgteqC;
import org.jacop.constraints.XgteqY;
import org.jacop.constraints.XlteqC;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SimpleSelect;
import org.jacop.search.SmallestDomain;

import java.util.ArrayList;

public class Diet {

    Store store;
    ArrayList<IntVar> allVars;
    IntVar[] x;
    public IntVar cost;
    int n;
    int m;
    int z = 0;

    public int[] limits  = new int[4];
    public int[] limits1  = new int[4];

    public ArrayList<IntVar> getAllVars() {
        return allVars;
    }

    public IntVar[] getX() {
        return x;
    }

    public IntVar getCost() {
        return cost;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public String[] getFoodMC() {
        return foodMC;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getFoodBK() {
        return foodBK;
    }

    public String[] getFoodKFC() {
        return foodKFC;
    }


    public Diet(int[] limits,int[] limits1) {
        this.limits = limits;
        this.limits1 = limits1;
    }


    public void model(int restSelId) {
        DatabasesHelper db = new DatabasesHelper();
        store = new Store();
        n = 4;
        String[] food;
        int[] price;
        int[][] matrix;
        int[] maxVals = {200000, 2000000, 2000000, 200000};
        switch (restSelId){
            case  (0):
                price = db.getPriceMC();
                m =price.length ;
                food = db.getFoodMC();
                matrix = db.getMatrixMC() ;
                break;
            case (1):
                price = db.getPriceKFC();
                m =price.length ;
                food = db.getFoodKFC();
                matrix = db.getMatrixKFC() ;
                break;
            case (2):
                 price = db.getPriceBK();
                 m =price.length ;
                 food = db.getFoodBK();
                 matrix = db.getMatrixBK() ;
                break;
            default:
                price = db.getPriceMC();
                m =price.length ;
                food = db.getFoodMC();
                matrix = db.getMatrixMC() ;
                break;
        }
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
            store.impose(new XgteqC(sums[i],limits[i]));
            //store.impose(new XlteqC(sums[i],limits1[i]));
        }

        cost = new IntVar(store, "cost", 0, 100000);
        store.impose( new SumWeight(x, price, cost) );


        allVars = new ArrayList<IntVar>();
        for(IntVar v : x)
            allVars.add(v);

        allVars.add(cost);


    }


    public int search(int[] price,String[] name,int restSelId) {
        int fff =0;
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
            fff = cost.value();
            System.out.println("hui nahiusdvadsvasdvsdvdsv " + fff);
            //coast = Integer.toString(cost.value());
            for(int y = 0; y < m; y++){
                    if (x[y].value()!=0){
                        price[z] = x[y].value();
                        switch (restSelId){
                            case  (0):
                                name[z]=foodMC[y];
                                break;
                            case (1):
                                name[z]=foodKFC[y];
                                break;
                            case (2):
                                name[z]=foodBK[y];
                                break;

                        }
                        z++;

                }
            }
        }  else {
           //coast = "No solution";
        }
        return fff;
    }































    String[] foodMC = {"Big Chicken Snack Box", "Snack box with wings", "Chicken wings 3x", "Chicken wings 5x", "Chicken wings 7x", "Strips 3x", "Strips 5x", "Strips 7x", "Cheese sticks 3x", "Cheese sticks 15x", "Chicken McNuggets 6x", "Chicken McNuggets 18x", "Big Mac Bacon", "Big Tasty Roll", "McChicken Premier", "Big Tasty", "Triple Cheeseburger", "Big Mac", "Royal", "Big Tasty with chicken", "Caesar Roll", "McChicken", "Chickenburger", "Cheeseburger", "Hamburger", "Double Filet-O-Fish", "Filet-O-Fish", "French Fries S", "French Fries M", "French Fries L", "Potato WedgesM", "Potato Wedges L", "McFlurry", "Sundae", "Milkshake", "Pie", "Muffin", "Coke", "Orage Juice", "Bonaqua"};
    String[] ingredients = {"Calories", "Proteins", "Fats", "Fat"};
    String[] foodBK = {"WHOPPER ", "BIG KING ", "WHOPPER  Junior", "WHOPPER  with cheese", "Cheeseburger", "Hamburger", "Double Whopper", "Bekonizer", "Triple WHOPPER ", "Steakhouse", "Double Cheeseburger", "Whopper Roll", "Caesar King", "Grillburger", "King Go Rustic", "King Go Nuggets", "Barbecue Chicken Grill", "Long Chicken", "Chickenburger", "Chicken King", "Strips King", "Fish King", "Fish Roll", "Caesar Roll with strips", "RUSTIC potatoes", "King Fries", "Wings King", "King Nuggets", "Onion Rings", "King Bouquet Big Mix", "King Bouquet Wings XXL", "Bucket King Bouquet Snack&Mix", "Caesar salad with shrimp", "Orange juice", "Pepsi", "Strawberry-vanilla pie", "Strawberry donat", "Waffle Cone", "Sunday Chocolate", "Vanilla shake", "Hot brownie with ice cream"};
    String[] foodKFC = {"Boxmaster","Schefburger","The shefburger is spicy","Double Schefburger","Double Shefburger spicy","Cheeseburger","Longer","Ai-Twister Cheese","Strips 5 pcs. original","Strips 2 pcs. Original","Strips 3 pcs. original","Strips 5 pcs. sharp","Strips 2 pcs. sharp","Strips 3 pcs. Sharp","Chicken wings 3 pieces","Chicken wings 5 pieces","Chicken wings 8 pieces","Caesar salad with sauce","French fries  small","French fries standard","Free Basket","Ice cream (cone)","Cheesecake with topping and raisins","Cherry pie","Americano","Cappuccino","Milk Shake","French fries  small","French fries standard"};


}
