const express = require('express');
const bodyParser = require('body-parser');

const {mongoose} = require('./db.js');

var SaleController = require('./controllers/saleController.js');
var StockItemController = require('./controllers/stockItemController.js');
var expenseController = require('./controllers/expenseController.js');

// to use the express framework
var app = express();
app.use(bodyParser.json());

app.listen(process.env.PORT || 3002, () => console.log("Server started at port 3002"));

app.use('/sales', SaleController);
app.use('/expenses', expenseController);
app.use('/stockitems',StockItemController);