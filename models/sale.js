const mongoose = require("mongoose");

var Sale = mongoose.model("Sale", {
  description: {type: String},
  quantity: {type: Number},
  retail_price: {type: Number},
  discount: {type: Number},
  invoice_needed: {type: Boolean},
  item: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Item'
  }
});

module.exports = { Sale };

