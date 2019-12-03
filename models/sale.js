const mongoose = require("mongoose");

var Sale = mongoose.model("Sale", {
  description: {type: String},
  quantity: {type: Number},
  retail_price: {type: Number},
  discount: {type: Number},
  invoice_needed: {type: Boolean},
  stock_item: {type: String},
  amount_paid: {type: Number}
});

module.exports = { Sale };

