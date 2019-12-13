const mongoose = require("mongoose");

var TransactionExpenses = mongoose.model("TransactionExpenses", {
  transaction_type: {type: String},
  shop: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Shop'
  },
  added_by: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Employee'
  },
  added_on : {type: Date},
  total: {type: Number},
  payment_proof_sent: {type: Boolean},
  payment_status: {type: Boolean}, // true if paid
  amt_paid: {type: Number},
  amt_due: {type: Number},
  due_dt: {type: Date},
  name: {type: String},
  mobile_number: {type: String}

}, 'transactions');

module.exports = { TransactionExpenses };