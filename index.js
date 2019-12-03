const express = require('express');
const bodyParser = require('body-parser');

const {mongoose} = require('./db.js');

var SaleController = require('./controllers/saleController.js');
var StockItemController = require('./controllers/stockItemController.js');

// to use the express framework
var app = express();
app.use(bodyParser.json());

app.listen(3002, () => console.log("Server started at port 3002"));

app.use('/sales', SaleController);