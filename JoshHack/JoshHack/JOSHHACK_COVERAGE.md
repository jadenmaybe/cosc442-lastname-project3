Baseline: 0%<br>
Final: 100% condition and Branch Coverage<br>

- get():
  - testGet_ItemAtIndex() verifies that items added to the inventory can be successfully retrieved at their exact expected index.
- add():
  - testAdd_Item() validates item insertions
  - testAdd_TilFull() and testAdd_FullInventory ensure that attempting to add an item to an already full inventory is handled without throwing exceptions or overwriting slots
- remove():
  - testRemove_ExistingItem() tests successful removal of an item, checking if the target slot becomes null
  - testRemove_NonExistentItem() checks handling removing an item that does not exist in the inventory
- isFull():
  - isFull_Empty(), isFull_NotFull(), and isFull_True() checks all states of isFull()
- contains():
  - contains_True() and contains_False() checks if contains() correctly identifies items that are in the inventory, or if they are not<br>

Since 100% condition is reached, it can be assumed that there is no remaining uncovered behavior.