const mongoose = require('mongoose');

var User = mongoose.model("Employee", {
  name: {type: String},
  address: {type: String},
  email: {type: String},
  mobile_number: {type: String},
  password: {type: String}
});

module.exports = { User }