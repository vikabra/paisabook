const mongoose = require("mongoose");

var StockItem = mongoose.model("StockItem", {
  stock_items: [{
    item_master: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Item'
    },
    quantity: {type: Number},
    cost_price: {type: Number},  
  }],
  discount: {type: Number},
  total:{type:Number},
  payment_status: {type: Boolean}, // true if paid
  payment_status_id: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'PaymentStatus'
  },  
  payment_proof_sent: {type: Boolean}, 
  shop: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Shop'
  }
});

module.exports = { StockItem };