const express = require('express');
const router = express.Router();

var { Expense } = require('../models/expense.js');
var { TransactionExpenses } = require('../models/transaction_expenses.js');
var { PaymentStatus } = require('../models/payment_status.js');
var { CustomerSupplier } = require('../models/customer_supplier.js');

// => localhost:3002/expenses
router.get('/', (req, res) => {
  console.log("getting expense for shop : "+req.query.shop);
  if(req.query.shop !== null){
    Expense.find({shop:req.query.shop}, (err, doc) => {
      if (!err) { res.send(doc)}
      else {console.log('Error in Retriving expense :' + JSON.stringify(err, undefined, 2)); }
    })
  }else{
    Expense.find((err, docs) => {
      if (!err) { res.send(docs); }
      else { console.log("Error in retrieving expenses data: " + JSON.stringify(err, undefined, 2)); }
    });
  }
  
});

// => localhost:3002/expenses
router.post('/', (req, res) => {
  //headers parameter - shop, addedby
  // this step sets up the attributes of the expenses
  // and persists it in the DB
  var addedOn = new Date(req.body.added_on);
  var dueDt;
  var amtDue;
  var exp = new Expense({
    shop: req.headers.shop,
    added_by: req.headers.addedby,
    added_on: addedOn,
    expense_items: req.body.expense_items,
    total: req.body.total,
    payment_proof_sent: req.body.payment_proof_sent,
    payment_status: req.body.payment_status,
  });

  if(!exp.payment_status){
    console.log("Payment not done adding to Payment status collection");
    dueDt = new Date(req.body.due_dt);
    amtDue = req.body.total - req.body.amt_paid;
    var pay = new PaymentStatus({
      //cust_supp_id not added TBD
      shop: req.headers.shop,
      added_by: req.headers.addedby,
      added_on: addedOn,
      payment_type: "Expense",
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
  
  
  exp.save((err, docs) => {
    if (!err) { 
      // now creating the transaction object so that it can be available
      // inside transactions later
      var transaction = new TransactionExpenses({
        transaction_type: "Expense",
        shop: req.headers.shop,
        added_by: req.headers.addedby,
        added_on: addedOn,
        total: req.body.total,
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
          console.log("Error in saving expenses data: " + JSON.stringify(err, undefined, 2))  
        }
      })
      res.send(docs); 
    } 
    else { 
      console.log("Error in saving expenses data: " + JSON.stringify(err, undefined, 2)); 
      res.send("Error");
    }
  });
});

/*router.get('/:shopId', (req, res) => {
  console.log('Error in RDFGDSDFSF');
  Expense.find({'shop_id':req.params.shopId}, (err, doc) => {
    if (!err) { res.send(doc)}
    else {console.log('Error in Retriving expense :' + JSON.stringify(err, undefined, 2)); }
  })      
})*/


module.exports = router;