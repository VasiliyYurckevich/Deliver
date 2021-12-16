//package com.example.deliverapp;
//
//import android.widget.TextView;
//
//import org.jacop.constraints.SumWeight;
//import org.jacop.constraints.XgteqC;
//import org.jacop.core.IntVar;
//import org.jacop.core.Store;
//import org.jacop.search.*;
//
//import java.util.ArrayList;
//
//public class Diet {
//
//    Store store;
//    ArrayList<IntVar> allVars;
//    IntVar[] x;
//    IntVar cost;
//    int n;
//    int m;
//
//    String[] food = {"Big Chicken Snack Box","Snack box with wings","Chicken wings 3x","Chicken wings 5x","Chicken wings 7x","Strips 3x","Strips 5x","Strips 7x","Cheese sticks 3x","Cheese sticks 15x","Chicken McNuggets 6x","Chicken McNuggets 18x","Big Mac Bacon","Big Tasty Roll","McChicken Premier","Big Tasty","Triple Cheeseburger","Big Mac","Royal","Big Tasty with chicken","Caesar Roll","McChicken","Chickenburger","Cheeseburger","Hamburger","Double Filet-O-Fish","Filet-O-Fish","French Fries S","French Fries M","French Fries L","Potato WedgesM","Potato Wedges L","McFlurry","Sundae","Milkshake","Pie","Muffin","Coke","Orage Juice","Bonaqua" };
//    String[] ingredients = {"Calories", "Chocolate", "Sugar", "Fat"};
//
//
//    /**
//     *
//     *  Model
//     *
//     */
//    public void model() {
//
//        store = new Store();
//
//        n = 4; // number of ingredients
//
//        int[] maxVals = {200000, 2000000, 2000000, 200000};
//        int[] price   = {1490,790,420,720,920,450,650,850,420,1950,530,1170,690,780,670,930,700,600,610,930,720,550,250,250,230,700,600,260,330,370,350,350,420,280,340,260,310,270,340,170}; // in cents
//        int[] limits  = {1500, 70, 80, 80};
//        m =price.length ;
//        int[][] matrix = {{1369,549,347,574,800,225,375,523,383,1979,268,894,580,516,530,702,598,509,597,838,537,365,295,304,254,478,333,239,341,448,330,479,324,243,249,187,265,170,240,0},  // calories
//                {  57,28,19,32,45,13,22,30,12,61,17,56,24,23,19,31,38,27,36,34,17,13,9,16,13,25,15,3,4,5,4,6,6,5,6,3,5,0,5,0},
//                {  252,25,23,38,52,11,18,25,27,139,14,47,29,31,31,47,36,26,32,48,34,17,14,13,9,23,13,12,17,22,15,22,8,5,6,8,3,0,1,0},
//                { 109,54,16,27,37,19,31,43,24,112,18,60,42,36,45,39,30,42,39,66,41,40,32,30,30,42,36,29,42,55,42,61,57,45,43,27,55,42,47,0}};
//
//
//
//        x = new IntVar[m];
//        for(int i = 0; i < m; i++) {
//            x[i] = new IntVar(store,
//                    "x_" + i,
//                    0,
//                    10);
//        }
//
//        IntVar[] sums = new IntVar[n];
//        for(int i = 0; i < n; i++) {
//            sums[i] = new IntVar(store, "sums_" + i, 0, 10000);
//            store.impose(new SumWeight(x, matrix[i], sums[i]));
//            store.impose(new XgteqC(sums[i], limits[i]));
//        }
//
//        cost = new IntVar(store, "cost", 0, 100000);
//        store.impose( new SumWeight(x, price, cost) );
//
//
//        allVars = new ArrayList<IntVar>();
//        for(IntVar v : x)
//            allVars.add(v);
//
//        allVars.add(cost);
//
//
//    }
//
//
//    public void search(TextView TC) {
//
//        SelectChoicePoint select =
//                new SimpleSelect(allVars.toArray(new IntVar[1]),
//                        new SmallestDomain(),
//                        new IndomainMin()
//                );
//
//        Search label = new DepthFirstSearch ();
//        label.getSolutionListener().searchAll(true);
//        label.getSolutionListener().recordSolutions(true);
//        boolean result = label.labeling(store, select, cost);
//        if (result) {
//            TC.setText(cost.value());
//            for(int i = 0; i < m; i++) {
//                System.out.println(food[i] + ": " + x[i].value());
//            }
//        }  else {
//            System.out.println("No solution.");
//        }
//
//    }
//}
