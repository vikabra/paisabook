const mongoose = require("mongoose");

var Transaction = mongoose.model("Transaction", {
  shop: {type: String},
  transaction_type: {type: String},
  amount_paid: {type: Number},
  quantity: {type: Number},
  discount: {type: Number},
  retail_price: {type: Number},
  stock_item: {type: String}
});

module.exports = { Transaction };