Open source, credit must be given

It contains some useful functions including visiualization of the trees, BST and AVL tree.

stateDiagram
[*] --> Choose_mode : start

state "Choose mode" as Choose_mode {
    entry / promptUserForMode
    exit / logModeSelection
}
state "Mode 1\n(limit surveillance)" as Mode_1 {
    entry / setLimitedSurveillance
    exit / clearSurveillanceMode
}
state "Mode 2\n(full time surveillance)" as Mode_2 {
    entry / setFullSurveillance
    exit / clearSurveillanceMode
}
state "User asks for a dish\n(with or without specification)" as User_asks_dish_1 {
    entry / askForDish
    exit / logDishRequest
}
state "Bono fetches recipes\nand asks which one to follow" as Bono_fetches_1 {
    entry / fetchRecipes
    exit / logRecipeSelection
}
state "Bono asks user how to read recipe" as Bono_asks_user_1 {
    entry / promptReadMode
    exit / logReadMode
}
state "Chef Bono reads all recipe" as Chef_Bono_reads_all_1 {
    entry / readAllRecipe
    exit / logRecipeRead
}
state "Chef Bono reads one step at a time" as Chef_Bono_reads_one_1 {
    entry / readStep
    exit / logStepRead
}
state "User asks question" as User_ask_question_1 {
    entry / answerUserQuestion
    exit / logUserQuestion
}
state "Dish is ready" as Dish_ready_1 {
    entry / notifyDishReady
    exit / logDishReady
}

state "User asks for a dish\n(with or without specification)" as User_asks_dish_2 {
    entry / askForDish
    exit / logDishRequest
}
state "Bono fetches recipes\nand asks which one to follow" as Bono_fetches_2 {
    entry / fetchRecipes
    exit / logRecipeSelection
}
state "Bono asks user how to read recipe" as Bono_asks_user_2 {
    entry / promptReadMode
    exit / logReadMode
}
state "Chef Bono reads all recipe" as Chef_Bono_reads_all_2 {
    entry / readAllRecipe
    exit / logRecipeRead
}
state "Chef Bono reads one step at a time" as Chef_Bono_reads_one_2 {
    entry / readStep
    exit / logStepRead
}
state "User asks question" as User_ask_question_2 {
    entry / answerUserQuestion
    exit / logUserQuestion
}
state "Bono Intervenes" as Bono_Intervenes {
    entry / intervene
    exit / logIntervention
}
state "Dish is ready" as Dish_ready_2 {
    entry / notifyDishReady
    exit / logDishReady
}

Choose_mode --> Mode_1 : modeSelected(mode1)
Choose_mode --> Mode_2 : modeSelected(mode2)

Mode_1 --> User_asks_dish_1 : userRequestsDish
Mode_2 --> User_asks_dish_2 : userRequestsDish

User_asks_dish_1 --> Bono_fetches_1 : dishRequested
User_asks_dish_2 --> Bono_fetches_2 : dishRequested

Bono_fetches_1 --> Bono_asks_user_1 : recipeSelected
Bono_fetches_1 --> User_asks_dish_1 : recipeRejected
Bono_fetches_2 --> Bono_asks_user_2 : recipeSelected
Bono_fetches_2 --> User_asks_dish_2 : recipeRejected

Bono_asks_user_1 --> Chef_Bono_reads_all_1 : readAllSelected
Bono_asks_user_1 --> Chef_Bono_reads_one_1 : readStepSelected
Bono_asks_user_2 --> Chef_Bono_reads_all_2 : readAllSelected
Bono_asks_user_2 --> Chef_Bono_reads_one_2 : readStepSelected
Bono_asks_user_2 --> Chef_Bono_reads_all_2 : triggered

Chef_Bono_reads_all_1 --> User_ask_question_1 : userAsksQuestion
Chef_Bono_reads_all_1 --> Dish_ready_1 : recipeComplete
Chef_Bono_reads_all_2 --> User_ask_question_2 : userAsksQuestion
Chef_Bono_reads_all_2 --> Bono_Intervenes : interrupt
Chef_Bono_reads_all_2 --> Dish_ready_2 : recipeComplete

Chef_Bono_reads_one_1 --> User_ask_question_1 : userAsksQuestion
Chef_Bono_reads_one_1 --> Dish_ready_1 : recipeComplete
Chef_Bono_reads_one_2 --> User_ask_question_2 : userAsksQuestion
Chef_Bono_reads_one_2 --> Bono_Intervenes : interrupt
Chef_Bono_reads_one_2 --> Dish_ready_2 : recipeComplete

User_ask_question_1 --> User_ask_question_1 : userAsksQuestion
User_ask_question_1 --> Dish_ready_1 : recipeComplete
User_ask_question_2 --> User_ask_question_2 : userAsksQuestion
User_ask_question_2 --> Bono_Intervenes : interrupt
User_ask_question_2 --> Dish_ready_2 : recipeComplete

Bono_Intervenes --> User_ask_question_2 : userAsksQuestion
Bono_Intervenes --> Dish_ready_2 : recipeComplete

Dish_ready_1 --> [*] : end
Dish_ready_2 --> [*] : end
