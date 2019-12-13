const mongoose = require('mongoose');

var CustomerSupplier = mongoose.model("CustomerSupplier", {
  shop: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Shop'
  },
  name: {type: String},
  email: {type: String},
  mobile_number: {type: String}
});

module.exports = { CustomerSupplier }