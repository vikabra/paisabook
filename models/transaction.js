const mongoose = require("mongoose");

var Transaction = mongoose.model("Transaction", {
  shop: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Shop'
  },
  sale: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Sale'
  },
  item: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Item'
  },
  payment_status: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'PaymentStatus'
  }
});

module.exports = { Transaction };