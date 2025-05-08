print('###################################### Init custom database ##########################################');
// use quickstartdb;
db.createCollection('products');
db.createCollection('user');
db.createCollection('test');
db.createCollection('DeadProducts');

db.createCollection("userInteracs_page1")
db.createCollection("userInteracs_page2")
db.createCollection("userInteracs_page3")