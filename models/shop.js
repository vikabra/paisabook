const mongoose = require("mongoose");

var Shop = mongoose.model("Shop", {
  name: {type: String},
  address: {type: String},
  registration_no: {type: String}
});

module.exports = { Shop }