# Console Application : Online Book Shop Application

## Target Audience 

### Customer

### Features :

#### There is 2 log in Option

-  User Log in Option
-  Admin Log in Option

### User

- user has sign in , sign up , delete account , recover deleted Account
   and log out Option.

- user can search books by bookName , bookAuthor and bookGenre.

- User can buy books accordingly to the stocks count

- user can do payment

### Admin

- Admin also has sign in , sign up , delete account , recover deleted Account
   and log out Option.

- Admin can change Books Price and Books Stocks Account

- Admin can Add new Books

- Admin can Delete UnSold Books.


### Folder :

### Online Book Store

- App's Data.json

- User and Admin's Personal Details.json

- User and Admin Usage stats.json

### Model Packages :

- colors
- dto
- main
- repository
- util
- view
- viewmodel

class Diagram :


+-----------------------------------------------------------------+
|                       Bookshop                                  |
+-----------------------------------------------------------------+
| - OnlineBookPurchaseRepository                                  |
+-----------------------------------------------------------------+
| + getInstance(): OnlineBookPurchaseRepository                   |
| + getJsonRetreiver(): JSONObject                                |
| + setJsonRetreiver(jsonObject: JSONObject): void                |
| + getJsonPersonalDetailsRetreiver(): JSONObject                 |
| + setJsonPersonalDetailsRetreiver(jsonObject: JSONObject): void |
| + getJsonUserStatsRetreiver(): JSONObject                       |
| + setJsonUserStatsRetreiver(jsonObject: JSONObject): void       |
+-----------------------------------------------------------------+

+-----------------------------------------------------------------+
|                 OnlineBookPurchaseRepository                    |
+-----------------------------------------------------------------+
| - jsonObject: JSONObject                                        |
| - jsonPersonalDetails: JSONObject                               |
| - jsonUserStats: JSONObject                                     |
+-----------------------------------------------------------------+
| + getInstance(): OnlineBookPurchaseRepository                   |
| + getJsonRetreiver(): JSONObject                                |
| + setJsonRetreiver(jsonObject: JSONObject): void                |
| + getJsonPersonalDetailsRetreiver(): JSONObject                 |
| + setJsonPersonalDetailsRetreiver(jsonObject: JSONObject): void |
| + getJsonUserStatsRetreiver(): JSONObject                       |
| + setJsonUserStatsRetreiver(jsonObject: JSONObject): void       |
+-----------------------------------------------------------------+

+-----------------------------------------------------+
|                   FileHandling                      |
+-----------------------------------------------------+
| - PATH_MAIN: String                                 |
| - PATH_PERSONAL_DETAILS: String                     |
| - PATH_USER_STATS_DETAILS: String                   |
+------------------------------------------------------------------------------------------------------------+
| + writeMainFile(jsonObject: JSONObject): void                                                              |
| + writeAdminStatsOfStocksInFile(currBook: JSONObject, userName: String, newCount: int): void               |
| + writeAdminStatsOfPriceInFile(currBook: JSONObject, userName: String, newPrice: int): void                |
| + writeAdminStatsOfNewBookInFile(userName: String, newBook: JSONObject): void                              |
| + writeAdminStatsOfDeleteBookInFile(userName: String, deletedBook: JSONObject): void                       |
| + addUserStatsInFile(currBook: JSONObject, noOfBooks: long, receivedMoney: double, userName: String): void |
| + writeNewUserInFile(person: Persons): void                                                                |
| + isAdminAlreadyPresent(userName: String, choice: int): boolean                                            |
| + isUserAlreadyPresent(userName: String, choice: int): boolean                                             |
| + deleteAdminInFile(userName: String): void                                                                |
| + deleteUserInFile(userName: String): void                                                                 |
| + recoverAdminInFile(userName: String): void                                                               |
| + recoverUserInFile(userName: String): void                                                                |
+------------------------------------------------------------------------------------------------------------+

+------------------------------------------------------+
|                       Persons                        |
+------------------------------------------------------+
| - userName: String                                   |
| - passWord: String                                   |
| - ID: long                                           |
+------------------------------------------------------+
| + getUserName(): String                              |
| + setUserName(userName: String): void                |
| + getPassWord(): String                              |
| + setPassWord(passWord: String): void                |
| + getID(): long                                      |
| + setID(ID: long): void                              |
+------------------------------------------------------+

+-------------------------------------------------------------------------+
|              AvailableBooksViewModel                                    |
+-------------------------------------------------------------------------+
| - availableBooksView: AvailableBooksView                                |
+-------------------------------------------------------------------------+
| + AvailableBooksViewModel(availableBooksView: AvailableBooksView): void |
| + getJsonArray(): JSONArray                                             |
| + findRelatedBookByTitle(key: String): void                             |
| + findRelatedBookByAuthor(key: String): void                            |
| + findRelatedBookByGenre(key: String): void                             |
+-------------------------------------------------------------------------+

+--------------------------------------------------------+
|                  JsonDataViewModel                     |
+--------------------------------------------------------+
| - jsonDataView: JsonDataview                           |
+--------------------------------------------------------+
| + JsonDataViewModel(jsonDataview: JsonDataview): void  |
| + assignJsonObject(): void                             |
+--------------------------------------------------------+



+-------------------------------------------------------------------------------------------------+
|               ModifyBooksViewModel                                                              |
+-------------------------------------------------------------------------------------------------+
| - modifyBooksView: ModifyBooksView                                                              |
+-------------------------------------------------------------------------------------------------+
| + ModifyBooksViewModel(modifyBooksView: ModifyBooksView): void                                  |
| + modifyBooksStocks(userName: String): void                                                     |
| + modifyTheCurrentBookStock(currBook: JSONObject, userName: String): boolean                    |
| + modifyBooksPrice(userName: String): void                                                      |
| + modifyTheCurrentBookPrice(currBook: JSONObject, userName: String): boolean                    |
| + showHistoryOfAdmin(userName: String): void                                                    |
| + findFullHistoryOfAdmin(adminArray: JSONArray, adminStats: JSONObject, userName: String): void |
| + addNewBook(userName: String): void                                                            |
| + deleteABook(userName: String): void                                                           |
| + isBookPresence(bookTitle: String): boolean                                                    |
+-------------------------------------------------------------------------------------------------+


+------------------------------------------------------------------------------+
|                       PurchaseBookViewModel                                  |
+------------------------------------------------------------------------------+
| - purchaseBookView: PurchaseBookView                                         |
+------------------------------------------------------------------------------+
| + PurchaseBookViewModel(purchaseBookView: PurchaseBookView): void            |
| + bookHasNoOfBooks(choice: int, noOfBooks: long): boolean                    |
| + getJsonArray(): JSONArray                                                  |
| + purchaseCurrentBooks(choice: int, noOfBooks: long, userName: String): void |
+------------------------------------------------------------------------------+


+-------------------------------------------------------------------------------+
|                       UserValidationViewModel                                 |
+-------------------------------------------------------------------------------+
| - userValidationView: UserValidationView                                      |
+-------------------------------------------------------------------------------+
| + UserValidationViewModel(userValidationView: UserValidationView): void       |
| + createUserNameAndPasswordForUser(choice: int): String                       |
| + UserNameAndPasswordValidation(userName: String, passWord: String): boolean  |
| + userVerify(choice: int): String                                             |
| + createUserNameAndPasswordForAdmin(choice: int): String                      |  
+-------------------------------------------------------------------------------+




