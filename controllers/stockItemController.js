const express = require('express');
const router = express.Router();

var { StockItem } = require('../models/stock_item.js');
var { TransactionExpenses } = require('../models/transaction_expenses.js');
var { PaymentStatus } = require('../models/payment_status.js');


// => localhost:3002/stockitems
router.get('/', (req, res) => {
    StockItem.find((err, docs) => {
      if (!err) { res.send(docs); }
      else { console.log("Error in retrieving stock item data: " + JSON.stringify(err, undefined, 2)); }
    });
  });
  
  // => localhost:3002/stockitems
  router.post('/', (req, res) => {  //headers parameter - shop, addedby
    // this step sets up the attributes of the expenses
    // and persists it in the DB
    var addedOn = new Date(req.body.added_on);
    var dueDt;
    var calc_total = 0;
    items = req.body.stock_items;
    for(var i=0;i<items.length;i++){
      var item_price = items[i].cost_price*items[i].quantity;
      calc_total+=item_price;
    }
    var final_total = calc_total*(100-req.body.discount)/100;
    var amtDue;
      var stock_item = new StockItem({
      shop: req.headers.shop,
      added_by: req.headers.addedby,
      added_on: addedOn,
      stock_items: items,
      discount: req.body.discount,
      total: final_total,
      payment_proof_sent: req.body.payment_proof_sent,
      payment_status: req.body.payment_status,
    });    

  if(!stock_item.payment_status){
    console.log("Payment not done adding to Payment status collection");
    dueDt = new Date(req.body.due_dt);
    amtDue = final_total - req.body.amt_paid;
    var pay = new PaymentStatus({
      //cust_supp_id not added TBD
      shop: req.headers.shop,
      added_by: req.headers.addedby,
      added_on: addedOn,
      payment_type: "Stock Item",
      amt_paid: req.body.amt_paid,
      amt_due: amtDue,
      due_dt: dueDt
    });
    pay.save((err, docs) => {
      if (!err) { 
        exp.payment_status_id = docs._id;   
      } 
      else { 
        console.log("Error in saving Payment Status data: " + JSON.stringify(err, undefined, 2)); 
        res.send("Error");
      }
    });
  }
  stock_item.save((err, docs) => {
    if (!err) { 
      // now creating the transaction object so that it can be available
      // inside transactions later
      var transaction = new TransactionExpenses({
        transaction_type: "Stock Item",
        shop: req.headers.shop,
        added_by: req.headers.addedby,
        added_on: addedOn,
        total: final_total,
        amt_paid: req.body.amt_paid,
        amt_due: amtDue,
        due_dt: dueDt,
        payment_proof_sent: req.body.payment_proof_sent,
        payment_status: req.body.payment_status,
        name: req.body.name,
        mobile_number: req.body.mobile_number
      })
      transaction.save((err, docs) => {
        if (err) {
          console.log("Error in saving Stock Item data: " + JSON.stringify(err, undefined, 2))  
        }
      })
      res.send(docs); 
    } 
    else { 
      console.log("Error in saving Stock Item data: " + JSON.stringify(err, undefined, 2)); 
      res.send("Error");
    }
  });
});

  
  
  router.get('/:id', (req, res) => {
    if (!ObjectId.isValid(req.params.id))
      return res.status(400).send(`No stock item with the given ID : ${req.params.id}`);
    stock_item = StockItem.findById(req.params.id, (err, doc) => {
      if (!err) { res.send(doc)}
      else {console.log('Error in Retriving stock item :' + JSON.stringify(err, undefined, 2)); }
    })      
  })
  
  
  module.exports = router;