const mongoose = require("mongoose");

var PaymentStatus = mongoose.model("PaymentStatus", {
  name: {type: String}
});

module.exports = { PaymentStatus };