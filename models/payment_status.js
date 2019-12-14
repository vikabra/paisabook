const mongoose = require("mongoose");

var PaymentStatus = mongoose.model("PaymentStatus", {
  cust_supp_id: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'CustomerSupplier'
  },
  shop: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Shop'
  },
  added_by: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Employee'
  },
  payment_type: {type: String}, //sale or expense or stock
  added_on: {type: Date},
  amt_paid: {type: Number},
  amt_due: {type: Number},
  due_dt: {type: Date}
});

module.exports = { PaymentStatus };