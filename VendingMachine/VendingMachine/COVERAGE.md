My initial and final coverage results were both 100%, so no tests were added or modified.

The compound Boolean decision in makePurchase() was chosen for condition-coverage analysis.
makePurchase() has both (item !=null) and (this.balance >= item.getPrice()), where an item cannot be null and user balance cannot be less than the price of the item. 

My text, testMakePurchase_Valid(), causes both conditions to be true.

testMakePurchase_InvalidBalance() causes (this.balance >= item.getPrice()) to be false, while (item !=null) is true

testMakePurchase_NullItem() produces (item !=null) to be false and (this.balance >= item.getPrice()) to be true

When I injected faults for the test-sensitivity experiment, several tests failed depending on what was changed.
Changed the >= in makePurchase to a >, testMakePurchase_Valid() fails
Removed validation in removeItem(), testRemoveItem_Null() fails
Removed validation in insertMoney(), testInsertMoney_Valid() fails
