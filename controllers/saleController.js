const express = require('express');
const router = express.Router();

var { Sale } = require('../models/sale.js');
var { Shop } = require('../models/shop.js');
var { PaymentStatus } = require('../models/payment_status.js');
var { Item } = require('../models/item.js');

// => localhost:3002/sales
router.get('/', (req, res) => {
  Sale.find((err, docs) => {
    if (!err) { res.send(docs); }
    else { console.log("Error in retrieving sales data: " + JSON.stringify(err, undefined, 2)); }
  });
});

// => localhost:3002/sales
router.post('/', (req, res) => {
  var payment_type = PaymentStatus.findById(req.params.payment_type_id);
  var shop = Shop.findById(req.params.shop_id, (err, docs) => {
    console.log("Errors so far: " + JSON.stringify(docs));
    if (!err) {
      var shop = docs; 
      console.log("Shop details found:" + JSON.stringify(shop));
      // first find the item that is being sold and initialize it
      var item = Item.findById(req.params.item_id, (err, docs) => {
        // i.e. if the item doesn't exist create it on the fly, but now 
        // throwing an error
        if (err) {
          res.status(400).send(`No item with given ID: ${req.params.shop_id}`);
        }
        else {
          var item = docs;
        }
      });
      // this step sets up the attributes of the sale
      // and persists it in the DB
      var sale = new Sale({
        description: req.body.description,
        quantity: req.body.quantity,
        retail_price: req.body.retail_price,
        discount: req.body.discount,
        invoice_needed: req.body.invoice_needed,
        shop: shop.id,
        payment_type: payment_type.id,
        item: item.id
      });
      sale.save((err, docs) => {
        if (!err) { 
          // now creating the trail object so that it can be available
          // inside transactions later
          // var transaction = new transaction({
          //   shop: shop.id,
          //   sale: sale.id,
          //   item: item.id,
          //   payment_status: payment_status.id
          // })
          // res.send(docs); 
        }
        else { 
          console.log("Error in saving sales data: " + JSON.stringify(err, undefined, 2)); 
        }
      });
    }
    else { 
      return res.status(400).send(`No shop with given ID: ${req.params.shop_id}`)
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