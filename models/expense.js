const mongoose = require("mongoose");

var Expense = mongoose.model("Expense", {
  shop: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Shop'
  },
  added_by: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Employee'
  },
  added_on : {type: Date},
  expense_items: [{ 
    description: String,
    amount: Number 
  }],
  total: {type: Number},
  payment_proof_sent: {type: Boolean},
  payment_status: {type: Boolean}, // true if paid
  payment_status_id: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'PaymentStatus'
  }  
});

module.exports = { Expense };

