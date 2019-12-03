const express = require('express');
const router = express.Router();

var { Sale } = require('../models/sale.js');
var { Transaction } = require('../models/transaction.js');

// => localhost:3002/sales
router.get('/', (req, res) => {
  Sale.find((err, docs) => {
    if (!err) { res.send(docs); }
    else { console.log("Error in retrieving sales data: " + JSON.stringify(err, undefined, 2)); }
  });
});

// => localhost:3002/sales
router.post('/', (req, res) => {
  // this step sets up the attributes of the sale
  // and persists it in the DB
  var sale = new Sale({
    description: req.body.stock_item,
    quantity: req.body.quantity,
    retail_price: req.body.retail_price,
    discount: req.body.discount,
    invoice_needed: req.body.invoice_needed,
    amount_paid: req.body.amount_paid
  });
  sale.save((err, docs) => {
    if (!err) { 
      // now creating the transaction object so that it can be available
      // inside transactions later
      var transaction = new Transaction({
        transaction_type: "Sale",
        amount_paid: req.body.amount_paid,
        quantity: req.body.quantity,
        discount: req.body.discount,
        retail_price: req.body.retail_price,
        stock_item: req.body.stock_item
      })
      transaction.save((err, docs) => {
        if (err) {
          console.log("Error in saving sales data: " + JSON.stringify(err, undefined, 2))  
        }
      })
      res.send(docs); 
    } 
    else { 
      console.log("Error in saving sales data: " + JSON.stringify(err, undefined, 2)); 
    }
  });
});

router.get('/:id', (req, res) => {
  if (!ObjectId.isValid(req.params.id))
    return res.status(400).send(`No sale with the given ID : ${req.params.id}`);
  sale = Sale.findById(req.params.id, (err, doc) => {
    if (!err) { res.send(doc)}
    else {console.log('Error in Retriving Sale :' + JSON.stringify(err, undefined, 2)); }
  })      
})


module.exports = router;