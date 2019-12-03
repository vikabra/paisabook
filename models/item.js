const mongoose = require("mongoose");

var Item = mongoose.model("Item", {
  description: {type: String},
  company: {type: String},
  brand: {type: String},
  retail_price: {type: Number},
  cost_price: {type: Boolean}
});

module.exports = { Item };