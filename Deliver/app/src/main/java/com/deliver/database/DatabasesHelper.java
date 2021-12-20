package com.deliver.database;

public class DatabasesHelper {



    public  int[] priceMC   = {1490,790,420,720,920,450,650,850,420,1950,530,1170,690,780,670,930,700,600,610,930,720,550,250,250,230,700,600,260,330,370,350,350,420,280,340,260,310,270,340,170}; // in cents



    int[] limits  = {};

    public  int[][] matrixMC = {{1369,549,347,574,800,225,375,523,383,1979,268,894,580,516,530,702,598,509,597,838,537,365,295,304,254,478,333,239,341,448,330,479,324,243,249,187,265,170,240,0},  // calories
            {  57,28,19,32,45,13,22,30,12,61,17,56,24,23,19,31,38,27,36,34,17,13,9,16,13,25,15,3,4,5,4,6,6,5,6,3,5,0,5,0},
            {  252,25,23,38,52,11,18,25,27,139,14,47,29,31,31,47,36,26,32,48,34,17,14,13,9,23,13,12,17,22,15,22,8,5,6,8,3,0,1,0},
            { 109,54,16,27,37,19,31,43,24,112,18,60,42,36,45,39,30,42,39,66,41,40,32,30,30,42,36,29,42,55,42,61,57,45,43,27,55,42,47,0}};

    public  int[][] matrixBK =  {{507 ,313 ,679  ,286  ,242  ,809 ,1022 ,1026 ,645  ,415  ,519  ,322 ,309  ,616 ,414 ,501  ,574  ,322 ,562 ,500 ,458 ,458  ,406 ,482 ,342  ,345 ,322 ,285 ,1522 ,1999  ,1189+1 ,494 ,89  ,190 ,199+1 ,239  ,110  ,268 ,511  ,453},
             {24 ,12  ,31 ,14  ,12  ,44 ,57  ,62 ,29 ,24 ,24  ,12 ,14  ,8 ,8 ,27  ,25  ,12 ,23  ,23 ,17  ,18  ,28 ,8 ,4  ,34 ,24 ,4  ,79  ,197  ,38 ,13  ,0  ,0 ,2  ,3  ,3 ,6 ,11  ,6},
             {31  ,15 ,39 ,11  ,7  ,47  ,66 ,63 ,35  ,21  ,30 ,13  ,11  ,24  ,24  ,20  ,31  ,13  ,27 ,26 ,23 ,19+1 ,17  ,24  ,14 ,21  ,14  ,14  ,79  ,124 ,52  ,39  ,0 ,0 ,7  ,14  ,3 ,7  ,8  ,26},
             {32 ,33 ,52 ,32 ,31  ,52  ,50  ,53  ,52 ,32  ,19+1 ,38  ,32 ,58  ,58  ,62  ,47 ,38  ,57  ,42 ,46 ,33  ,27  ,58  ,49+1 ,4  ,24  ,35  ,122  ,23  ,141  ,21  ,22  ,43  ,31 ,24  ,18 ,40 ,91  ,47}};

    public  int[] priceBK = {590,390,710,240,220,920,1080,1230,880,490,730,390,790,370,540,790,290,240,580,430,580,710,710,350,330,840,460,320,1450,250,1150,990,150,360,250,250,440,290,390,440};
    public  int[][] matrixKFC ={{640,235,233,575,570,325,300,275,410,165,245,413,160,235,261,435,687,345,210,345,670,130,310,255,81,74,165,210,345},
            {22,10,11,25,25,12,13,10,35,14,21,31,12,19,13,23,36,22,3,5,7,4,8,2,1,4,3,3,5},
            {42,22,22,49,49,18,13,14,21,9,13,14,6,9,17,28,45,19,11,19,31,3,14,12,2,4,4,11,19},
            {58,11,11,38,38,35,32,25,19,8,12,22,9,14,13,21,33,19,21,37,61,22,39,32,16,6,27,21,37}};
    public  String[] foodMC = {"Big Chicken Snack Box", "Snack box with wings", "Chicken wings 3x", "Chicken wings 5x", "Chicken wings 7x", "Strips 3x", "Strips 5x", "Strips 7x", "Cheese sticks 3x", "Cheese sticks 15x", "Chicken McNuggets 6x", "Chicken McNuggets 18x", "Big Mac Bacon", "Big Tasty Roll", "McChicken Premier", "Big Tasty", "Triple Cheeseburger", "Big Mac", "Royal", "Big Tasty with chicken", "Caesar Roll", "McChicken", "Chickenburger", "Cheeseburger", "Hamburger", "Double Filet-O-Fish", "Filet-O-Fish", "French Fries S", "French Fries M", "French Fries L", "Potato WedgesM", "Potato Wedges L", "McFlurry", "Sundae", "Milkshake", "Pie", "Muffin", "Coke", "Orage Juice", "Bonaqua"};
    public  String[] foodBK = {"WHOPPER ", "BIG KING ", "WHOPPER  Junior", "WHOPPER  with cheese", "Cheeseburger", "Hamburger", "Double Whopper", "Bekonizer", "Triple WHOPPER ", "Steakhouse", "Double Cheeseburger", "Whopper Roll", "Caesar King", "Grillburger", "King Go Rustic", "King Go Nuggets", "Barbecue Chicken Grill", "Long Chicken", "Chickenburger", "Chicken King", "Strips King", "Fish King", "Fish Roll", "Caesar Roll with strips", "RUSTIC potatoes", "King Fries", "Wings King", "King Nuggets", "Onion Rings", "King Bouquet Big Mix", "King Bouquet Wings XXL", "Bucket King Bouquet Snack&Mix", "Caesar salad with shrimp", "Orange juice", "Pepsi", "Strawberry-vanilla pie", "Strawberry donat", "Waffle Cone", "Sunday Chocolate", "Vanilla shake", "Hot brownie with ice cream"};

    public  String[] getFoodMC() {
        return foodMC;
    }

    public  String[] getFoodBK() {
        return foodBK;
    }

    public  String[] getFoodKFC() {
        return foodKFC;
    }

    public  String[] foodKFC = {"Boxmaster","Schefburger","The shefburger is spicy","Double Schefburger","Double Shefburger spicy","Cheeseburger","Longer","Ai-Twister Cheese","Strips 5 pcs. original","Strips 2 pcs. Original","Strips 3 pcs. original","Strips 5 pcs. sharp","Strips 2 pcs. sharp","Strips 3 pcs. Sharp","Chicken wings 3 pieces","Chicken wings 5 pieces","Chicken wings 8 pieces","Caesar salad with sauce","French fries  small","French fries standard","Free Basket","Ice cream (cone)","Cheesecake with topping and raisins","Cherry pie","Americano","Cappuccino","Milk Shake","French fries  small","French fries standard"};


    public int[] getLimits() {
        return limits;
    }

    public  int[][] getMatrixMC() {
        return matrixMC;
    }

    public  int[][] getMatrixBK() {
        return matrixBK;
    }

    public  int[] getPriceBK() {
        return priceBK;
    }

    public  int[][] getMatrixKFC() {
        return matrixKFC;
    }

    public  int[] getPriceKFC() {
        return priceKFC;
    }

    public  int[] priceKFC = {750 ,470 ,470 ,850 ,850 ,300 ,250 ,250 ,740 ,370 ,470 ,740 ,370 ,470 ,410 ,750 ,990 ,510 ,260 ,330 ,490 ,170 ,310 ,250 ,370 ,370 ,320 ,260 ,350};


    public  int[] getPriceMC() {
        return priceMC;
    }
    public void setLimits(int[] limits) {
        this.limits = limits;
    }
}
