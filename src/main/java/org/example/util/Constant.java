package org.example.util;

public class Constant {
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgrepass";
    public static final String URL = "jdbc:postgresql://localhost:5432/hw08";

    public static final String startMenu =
            "\n========Welcome========\nYou want to: \n1. Register\n2. Login\n0. Exit";

    public static final String menu =
            "\n========Home Menu========\nGo to:\n1. Category Menu\n2. Brand Menu\n3. Product Menu\n4. Share Holder Menu\n0. Logout";

    public static final String categoryMenu =
            "\n========Category Menu========\n1. Add a new category\n2. Remove a category\n3. Edit a category by ID\n4. Find a category by ID\n5. Show all categories\n0. Back to home menu";

    public static final String brandMenu =
            "\n========Brand Menu========\n1. Add a new brand\n2. Remove a brand\n3. Edit a brand by ID\n4. Find a brand by ID\n5. Show all brands\n0. Back to home menu";

    public static final String productMenu =
            "\n========Product Menu========\n1. Add a new product\n2. Remove a product\n3. Edit a product by ID\n4. Find a product by ID\n0. Back to home menu";

    public static final String shareholderMenu =
            "\n========Shareholder Menu========\n1. Add a new shareholder\n2. Remove a shareholder\n3. Edit a shareholder info by ID\n4. Load shareholder info by ID\n5. Take share of a brand\n6. Sell share of a brand\n7. Change existing share\n8. Show all shares and shareholders info\n0. Back to home menu";
}
